dbuti useage 


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


      <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.26</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.11.0</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/commons-dbutils/commons-dbutils -->
        <dependency>
            <groupId>commons-dbutils</groupId>
            <artifactId>commons-dbutils</artifactId>
            <version>1.7</version>
        </dependency>