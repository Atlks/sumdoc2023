bk dsn arch smp arch dsn perf ha ext smp rdable


<!-- TOC -->

- [溜达目标 性能 稳定性 成本可读性 扩展性](#溜达目标-性能-稳定性-成本可读性-扩展性)
- [从ui net db 将强](#从ui-net-db-将强)
- [frm lan lib ide  smpl](#frm-lan-lib-ide--smpl)
- [从fun功能实现保证简单性](#从fun功能实现保证简单性)
- [-----------------------------具体arch](#-----------------------------具体arch)
- [prgrmlan js python ,bizdsl sql](#prgrmlan-js-python-bizdsl-sql)
- [net http get](#net-http-get)
- [file rds mgdb store](#file-rds-mgdb-store)
- [svrles ,sql as bizlan crud  vs urlExprs](#svrles-sql-as-bizlan-crud--vs-urlexprs)
- [ui list same dblist  ,jji fenbiao](#ui-list-same-dblist--jji-fenbiao)
- [no invoke lev<2     biz<util model](#no-invoke-lev2-----bizutil-model)
- [josn ext dt... schma less](#josn-ext-dt-schma-less)

<!-- /TOC -->

# 溜达目标 性能 稳定性 成本可读性 扩展性
架构设计的目的 这篇文章中说过，架构设计的目的就是为了解决软件系统复杂度带来的问题。

但是究竟复杂度有哪些呢？所以今天借此说说软件复杂度的六个来源:

1.高性能;

2.高可用;

3.可扩展性;

4.低成本;

5.安全;

smp readable........



# 从ui net db 将强
# frm lan lib ide  smpl
h5 scrpt sql yml json 


# 从fun功能实现保证简单性

标准功能模板 reg login  crud

im sys


# -----------------------------具体arch



# prgrmlan js python ,bizdsl sql
druid to parse sql..backend nosql..so no inject rsk

# net http get

# file rds mgdb store
 
# svrles ,sql as bizlan crud  vs urlExprs


bk dsn biz lan sql curd  bizlan 



?op=update dt1 set stat=ok where  id1==2344
use sql as embd dsl....good...

better than url model...
so  srvles........


db storn use rds and mgdb,,and file..so no inject risk...

#

# ui list same dblist  ,jji fenbiao

# no invoke lev<2     biz<util model

# josn ext dt... schma less