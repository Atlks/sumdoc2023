bk db 存储引擎

存储引擎
介绍完数据模型后，来讨论下数据库存储引擎的实现。存储引擎即对数据的一种存取机制，其中最关键的概念是索引，索引是帮助数据库高效获取数据的数据结构。常见的索引有基于 Hash Table Index、Log-Structured 和 Page-Oriented Storage（B-Tree） 。不同数据结构实现的索引各有各自的优缺点，不同的存储引擎中实现索引所采用的数据结构也不相同。

Hash Table Index
Hash Table Index 适用于 Key-Value 数据库，Riak 的 bitcask 存储引擎就使用了 Hash Table Index。写操作将记录追加在磁盘文件，并把其所在的偏移值记录在内存的哈希表中。读操作根据哈希表中 key 对应的偏移值从磁盘文件相应的位置读取。

Hash Table Index 具备了如下优点：

读写效率高。每次写操作都是顺序写，并且只需更新内存数据结构，读操作只需要查询一次内存哈希表中的偏移值再加上一次硬盘随机读。
模型简单，易于理解。
实践中使用 Hash Table Index 需要注意下面几个细节：

重启后通过遍历磁盘数据文件记录来重建哈希表会比较耗时，bitcask 采用索引文件来加速重启后重建哈希表。
使用 checksum 来检测未完整写入的数据。