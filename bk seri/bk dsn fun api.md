bk dsn fun api


   setGetM( tomcat, ctx_webapp ,"/m",  (req, res) -> {
       //连接本地的 Redis 服务
       Jedis jedis = new Jedis("localhost");
       // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
       jedis.auth("df");
       System.out.println("连接成功");
       //查看服务是否运行
       System.out.println("服务正在运行: " + jedis.ping());

       System.out.println(jedis.get("fgfkkk"));

//while(true){
       Object msgx = jedis.brpoplpush("msgx", "msgxHstry", 10);

       String msg = JSONObject.toJSONString(msgx);
       System.out.println(msg);

       return msg;
   });


    private static void setGetM(Tomcat tomcat, Context ctx_webapp, String pattern_svltpath ,  Route route
                                ) {
        String svltname =  UUID.randomUUID().toString();
        tomcat.addServlet(ctx_webapp, svltname, new HttpServlet() {

            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {


                    resp.getWriter().write(  (String) route.handle( req,  resp));
                    resp.getWriter().flush();

            }

        });

        ctx_webapp.addServletMappingDecoded(pattern_svltpath, svltname);
    }
}

package testkg;

import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Route {

    /**
     * Invoked when a request is made on this route's corresponding path e.g. '/hello'
     *
     * @param request  The request object providing information about the HTTP request
     * @param response The response object providing functionality for modifying the response
     * @return The content to be set in the response
     * @throws java.lang.Exception implementation can choose to throw exception
     */
    Object handle(HttpServletRequest req, HttpServletResponse resp)  ;

}



package spark;

/**
 * Created by Per Wendel on 2014-05-10.
 */
@FunctionalInterface
public interface Route {

    /**
     * Invoked when a request is made on this route's corresponding path e.g. '/hello'
     *
     * @param request  The request object providing information about the HTTP request
     * @param response The response object providing functionality for modifying the response
     * @return The content to be set in the response
     * @throws java.lang.Exception implementation can choose to throw exception
     */
    Object handle(Request request, Response response) throws Exception;

}
