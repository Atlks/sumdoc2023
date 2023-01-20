import spark.Spark;

import javax.servlet.http.HttpServletRequest;

import static spark.Spark.get;
import static spark.Spark.port;


public class longconnHttp {


    public static void main(String[] args) throws Exception {


        Spark.exception(RuntimeException.class, (exception, request, response) -> {
            //exception.printStackTrace();
            throw exception;
        });
        port(8888);
        get("/h", (req, res) -> "Hel..");
        get("/hh", (req, res) -> "Hel222.");
        // get("/hh", extracted());
        get("/longconn",

                (req, res2) -> {

                    HttpServletRequest req2 = req.raw();


                  //  return "my txt";

                 return new Handler_Http_mqtt().getMq();
                 //getMq();
                });



    }




}
