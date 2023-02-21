bk dsn idxless arch

use bk dsn 存储多元化，文件分区 配置文件 rds mgdb

 

- [data fmr use json](#data-fmr-use-json)
- [map set list to jsonSEriz store...](#map-set-list-to-jsonseriz-store)
- [纯文件也可以很大数据量提升性能。。](#纯文件也可以很大数据量提升性能)
- [文件夹folder可以作为层次数据库 tree结构主键，按照ui来一一对应哈哈。](#文件夹folder可以作为层次数据库-tree结构主键按照ui来一一对应哈哈)
- [zip file mode](#zip-file-mode)
- [embd db sqlt](#embd-db-sqlt)
- [rds](#rds)
- [mgdb模式](#mgdb模式)
- [pgsql mysql](#pgsql-mysql)


上一篇我们讲述了哪些情况下可以使用索引（11种情况，这也是我们非常重视的sql优化的部分）。那么哪些情况下不适合使用索引呢？这里我们列出了7中情况。

1.在where条件中（包括group by以及order by）里用不到的字段不需要创建索引，索引的价值是快速定位，如果起不到定位的字段通常是不需要创建索引的。

2.数据量小的表最好不要使用索引

如果表中记录太少，比如少于1000个，那么是不需要创建索引的。表记录太少，是否创建索引对查询效率的影响并不大。甚至说，查询花费的时间可能比遍历索引的时间
————————————————


强制使用路径索引模式来访问数据。。数据直接按照tree来安排。。默认索引了相当于

把wehre字段带入到路径中。。

层次数据库存储模型简单。。vs 网状db



