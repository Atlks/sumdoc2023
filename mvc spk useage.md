mvc spk useage

use raw resp out bytearr
ret    return resps.raw（）  just ok


package com.im.action;
import com.im.action.sys.GetSmsCodeAction;
import com.im.filter.SignitureValidFilter;
import com.webkit.web.FilterDispatcher;
import spark.Spark;

import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class spk {

    public static void main(String[] args) {
        Spark.exception(RuntimeException.class, (exception, request, response) -> {
            //exception.printStackTrace();
            throw exception;
        });
        port(8088);
        System.out.println(org.eclipse.jetty.server.Handler.class);
         System.out.println(org.slf4j.helpers.MessageFormatter.class);
     //   System.out.println( org.slf4j.impl.StaticLoggerBinder.class);
        get("/h", (req, res) -> "Hel..");
        get("/hh", (req, res) -> "Hel222.");



        post("/im-biz/sys/getSmsCode", (req, res) -> {


            HttpServletResponse resps=   res.raw();
            HttpServletRequest reqst=req.raw();


            addFltr( reqst,resps);
            new GetSmsCodeAction().execute(  resps.getOutputStream(), reqst);
            resps.flushBuffer();
           return resps;
        });


        // get("/hh", extracted());
        get("/p",

                (req, res) -> {

                    // HttpServletRequest req2 = (HttpServletRequest) req;
                    // spark.http.matching.RequestWrapper req2=req;
//							System.out.println(req2.getSession().getServletContext().getRealPath("/"));
//							;
                    HttpServletRequest req2 = req.raw();

                    String path = req2.getServletContext().getContextPath();
                    System.out.println(path);
                    String realPath = req2.getServletContext().getRealPath("/uploadFile");
                    System.out.println(realPath);
                    return 1;

                });



}

    private static void addFltr(HttpServletRequest reqst,HttpServletResponse resps  ) throws IOException, ServletException {
        SignitureValidFilter signitureValidFilter = new SignitureValidFilter();
        signitureValidFilter.exceptURI=            "/index.jsp,/tool/scheduleEvent";

        signitureValidFilter.doFilter(reqst, resps,new FilterChain(){

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {

            }
        });


        FilterConfig  fc=new FilterConfig() {
            @Override
            public String getFilterName() {
                return "fltnm";
            }

            @Override
            public ServletContext getServletContext() {
                return reqst.getServletContext();
            }

            @Override
            public String getInitParameter(String s) {
                if(s.equals("charset"))
                return "utf8";
                return "";
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }
        };
        FilterDispatcher filterDispatcher = new FilterDispatcher();
        filterDispatcher.init(fc);
        filterDispatcher.doFilter(reqst, resps,new FilterChain(){
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {

            }
        });
    }
}
