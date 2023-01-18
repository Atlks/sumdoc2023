长连接通知 类库 netty mina mq
sdffdd
dsfsdf  fffdf
Netty、Apache Mina和其他类似高性能网络解决方案的便捷vdfsddfdsafdfdsfdsf

df

业务开发者之need   bizHandler 走ok、、、


通信调度层 Reactor：该层的主要职责就是监听网络的读写和连接操作，负责将网络层的数据读取到内存缓冲区，然后触发各种网络事件，例如连接创建、连接激活、读事件、写事件等，将这些事件触发到 PipeLine 中，由 PipeLine 管理的责任链来进行后续的处理

责任链层 Pipeline：它负责上述的各种网络事件在责任链中的有序传播，同时负责动态地编排责任链。责任链可以选择监听和处理自己关心的事件，它可以拦截处理事件，以及向前向后传播事件，实现了架构层面的分层隔离

业务逻辑编排层 Service ChannelHandler：业务逻辑编排层通常有两类，一类是纯粹的业务逻辑编排，还有一类是其他的应用层协议插件，用于特定协议相关的会话和链路管理


# mina vs netty

mina的代码很简洁。。。netty的代码好啰嗦了。。。
mina的是命令式样代码。。netty的函数式样