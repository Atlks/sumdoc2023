package testkg;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class zipT {
    static Logger logger = LoggerFactory.getLogger(IstTestFl.class);
    public static void main(String[] args) throws  Exception {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("zip2024" + UUID.randomUUID() + ".zip")));

        logger.info("=============start.....");
        for (int n = 0; n < 10 * 1; n++) {

            try {
                //创建压缩包里的文件
                zipOutputStream.putNextEntry(new ZipEntry( "rename.json"));

                zipOutputStream.write("txt".getBytes());    //将数据写入到压缩包里的文件里面
                zipOutputStream.closeEntry();


                //      FileUtils.writeStringToFile(new File("flds/dt2022"+ UUID.randomUUID() +".txt"),txt,true);

            } catch (Throwable e) {
                System.out.println(e);
            }

        }
        zipOutputStream.flush();
        zipOutputStream.close();
    }
}
