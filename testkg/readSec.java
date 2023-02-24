package testkg;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.UUID;
import java.util.stream.Stream;

public class readSec {

    public static void main(String[] args) throws Exception {
//        byte[] sectorBytes = readDiskSector("d:/", 1024, 512);
//        System.out.println(JSONObject.toJSONString(sectorBytes));


        Logger logger = LoggerFactory.getLogger(IstTestFl.class);
        String txt = "aaaaaaaaaaaaaaaaaaaaaaaaadkjflkdsjfklasjfkld jfkdsajfkldsjfkldsjafklsdjafkljadkslfjdkl\r\n";

        logger.info("=============start.....");

        //通过 RandomAccessFile 得到，"rw" 代表可读可写
    //    RandomAccessFile randomAccessFile = new RandomAccessFile("500w.txt", "rw");

      //  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("log"+UUID.randomUUID()+".txt", true)), 1024);
        FileOutputStream fileOutputStream1 = new FileOutputStream("aFile.txt");
        BufferedOutputStream buf = new BufferedOutputStream(fileOutputStream1);
        FileChannel channel2 = null;
        //=buf.getChannel();  /// 500w 10s     50w /s  so slow..
        //  BufferedOutputStream speed is more fater chanel model...logn time is also 200w per sec...   perf..

     //   rdmfile 500w8s
        for (int n = 0; n < 500 * 10000; n++) {

            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(txt.getBytes().length);

                byteBuffer.put(txt.getBytes());
                byteBuffer.flip();
                channel2.write(byteBuffer);
            } catch (Throwable e) {
                System.out.println(e);
            }

        }

        channel2.close();

        logger.info("===================end.....");
    }

    /**
     * 读取磁盘或TF卡指定扇区
     *
     * @param device 设备，如/dev/sda
     * @param sector 扇区号
     * @param size   扇区大小，字节
     * @return 扇区内容
     */
    public static byte[] readDiskSector(String device, int sector, int size) throws Exception {
        byte[] sectorBytes = null;
        FileChannel fc = null;
        try {
            Path fp = Paths.get(device);
            fc = FileChannel.open(fp, EnumSet.of(StandardOpenOption.READ));
            ByteBuffer buffer = ByteBuffer.allocate(size);
            fc.read(buffer, sector * size);
            fc.close();
            sectorBytes = buffer.array();
        } finally {
            if (fc != null)
                fc.close();
        }
        return sectorBytes;
    }

}
