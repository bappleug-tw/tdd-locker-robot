## Tasking

 - when 使用Robot存包
   - given 一个空储物柜 when 使用Robot存包 then 存包成功并返回票据;
   - given 两个储物柜，第一个储物柜空置率为2/3，第二个储物柜空置率为1/3 when 使用Robot存包 then 在第一个储物柜存储成功;
   - given 两个储物柜，第一个储物柜空置率为1/4，第二个储物柜空置率为2/3 when 使用Robot存包 then 在第二个储物柜存储成功;
   - given 两个储物柜，第一个储物柜空置率为2/6（1/3），第二个储物柜空置率为1/3 when 使用Robot存包 then 在第一个储物柜存储成功;
   - given 两个储物柜都没有剩余存储空间 when 使用Robot存包 then 存储失败;
 - when 使用Robot取包
   - given 两个储物柜，提供有效的票据，其对应的包存储在第一个储物柜 when 使用Robot取包 then 在第一个储物柜取包成功;
   - given 两个储物柜，提供有效的票据，其对应的包存储在第二个储物柜 when 使用Robot取包 then 在第二个储物柜取包成功;
   - given 两个储物柜，提供无效的票据 when 使用Robot存包 then 取包失败;
