
<!-- TOC -->

- [简单易用的embd db](#简单易用的embd-db)
- [sqlite参数配置调优性能](#sqlite参数配置调优性能)
- [分片机制 uid.biz.time三级分片模式](#分片机制-uidbiztime三级分片模式)
- [数据存储简单化，全部为json模式](#数据存储简单化全部为json模式)

<!-- /TOC -->



# 简单易用的embd db

为什么使用sqlite因为简单，比起mysql，mgdb这一类网络db更加简单。

足够轻量化。。。


# sqlite参数配置调优性能


 //def is 1kps ,,loook like mysql
 我们去除插入前uniq 外键等检查 
 降低db隔离级别。。

   queryRunner.update(conn, "      PRAGMA read_uncommitted = on;"); //loks not tkefk
        queryRunner.update(conn, "PRAGMA synchronous = OFF;");


 使用事务模式 每秒去commit一下，单个库表每秒并发插入约30w/s

 上万个库表，并发插入可以达到 30亿次每秒


# 分片机制 uid.biz.time三级分片模式

 按照用户。业务。日期时间来三级分片。。

 很多 业务 二级分片已经足够使用。。群聊等业务可能需要按天来分片，特大群需要按小时分片。。。

在大业务也可按照分钟来分片。。

每个用户一个主存储文件夹。。每个业务一个库表来存储。。

frds列表使用 frds.db... 加入的群组列表使用 grp.db来存储。。
私聊信息使用   uid_frd_msg.db来存储消息。
群聊使用 多个 grp123_msg_20221212.db按照日期时间来分片存储数据即可。。

分片原则尽可能的扁平化，减少层次。。适当使用名称来缩减层次，类似命名空间模式前缀。。。

# 数据存储简单化，全部为json模式

只有一个字段c

        queryRunner.update(conn, "CREATE TABLE t (c  TEXT) ");