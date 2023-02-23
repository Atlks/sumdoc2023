

  logger.info("=============start.....");

        Stream<String> lines = Files.lines(Paths.get("dt217_900w.txt"));
        String line = lines.skip(886 * 10000).findFirst().get();
        System.out.println(line);


        logger.info("===================end.....");


        读取后一条需要1.5s,,一千万数据量

        月前面数据越快。。。

如何提升大文件读取，随机读取vs 分区模式


通过索引可以获取到主键，然后读取内部主键rowid_linenum，通过主键rowid_linenum去查询对应文件，使用随机读取acc很快的。。1ms定位


主键和linenum，起始idx，需要有个hash对应表即可。。

这样随机读取到某记录的其实idx，直接readline即可。。速度很快1ms

不需要分区模式了。。。。



那就需要分区了。。分片了。。

每100w为一片，可以快速到200ms读取最长。。。
10w为一片，则是80ms



索引hash，体积100w数据大概25M。。。1kw 250g,,  一个亿所以大概会2个G了。。。