tmct embd 8



```
<!-- https://mvnrepository.com/artifact/xalan/serializer -->
<dependency>
    <groupId>xalan</groupId>
    <artifactId>serializer</artifactId>
    <version>2.7.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/xalan/xalan -->
<dependency>
    <groupId>xalan</groupId>
    <artifactId>xalan</artifactId>
    <version>2.7.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/javax.annotation/javax.annotation-api -->
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>1.3.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-core -->
<!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-core -->
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-core</artifactId>
    <version>8.0.53</version>
</dependency>
```

```
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;

public class Main {
    public static void main(String[] args) throws Exception {


        System.out.println("Hello world!");

        System.out.println(11);
        int port = 8080;


        Tomcat tomcat = new Tomcat();
        //  D:\im-server-core\im-biz\src\main\webapp
//            URL url = getClass().getClassLoader().getResource("spring-servlet.xml");
//            String pwd = StringUtils.substringBefore(url.getPath(), "/target/classes");


        tomcat.setBaseDir("D:\\im-server-core\\im-biz\\src\\main");
        tomcat.setPort(port);


        String contextPath = "/";
        String pwd = "D:\\im-server-core\\im-biz\\src\\main\\webappx".trim();
        tomcat.addWebapp(contextPath, pwd);
        tomcat.enableNaming();
        tomcat.start();
        tomcat.getServer().await();


    }
}
```

回报一些warning。不管他启动欧克。。



[Hello](http://localhost:8080/index.htm)