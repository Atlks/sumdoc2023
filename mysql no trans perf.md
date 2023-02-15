mysql no trans perf

<!-- TOC -->

- [insert 1w  11s](#insert-1w--11s)
- [定时刷盘日志 9w dt  5s](#定时刷盘日志-9w-dt--5s)
- [测试内存表 ist 9w   3s 中了。。](#测试内存表-ist-9w---3s-中了)
- [rds 9w lst  1.7s](#rds-9w-lst--17s)

<!-- /TOC -->



[toc]

def

# table

| db | pef tms per sec|
|---|---|
|rds| 4.5w ps|
| mysql mmr | 3w ps |
| mysql trx0 nochk| 3w ps |
| mgdb | 1.5w ps |
| file append | 1.5w ps |
| msql perf n mltThrdPool | 1.5w ps |
| mysql dft | 1k ps |

调整隔离级别还是有用的。。可能50%的提升率。。。


# insert 1w  11s


Feb 14, 2023 5:13:43 PM pkg1.dbNoTrsTst main
信息: =============start.....
Feb 14, 2023 5:13:54 PM pkg1.dbNoTrsTst main
信息: ===================end.....


11s


change iso leve read uncommted but 20% enhance




select count(*) from tb

 show global variables like '%tx_isolation%'

SET GLOBAL TRANSACTION ISOLATION LEVEL READ UNCOMMITTED


 set tx_isolation='READ-COMMITTED';


 打开mysql.ini配置文件，在最后加上

可选参数有：READ-UNCOMMITTED, READ-COMMITTED, REPEATABLE-READ, SERIALIZABLE.
[mysqld]
transaction-isolation = REPEATABLE-READ



change trx log level 

set global  innodb_flush_log_at_trx_commit=0

select @@innodb_flush_log_at_trx_commit;


enhance 10倍数，，1s都不需要了

Feb 14, 2023 5:35:02 PM pkg1.dbNoTrsTst main
信息: =============start.....
Feb 14, 2023 5:35:02 PM pkg1.dbNoTrsTst main
信息: ===================end.....


# 定时刷盘日志 9w dt  5s
set global  innodb_flush_log_at_trx_commit=0
Feb 14, 2023 5:36:01 PM pkg1.dbNoTrsTst main
信息: =============start.....
Feb 14, 2023 5:36:06 PM pkg1.dbNoTrsTst main
信息: ===================end.....

# 测试内存表 ist 9w   3s 中了。。

[mysqld]
max_heap_table_size=1024M

only chg ini file,.cant chg in cmd

 show   variables like '%heap%'
set global  max_heap_table_size=999999 
not take effc

Feb 14, 2023 5:46:44 PM pkg1.dbNoTrsTst main
信息: =============start.....
Feb 14, 2023 5:46:47 PM pkg1.dbNoTrsTst main
信息: ===================end.....

# rds 9w lst  1.7s

1.7s

# 去除约束检查提升25% foreign_key_checks与unique_checks
当我们对MYSQL RDS进行大批量数据导入或加载，尤其涉及大表时，我们可以通过暂时关闭外键和唯一约束检查来提高插入性能，但是这会带来潜在的风险，您务必要确保相应的数据一致和唯一性。否则约束检查不能通过。

我们可以按以下方式进行数据库导入：

SET FOREIGN_KEY_CHECKS = 0;
SET UNIQUE_CHECKS = 0;
SET AUTOCOMMIT = 0;

4s到3s了

primary key：主键约束 不要设置主键

2、create table约束　

　　1、not null：非空约束

　　2、unique：唯一约束

　　3、primary key：主键约束

　　4、foreign key：外键

　　5、check：检查---enum、set


# 零食 禁用索引

对非空表插入数据时，MySQL 会根据表的索引对插入的记录进行排序。插入大量数据时，这些排序会降低插入数据的速度。为了解决这种情况，可以在插入数据之前先禁用索引，等到数据都插入完毕后在开启索引。

禁用索引的语句为：

ALTER TABLE table_name DISABLE KEYS;

重新开启索引的语句为：

ALTER TABLE table_name ENABLE KEYS;
 


# thread_concurrency ，，cpu2被也能提升20%




4.1.5修改thread_concurrency值，由目前默认的8，修改为64
     thread_concurrency=64





thread_concurrency的值的正确与否, 对mysql的性能影响很大, 在多个cpu(或多核)的情况下，错误设置了thread_concurrency的值, 会导致mysql不能充分利用多cpu(或多核), 出现同一时刻只能一个cpu(或核)在工作的情况。

thread_concurrency应设为CPU核数的2倍. 比如有一个双核的CPU, 那thread_concurrency  的应该为4; 2个双核的cpu, thread_concurrency的值应为8.

比如：根据上面介绍我们目前系统的配置，可知道为4个CPU,每个CPU为8核，按照上面的计算规则，这儿应为:4*8*2=64

查看系统当前thread_concurrency默认配置命令：

 show variables like 'thread_concurrency';
-----------------------------------

4.2.1.2：innodb_buffer_pool_size(默认128M)

innodb_buffer_pool_size=1024M(1G)


# insert DELAYED 语句
、改写所有insert语句为insert delayed
这个insert delayed不同之处在于：立即返回结果，后台进行处理插入。

# write file append 9wdt 5s
sprs..maybe no cache

# 没啥作用测试。的参数


   innodb_buffer_pool_size:主要针对InnoDB表性能影响最大的一个参数。功能与Key_buffer_size一样。InnoDB占用的内存，除innodb_buffer_pool_size用于存储页面缓存数据外，另外正常情况下还有大约8%的开销，主要用在每个缓存页帧的描述、adaptive hash等数据结构，如果不是安全关闭，启动时还要恢复的话，还要另开大约12%的内存用于恢复，
-----------------------------------
MySQL性能优化之参数配置
innodb_buffer_pool_size  没啥作用测试。。


 Set .._log_file_size to 25 % of buffer pool size
innodb_log_file_size=500M
innodb_log_buffer_size=800M


没啥用 adj  innodb_log_file_size

Innodb_write_io_threads  没啥用 


# call sp ywe slow........50s omg
# close many ywesu


aria_checkpoint_interval
no null check

# 

#  last final

only  innodb_flush_log_at_trx_commit=0
and delete ywesu check ,,just can 3s like memry db...
another thread adj not need  trans leve not need adj to read uncommit...


[mysqld]
#bulk_insert_buffer_size=500M
#thread_concurrency=24
# innodb_write_io_threads=24
max_heap_table_size=1024M
innodb_flush_log_at_trx_commit=0
# transaction-isolation = READ-UNCOMMITTED
port=3306


由于 InnoDB 不支持 INSERT DELAYED，因此使用大型 InnoDB 缓冲池是最接近 INSERT DELAYED 的方法。所有 DML（插入、更新和删除）都将缓存在 InnoDB 缓冲池中。写入的事务信息会立即写入重做日志（ib_logfile0、ib_logfile1