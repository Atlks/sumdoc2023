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
        jedis.auth("kPrbMjTIrCcrcBl88Dsc");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());

        System.out.println(jedis.get("fgfkkk"));


        //1.创建BufferedWriter类型的对象与c:/a.txt文件关联，true代表可以追加，
        //写入流,设置缓存区大小为1024K
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dt2024_900w.txt",true)),10240);






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
