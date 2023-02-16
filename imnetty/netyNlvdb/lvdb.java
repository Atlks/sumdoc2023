package netyNlvdb;
import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.File;
import java.io.IOException;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
public class lvdb {

    public static void main(String[] args) throws IOException {

        Options options = new Options();
        options.createIfMissing(true);
        DB db = factory.open(new File("lvltest"), options);

                byte[] key = new String("key11" ).getBytes();
                byte[] value = new String("22value" ).getBytes();

                db.put(key, value);

            //    db.delete(key);


    }
}
