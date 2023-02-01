embd tmct8

<!-- TOC -->

- [addWebapp vs Context](#addwebapp-vs-context)
- [Container(容器)](#container容器)
- [三种Context的联系与区别](#三种context的联系与区别)
- [code](#code)

<!-- /TOC -->

Context 
# addWebapp vs Context

addwebapp invoke addcontext




# Container(容器)
Connector连接器负责外部交流，Container容器负责内部处理。也就是说连接器处理Socket通信和应用层协议的解析，得到ServletRequest，而容器则负责处理ServletRequest 。

Tomcat设计了4种容器: Engine、Host、Context、Wrapper ，这四种容器是父子关系。

Engine: 最顶层容器组件，可以包含多个Host。实现类为org.apache.catalina.core.StandardEngine
Host: 代表一个虚拟主机，每个虚拟主机和某个域名Domain Name相匹配，可以包含多个Context。实现类为org.apache.catalina.core.StandardHost
Context: 一个Context对应于一个Web 应用，可以包含多个Wrapper。实现类为org.apache.catalina.core.StandardContext
Wrapper: 一个Wrapper对应一个Servlet。负责管理 Servlet ，包括Servlet的装载、初始化、执行以及资源回收。实现类为org.apache.catalina.core.StandardWrapper


# 三种Context的联系与区别
Tomcat中有如下三种Context: ServletContext、StandardContext、ApplicationContext。下面这张图很好的展现了这三个Context的结构。
StandardContext

org.apache.catalina.Context接口的默认实现为StandardContext，而Context在Tomcat中代表一个web应用。ApplicationContext所实现的方法其实都是调用的StandardContext中的方法，StandardContext是Tomcat中真正起作用的Context。

获取StandardContext
向各种中间件和框架注入内存马的基础，就是要获得context，context实际上就是拥有当前中间件或框架处理请求、保存和控制servlet对象、保存和控制filter对象等功能的对象。

# code
 package com.im.action;

import com.im.action.login.LoginAction;
import com.im.action.sys.GetSmsCodeAction;
import com.im.filter.SignitureValidFilter;
import com.webkit.web.FilterDispatcher;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TmctEmbd {

    public static void main(String[] args) throws LifecycleException {
        System.out.println(11);
        int port = 8080;


        Tomcat tomcat = new Tomcat();
        tomcat.getHost().setAutoDeploy(true);
        tomcat.setPort(port);

        //-------------add ctx
        Context ctx_webapp = tomcat.addContext("/", "D:\\im-server-core\\im-biz\\target\\");
        //  addWEbapp 更麻烦解压缩war包会

        //-------add filter   SignitureValidFilter
        FilterDef filter1definition = new FilterDef();

        filter1definition.setFilterName(SignitureValidFilter.class.getSimpleName());
        filter1definition.setFilterClass(SignitureValidFilter.class.getName());
        filter1definition.addInitParameter("exceptURI", "/index.jsp,/tool/scheduleEvent");
        ctx_webapp.addFilterDef(filter1definition);
        FilterMap filter1mapping = new FilterMap();
        filter1mapping.setFilterName(SignitureValidFilter.class.getSimpleName());
        filter1mapping.addURLPattern("/*");
        ctx_webapp.addFilterMap(filter1mapping);


        ///------------add disptch flter
        FilterDef filter_disptch = new FilterDef();

        filter_disptch.setFilterName(com.webkit.web.FilterDispatcher.class.getSimpleName());
        filter_disptch.setFilterClass(com.webkit.web.FilterDispatcher.class.getName());

        ctx_webapp.addFilterDef(filter_disptch);
        FilterMap filter_disptch_map = new FilterMap();
        filter_disptch_map.setFilterName(FilterDispatcher.class.getSimpleName());
        filter_disptch_map.addURLPattern("/*");
        ctx_webapp.addFilterMap(filter_disptch_map);

        //-----------------------------api 2

        addSvlt(tomcat, ctx_webapp, "/im-biz/sys/getSmsCode", new GetSmsCodeAction());
 
        addSvlt(tomcat, ctx_webapp, "/im-biz/login/login", new LoginAction());
        //   ActionContext.getActionContext().


        //-------------------------------api1
        //http://localhost:8088/wa/snm
        HttpServlet httpServlet = new HttpServlet() {
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.getWriter().println("111");
            }
        };
        tomcat.addServlet(ctx_webapp, "snm", httpServlet);
        ctx_webapp.addServletMappingDecoded("/snm", "snm");//must last line


        //--------------------------------start

        tomcat.start();
        tomcat.getServer().await();
    }

    private static void addSvlt(Tomcat tomcat, Context ctx_webapp, String pattern_svltpath, AbstractAction act
    ) {
        String svltname = act.getSubClassName();
        tomcat.addServlet(ctx_webapp, svltname, new HttpServlet() {


            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                act.execute(resp.getOutputStream());

            }
        });

        ctx_webapp.addServletMappingDecoded(pattern_svltpath, svltname);
    }
}

/**
 * System.out.println(javax.annotation.security.DeclareRoles.class);
 * //  javax.annotation.security.DeclareRoles
 * System.out.println(javax.servlet.ServletContext.class);
 * System.out.println(org.apache.jasper.servlet.JspServlet.class);
 * System.out.println(org.apache.tomcat.util.descriptor.tld.TldParser.class);
 * System.out.println(javax.el.ExpressionFactory.class);
 * System.out.println(org.apache.el.ExpressionFactoryImpl.class);
 * System.out.println(org.apache.tools.ant.launch.AntMain.class);
 * //  tomcat.setBaseDir("D:\\im-server-core\\im-biz\\target");
 * <p>
 * <p>
 * //        String pwd = "D:\\im-server-core\\im-biz\\target".trim();
 * //        System.out.println(pwd);
 * //        tomcat.addWebapp("/", pwd);
 */
