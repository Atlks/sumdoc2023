bk dsn 存储多元化，文件分区 配置文件 rds mgdb

<!-- TOC -->

- [data fmr use json](#data-fmr-use-json)
- [map set list to jsonSEriz store...](#map-set-list-to-jsonseriz-store)
- [纯文件也可以很大数据量提升性能。。](#纯文件也可以很大数据量提升性能)
- [文件夹folder可以作为层次数据库 tree结构主键，按照ui来一一对应哈哈。](#文件夹folder可以作为层次数据库-tree结构主键按照ui来一一对应哈哈)
- [zip file mode](#zip-file-mode)
- [embd db sqlt](#embd-db-sqlt)
- [rds](#rds)
- [mgdb模式](#mgdb模式)
- [pgsql mysql](#pgsql-mysql)

<!-- /TOC -->
# data fmr use json

# map set list to jsonSEriz store...
# 纯文件也可以很大数据量提升性能。。
按照用户uid作为跟文件夹 db
文件夹作为_coll。。。具体文件作为rec。。   具体rec命名可以datetime_haomiao...或者主键名字比较好。。




bk dsn 分库分表模式无idx模式 ui对应table模式


文件的远程访问可以使用http暴漏即可。。
修改可以ftp ssh等。webdav 。。。online explore组件来修改。。

文件的写入速度一秒 1.5w条文件记录也足够使用了。。
foreach wiret file 2k/s ....but if zip file...5k fil per sec...

几万个记录方面够用了。。超过大几万，可以时间来分块。。too many file also can use zip file mode..hah ..



# 文件夹folder可以作为层次数据库 tree结构主键，按照ui来一一对应哈哈。
# zip file mode

zip file should write faster,,,and rd fastr..  bcs only one file out stream...


private static void wrt_zip_files() throws IOException {

        //5000 file /secd....if bufferwarp   10w file one sec...  10ps  so fast

 can replace sqlt?? no lock easy use..       

# embd db sqlt

# rds 
# mgdb模式
# pgsql mysql


