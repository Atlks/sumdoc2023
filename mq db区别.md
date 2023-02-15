mq db区别


的message store KV存储的目的就是增加写消息时的吞吐，比如顺序写磁盘，批量写page cache，每200ms fsync一次消息等；而大多数数据库，都是朝着优化读性能的设计去的。另外，还有更多的特性是数据库没有的，比如DeadLetterQueue，BackoutQueue，Topic Subscription，DelayQueue，AMQP/MQTT协议的支持，Transient messages等等。

作者：小马
链接：https://www.zhihu.com/question/381149135/answer/1094585448
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。