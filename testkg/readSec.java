package testkg;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class readSec {

    public static void main(String[] args) throws Exception {
//        byte[] sectorBytes = readDiskSector("d:/", 1024, 512);
//        System.out.println(JSONObject.toJSONString(sectorBytes));


        //通过 RandomAccessFile 得到，"rw" 代表可读可写
        RandomAccessFile randomAccessFile = new RandomAccessFile("1834.txt", "rw");
        FileChannel channel2 = randomAccessFile.getChannel();


        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byte[] bs = "s123456789".getBytes();
        byteBuffer.put(bs);
        byteBuffer.flip();
        channel2.write(byteBuffer);
        channel2.close();
    }

    /**
     * 读取磁盘或TF卡指定扇区
     * @param device 设备，如/dev/sda
     * @param sector 扇区号
     * @param size 扇区大小，字节
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
        }
        finally {
            if(fc != null)
                fc.close();
        }
        return sectorBytes;
    }

}
