impt wk drkt  tech


<!-- TOC -->

- [hiperf net  netty   Grizzly](#hiperf-net--netty---grizzly)
- [hiperf store  db  ..grizzy + leveldb](#hiperf-store--db--grizzy--leveldb)
- [dt algo...not depd db...](#dt-algonot-depd-db)
- [push tech...http long tech](#push-techhttp-long-tech)
- [稳定性高可用，，使用最简化技术。。 主从技术即可Ha](#稳定性高可用使用最简化技术-主从技术即可ha)
- [弹性扩容](#弹性扩容)
- [hiperf 系统拆分 分别优化 分割](#hiperf-系统拆分-分别优化-分割)

<!-- /TOC -->

# hiperf net  netty   Grizzly
# hiperf store  db  ..grizzy + leveldb
mysql optimz 。。no trx no check
# dt algo...not depd db...  
maybe can use embd db  to use ..

# push tech...http long tech

http+ redis block list read...binggo....

# 稳定性高可用，，使用最简化技术。。 主从技术即可Ha

Grizzly快速入门_mysonghushu的博客-CSDN博客_grizzly入门

# 弹性扩容

cloud

# hiperf 系统拆分 分别优化 分割


做系统拆分，将系统分成与用户长连接相关的连接服务器和无状态服务器两种

使用不同的技术 根据业务特点

再比如某些用户需要群发消息，但对发送速度不做要求，这样的话系统就可以将这些消息放到慢速队列中，以避免影响快速队列以及真个系统的运行速度。