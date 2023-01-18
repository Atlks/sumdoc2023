im聊天软件技术栈

 [TOC]






# 1. 使用netty mina 来实现 麻烦
mina比较简单，netty更复杂。  java nio api also dift use...


# 2. 使用mq机制更加简单 vs mina模式

面上已经存在专业的MQ有RocketMQ、Kafka等，为什么还需要Redis来自定义实现消息队列？

重！需要额外的成本负担，包括运维成本、学习成本等等；所以如果你的场景足够简单，redis 完全能满足需求，可以考虑使用 redis 做消息队列
redis 是一款轻量级内存组件，相信你一定也经常使用，使用成本低。



4、redis 消息队列优点
方便，相信目前很多项目开发已引入 redis, 因此，运维、学习成本等就降低了
轻量级，开箱即用，也可以自己做一层简单封装
多样性，可根据需求选择底层队列结构


# 3. 使用redis mq 最轻便的


4、redis 消息队列优点
方便，相信目前很多项目开发已引入 redis, 因此，运维、学习成本等就降低了
轻量级，开箱即用，也可以自己做一层简单封装
多样性，可根据需求选择底层队列结构

 # 识别恶意用户踢掉的问题。。断掉其链接

use kill client mode


## 3.1. 列出所有已连接客户端

redis 127.0.0.1:6379> CLIENT LIST
addr=127.0.0.1:43501 fd=5 age=10 idle=0 flags=N db=0 sub=0 psub=0 multi=-1 qbuf=0 qbuf-free=32768 obl=0 oll=0 omem=0 events=r cmd=client

## 3.2. 杀死当前客户端的连接

redis 127.0.0.1:6379> CLIENT KILL 127.0.0.1:43501
O
 动态更改rds密码启动
 通过代理链接 可能比较复杂了。。

 # other

 因此；Mina可以理解成对Socket 封装的通讯方式，提供client 和Server端，采用线程池的方式，提供超载保护和传输流量控制。今天可能大家看到这里还是一点晕。后面我们会介绍大家如何使用mina，如果你乐意看官方文档，可以直接跳过。