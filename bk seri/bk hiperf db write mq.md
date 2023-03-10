bk hiperf  db write mq


基本概念
leveldb是一个写性能十分优秀的存储引擎，是典型的LSM树(Log Structured-Merge Tree)实现。LSM树的核心思想就是放弃部分读的性能，换取最大的写入能力。

LSM树写性能极高的原理，简单地来说就是尽量减少随机写的次数。对于每次写入操作，并不是直接将最新的数据驻留在磁盘中，而是将其拆分成（1）一次日志文件的顺序写（2）一次内存中的数据插入。leveldb正是实践了这种思想，将数据首先更新在内存中，当内存中的数据达到一定的阈值，将这部分数据真正刷新到磁盘文件中，因而获得了极高的写性能（顺序写60MB/s, 随机写45MB/s）。


memtable
之前提到，leveldb的一次写入操作并不是直接将数据刷新到磁盘文件，而是首先写入到内存中作为代替，memtable就是一个在内存中进行数据组织与维护的结构。memtable中，所有的数据按用户定义的排序方法排序之后按序存储，等到其存储内容的容量达到阈值时（默认为4MB），便将其转换成一个不可修改的memtable，与此同时创建一个新的memtable，供用户继续进行读写操作。memtable底层使用了一种跳表数据结构，这种数据结构效率可以比拟二叉查找树，绝大多数操作的时间复杂度为O(log n)。


Leveldb是一个google实现的非常高效的kv数据库，目前的版本1.2能够支持billion级别的数据量了。 在这个数量级别下还有着非常高的性能，主要归功于它的良好的设计。特别是LSM算法。


它是 Google 开源的 NOSQL 存储引擎库，是现代分布式存储领域的一枚原子弹。在它的基础之上，Facebook 开发出了另一个 NOSQL 存储引擎库 RocksDB，沿用了 LevelDB 的先进技术架构的同时还解决了 LevelDB 的一些短板。你可以将 RocksDB 比喻成氢弹，它比 LevelDB 的威力更大一些。现代开源市场上有很多数据库都在使用 RocksDB 作为底层存储引擎，比如大名鼎鼎的 TiDB


Redis 缓存有什么问题？
当我们将 Redis 拿来做缓存用时，背后肯定还有一个持久层数据库记录了全量的冷热数据。Redis 和持久层数据库之间的数据一致性是由应用程序自己来控制的。应用程序会优先去缓存中获取数据，当缓存中没有数据时，应用程序需要从持久层加载数据，然后再放进缓存中。当数据更新发生时，需要将缓存置为失效。

fu 
}


有过这方面开发经验的朋友们就知道写这样的代码还是挺繁琐的，所有的涉及到缓存的业务代码都需要加上这一部分逻辑。



严格来说我们还需要仔细考虑缓存一致性问题，比如在 updateUser 方法中，数据库正确执行了更新，但是缓存 redis 因为网络抖动等原因置为失效没有成功，那么缓存中的数据就成了过期数据。如果你将设置缓存和更新持久存的先后顺序反过来

LevelDB 是如何解决的？
LevelDB 将 Redis 缓存和持久层合二为一，一次性帮你搞定缓存和持久层。有了 LevelDB，你的代码可以简化成下面这样


LevelDB 具体是什么？
前面我们说道它是一个 NOSQL 存储引擎，它和 Redis 不是一个概念。Redis 是一个完备的数据库，而 LevelDB 它只是一个引擎。如果将数据库必须成一辆高级跑车，那么存储引擎就是它的发动机，是核心是心脏。有了这个发动机，我们再给它包装上一系列的配件和装饰，就可以成为数据库


evelDB 性能优化上做了哪些事情
数据内存操作
写操作完全基于内存实现，速度无疑会很快，但是相对于Redis来看，由于是多线程或者多协程操作，会存在强锁问题。读操作，热点数据内存中大概率会读到，即使读不到也会有下面“磁盘顺序读写”来进一步保证性能。
 但是很显然levelDB是一种适合写多读少的NoSQL数据库。

磁盘顺序读写
磁盘随机读写和顺序读写的性能差异是惊人的，levelDB正是利用了这一点来做的。
 随机读写做下差异比较的话，普通磁盘的顺序访问速度跟SSD顺序访问速度差不多一致，


 额外说几句，这里的磁盘顺序写及分层结构，其实本质上就是一种LSM（Log-Structured Merge Tree）存储引擎的思想，解决了B+树随机读的问题，但是对应的也牺牲了一定的读性能，归并操作都是为了优化读性能，类似的还有TSM(Time-Structured Merge Tree），有兴趣可以看一下，比如InfluxDB底层的存储引擎经历了从LevelDB到BlotDB，再到选择自研TSM的过程，TSM其实就是针对LSM引擎文件句柄过多、无TTL机制、减缓删除流量压力等所产生出的一种结构，本质的思想其实还是LSM。