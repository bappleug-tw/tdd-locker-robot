## 业务需求：
- AC1: 可以使用储物柜上的储物格来存放物品，存放成功时返回回执单
- AC2: 储物柜上的个储物格都放满时无法继续存放
- AC3: 输入回执单号可以从对应的储物格中取出物品
- AC4: 输入已取出的单号时无法取出物品
- AC5: 输入错误的单号时无法取出物品

## Tasking:
Sample data? vs logic?
- when 进行储存操作
    - given 储物柜为空 then 存储成功，返回储物格编号1和回执单号
    - given 储物柜的0~4号储物格被占用 then 存储成功时返回的储物格编号为5
    - given 储物柜的14~18号储物格被占用 then 存储成功时返回的储物格编号为0
    - given 储物柜已占满 then 存储失败，抛出异常
- when 进行取出操作
    - given 使用存储成功返回的回执单号进行取出操作 then 返回对应的储物格编号
    - given 使用用已取出的单号进行取出操作 then 取出失败，抛出异常
    - given 使用错误的回执单号进行取出操作 then 取出失败，抛出异常
- when 创建储物柜
    - given 储物柜的最大容量:5 then 空储物柜最多连续存储五次
    - given 储物柜的最大容量:0 then 创建失败，抛出异常