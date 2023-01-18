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