rds perf adj


开启AOF前面我们分析了 RDB 和 AOF rewrite 对 Redis 性能的影响，主要关注点在 fork 上。其实，关于数据持久化方面，还有影响 Redis 性能的因素，这次我们重点来看 AOF 数据持久化。如果你的 AOF 配置不合理，还是有可能会导致性能问题。当 Redis 开启 AOF 后，其工作原理如下：Redis 执行写命令后，把这个命令写入到 AOF 文件内存中（write 系统调用）Redis 根据配置的 AOF 刷盘策略，把 AOF 内存数据刷到磁盘上（fsync 系统调用）为了保证 AOF 文件数据的安全性，Redis 提供了 3 种刷盘机制：appendfsync always：主线程每次执行写操作后立即刷盘，此方案会占用比较大的磁盘 IO 资源，但数据安全性最高appendfsync no：主线程每次写操作只写内存就返回，内存数据什么时候刷到磁盘，交由操作系统决定，此方案对性能影响最小，但数据安全性也最低，Redis 宕机时丢失的数据取决于操作系统刷盘时机appendfsync everysec：主线程每次写操作只写内存就返回，然后由后台线程每隔 1 秒执行一次刷盘操作（触发fsync系统调用），此方案对性能影响相对较小，但当 Redis 宕机时会丢失 1 秒的数据
------
看到这里，我猜你肯定和大多数人的想法一样，选比较折中的方案 appendfsync everysec 就没问题了吧？这个方案优势在于，Redis 主线程写完内存后就返回，具体的刷盘操作是放到后台线程中执行的，后台线程每隔 1 秒把内存中的数据刷到磁盘中。这种方案既兼顾了性能，又尽可能地保证了数据安全，是不是觉得很完美？
------
著作权归@pdai所有
原文链接：https://pdai.tech/md/db/nosql-redis/db-redis-x-performance.html