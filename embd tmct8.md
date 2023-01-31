embd tmct8



package com.im.action;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class t {

    public static void main(String[] args) throws LifecycleException {
        System.out.println(11);
        int port = 8088;


        Tomcat tomcat = new Tomcat();
        tomcat.getHost().setAutoDeploy(true);
        tomcat.setPort(port);
        StandardContext ctx_webapp = new StandardContext();
        ctx_webapp.setPath("/");
        ctx_webapp.addLifecycleListener(new Tomcat.FixContextListener());

        tomcat.getHost().addChild(ctx_webapp);

        //-------------------------------api1
      //http://localhost:8088/wa/snm
        HttpServlet httpServlet = new HttpServlet() {
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.getWriter().println("111");
            }
        };
        tomcat.addServlet(ctx_webapp, "snm", httpServlet);
        ctx_webapp.addServletMappingDecoded("/snm", "snm");//must last line


        //-----------------------------api 2
        tomcat.addServlet(ctx_webapp, "snm2",  new HttpServlet() {
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.getWriter().println("222");
            }
        });
        ctx_webapp.addServletMappingDecoded("/snm2", "snm2");//must last line


        //--------------------------------start

        tomcat.start();
        tomcat.getServer().await();
    }
}

/**
 *
 *
 System.out.println(javax.annotation.security.DeclareRoles.class);
 //  javax.annotation.security.DeclareRoles
 System.out.println(javax.servlet.ServletContext.class);
 System.out.println(org.apache.jasper.servlet.JspServlet.class);
 System.out.println(org.apache.tomcat.util.descriptor.tld.TldParser.class);
 System.out.println(javax.el.ExpressionFactory.class);
 System.out.println(org.apache.el.ExpressionFactoryImpl.class);
 System.out.println(org.apache.tools.ant.launch.AntMain.class);
 *   //  tomcat.setBaseDir("D:\\im-server-core\\im-biz\\target");
 *
 *
 //        String pwd = "D:\\im-server-core\\im-biz\\target".trim();
 //        System.out.println(pwd);
 //        tomcat.addWebapp("/", pwd);
 */
