package testkg;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class IstTestFl {

    public static void main(String[] args) throws Exception {
      //  rd_lastLine();
          wrt();
      //  rd();

    }

    private static void rd_lastLine() throws IOException {
     // very fast ,mayr use radnm seek ftuncthm,,only need 3ms
        Logger logger = LoggerFactory.getLogger(IstTestFl.class);
        String txt = "aaaaaaaaaaaaaaaaaaaaaaaaadkjflkdsjfklasjfkld jfkdsajfkldsjfkldsjafklsdjafkljadkslfjdkl";

        logger.info("=============start.....");
        String line="";

        ReversedLinesFileReader reversedLinesReader = new ReversedLinesFileReader(new File("dt217_900w.txt"), Charset.defaultCharset());
        System.out.println(reversedLinesReader.readLine());




        logger.info("===================mid.....");
        Stream<String> lines = Files.lines(Paths.get("dt217_900w.txt"));
        line = lines.skip(899 * 10000).findFirst().get();
        System.out.println(line);


        logger.info("===================end.....");


    }

    private static void rd() throws IOException {

        Logger logger = LoggerFactory.getLogger(IstTestFl.class);
        String txt = "aaaaaaaaaaaaaaaaaaaaaaaaadkjflkdsjfklasjfkld jfkdsajfkldsjfkldsjafklsdjafkljadkslfjdkl";

        logger.info("=============start.....");
        String line="";


        RandomAccessFile raf=new RandomAccessFile("dt217_900w.txt", "r");
        //获取RandomAccessFile对象文件指针的位置，初始位置是0
      //  System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer());
        raf.seek(886 * 10000*92);//移动文件指针位置
        System.out.println(raf.readLine());System.out.println(raf.readLine());

       logger.info("===================mid.....");
//        int n = -1;
//        LineIterator iterator = FileUtils.lineIterator(new File("dt217_900w.txt"), "UTf-8");
//        while (iterator.hasNext()) {
//            n++;
//            if (n < 886 * 10000)
//            {
//                iterator.nextLine(); continue;
//            }
//
//            else {
//                  line = iterator.nextLine();
//                System.out.println(line);
//                break;
//            }
//        }
        logger.info("===================mid.....");
        Stream<String> lines = Files.lines(Paths.get("dt217_900w.txt"));
        line = lines.skip(899 * 10000).findFirst().get();
        System.out.println(line);


        logger.info("===================end.....");


    }

    private static void wrt() throws IOException {
        Logger logger = LoggerFactory.getLogger(IstTestFl.class);
        String txt = "aaaaaaaaaaaaaaaaaaaaaaaaadkjflkdsjfklasjfkld jfkdsajfkldsjfkldsjafklsdjafkljadkslfjdkl";
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
        jedis.auth("kPrbMjTIrCcrcBl88Dsc");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());

        System.out.println(jedis.get("fgfkkk"));


        //1.创建BufferedWriter类型的对象与c:/a.txt文件关联，true代表可以追加，
        //写入流,设置缓存区大小为1024K
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dt217_900w.txt", true)), 1024);


        logger.info("=============start.....");
        for (int n = 0; n < 900 * 10000; n++) {

            try {
                //   FileUtils.writeStringToFile(new File("dt2022.txt"),txt,true);
                //2.将字符串数据"last demo!"写入文件中
                bw.write(n + txt);
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
