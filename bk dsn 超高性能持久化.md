bk dsn 超高性能持久化 BufferedWriter 

<!-- TOC -->

- [BufferedWriter](#bufferedwriter)
- [高性能持久化的关键就是内存buff ，批量flush](#高性能持久化的关键就是内存buff-批量flush)
    - [可以自定义时间flush刷盘啊厉害。。      bw.flush();](#可以自定义时间flush刷盘啊厉害------bwflush)
    - [其次没有结构直接str追加，所以更快，](#其次没有结构直接str追加所以更快)
- [table](#table)
- [持久化机制可以 每隔 几秒s持久化](#持久化机制可以-每隔-几秒s持久化)
- [ref](#ref)
- [code](#code)

<!-- /TOC -->


# BufferedWriter
# 高性能持久化的关键就是内存buff ，批量flush


Java BufferedWriter.write()输出文件
java中bufferedWriter.write()写入数据到文件中，由于缓冲区大小有限，需要调用flush方法及时刷新缓冲区，写文件的时候可以提高效率。


900w数据一秒钟。。800M了。。


## 可以自定义时间flush刷盘啊厉害。。      bw.flush();
## 其次没有结构直接str追加，所以更快，

，而rds需要lst结构追加。。


# table

| db | pef tms per sec|
|---|---|
|BufferedWriter| 800w ps|
|netty+bufile| 18w ps|
|rds| 4.5w ps|
| mysql mmr | 3w ps |
| mysql trx0 nochk| 3w ps |
| mgdb | 1.5w ps |
| file append | 1.5w ps |
| msql perf n mltThrdPool | 1.5w ps |
| mysql dft | 1k ps |


自己实现netty+bufferfile。。。。90w 5s...每秒18w dt
16m /s  /s /

netty+leavedb maybe better


# 持久化机制可以 每隔 几秒s持久化
高性能持久化的关键就是内存buff ，批量flush
BufferedWriter 可以自定义时间flush刷盘啊厉害。。      bw.flush();
其次没有结构直接str追加，所以更快，，而rds需要lst结构追加。。

# ref 

java文件写入的6种方法，性能最好是哪一种？_zhangkaixuan456的博客-CSDN博客

# code
package testkg;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;

public class IstTestFl {

    public static void main(String[] args) throws Exception {

        Logger logger = LoggerFactory.getLogger(IstTestFl.class);
        String txt="'aaaaaaaaaaaaaaaaaaaaaaaaadkjflkdsjfklasjfkld jfkdsajfkldsjfkldsjafklsdjafkljadkslfjdkl'\r\n";
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
        jedis.auth(" ");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());

        System.out.println(jedis.get("fgfkkk"));


        //1.创建BufferedWriter类型的对象与c:/a.txt文件关联，true代表可以追加，
        //写入流,设置缓存区大小为1024K
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dt2023_900w.txt",true)),10240);






        logger.info("=============start.....");
        for (int n = 0; n <900*10000; n++) {

            try {
             //   FileUtils.writeStringToFile(new File("dt2022.txt"),txt,true);
                //2.将字符串数据"last demo!"写入文件中
                bw.write(txt);
                bw.newLine();//输出换行，内部调用System.getProperty("line.separator")
            } catch (Throwable e) {
                System.out.println(e);
            }

        }


        //3.刷新缓存区
        bw.flush();
        //4.关闭流对象并释放有关的资源
        bw.close();

        logger.info("===================end.....");


    }
}
