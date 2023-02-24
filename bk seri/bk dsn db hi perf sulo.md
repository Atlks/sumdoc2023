 bk dsn db hi perf sulo


 # some biz use anoth instalnce ...

 iso leve ..readuncommit........t
 trx log  per sec log
no insert chk..unique ,foreight key  eth...not null chk...not null


# memery table model

#  share分片模式。。

那就要把底层的缓存搞好，让更少的请求直接到数据库，因为数据库的高并发实现起来是比较麻烦的，而且有些操作还有事务的要求等等，所以很难做到非常高的并发。