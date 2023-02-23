mysql useage impt 2023

<!-- TOC -->

- [last final perf](#last-final-perf)
- [query cache ,....cau use memry table to use ...](#query-cache-cau-use-memry-table-to-use-)
- [name space   ..as db name...](#name-space---as-db-name)

<!-- /TOC -->

#  last final perf

only  innodb_flush_log_at_trx_commit=0
and delete ywesu check ,,just can 3s like memry db...
another thread adj not need  trans leve not need adj to read uncommit...


[mysqld]
 
max_heap_table_size=1024M
innodb_flush_log_at_trx_commit=0
 
port=3306

can 3w per sec........ insert
def 1k per sec........
30times enhance


# query cache ,....cau use memry table to use ...

# name space   ..as db name...



//	sql="CALL  im_admin.ist()";

ref

mysql no trans perf