package im;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

public class btchimp_sql {
  //  set global max_allowed_packet = 20*1024*1024*10
    public static void main(String[] args) throws Exception {

        String rootDir = "D:\\sqls";


        // 使用 `Files.walk()` 方法
        Files.walk(Paths.get(rootDir))
                .filter(Files::isRegularFile)
                .forEach(f -> {
                    //  int idx =f.toString().lastIndexOf("\\");

                    System.out.println(f);


                    try {
                        String sqls = null;
                        String fname = f.getFileName().toString();
                        String db = getdb(fname);

                    //    String cmd="C:\xampp\mysql\bin\mysql.exe -uroot -proot -Dim_admin <  D:\sqls\admin\im_admin_admin_log.sql"
                        String cmd="C:\\xampp\\mysql\\bin\\mysql.exe -uroot -proot -D@dbname <  "+f;
                        cmd=cmd.replaceAll("@dbname",db);
                        System.out.println(cmd);
                        FileUtils.writeStringToFile(new File("imp.bat"),cmd+"\r\n","utf8",true);
                        Runtime.getRuntime().exec(cmd);
                      //  sqls = FileUtils.readFileToString(new File(f.toString()), "utf8");
                     //    btchimp(db, sqls);
                    } catch (Throwable e) {
                        System.out.println(e.getMessage());
                    }


                });

        System.out.println(11);
    }

    private static void btchimp(String db, String sqls) throws Exception {
        System.out.println("====>db:" + db);
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/test?allowMultiQueries=true&max_allowed_packet=50M";

        String USER = "root";
        String PASS = "root";
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        QueryRunner queryRunner = new QueryRunner();
        String[] sqls_a=sqls.split(";");
        if(!db.equals("im_admin"))
            return;
       for   (String sql:sqls_a) {
           sql=sql.trim();
            try{
                queryRunner.execute(conn, sql);
            }catch(Throwable e)
            {
                System.out.println(e);
            }

        }


    }

    private static String getdb(String fname) {
        int idx = fname.indexOf("_");
        idx = fname.indexOf("_", idx + 1);
        String dbname = fname.substring(0, idx);
        return dbname;
    }
}
