## Tasking

 - when 使用Robot存包
   - given 两个储物柜，第一个储物柜有5个剩余空间，第二个储物柜有4个剩余空间 when 使用Robot存包 then 在第一个储物柜存储成功,并出票据;
   - given 两个储物柜，第一个储物柜有4个剩余空间，第二个储物柜有5个剩余空间 when 使用Robot存包 then 在第二个储物柜存储成功,并出票据;
   - given 两个储物柜，第一个储物柜有5个剩余空间，第二个储物柜有5个剩余空间 when 使用Robot存包 then 存储成功,并出票据;
   - given 两个储物柜都没有剩余存储空间 when 使用Robot存包 then 存储失败;
 - when 使用Robot取包
   - given 两个储物柜，提供有效的票据，其对应的包存储在第一个储物柜 when 使用Robot存包 then 在第一个储物柜取包成功;
   - given 两个储物柜，提供有效的票据，其对应的包存储在第二个储物柜 when 使用Robot存包 then 在第二个储物柜取包成功;
   - given 两个储物柜，提供无效的票据 when 使用Robot存包 then 取包失败;
