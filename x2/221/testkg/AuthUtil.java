package testkg;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.input.ReversedLinesFileReader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;

import static testkg.ImUtil.writeStrToFilFrmObj;

public class AuthUtil {


    public static String storeFldr = "d:/dbstore_codelib";
    public static Route regv3() {
        return (req, res) -> {

            String uname = req.getParameter("uname");
            String pwd = req.getParameter("pwd");

            String accpath = storeFldr + "/" + "user_" + uname + ".json";
            if (new File(accpath).exists())
                throw new RuntimeException("alread reged");

            writeStrToFilFrmObj(accpath, new HashMap() {{
                put("uname", uname);

                put("pwd", pwd);
            }});
            return 0;
        };
    }

    public static Route loginv3() {
        return (req, res) -> {

            checkAuth(req);
            //gene token  uname,id,date,,exprise date
            //login dont need gene token,just use uname_pwd  as key to acc another api just ok..

            return  geneToken(req);
        };
    }

    public static Object geneToken(HttpServletRequest req) {
        // aes too rouble... base64 smple
        String curUname = req.getParameter("uname");
        String pwd =  req.getParameter("pwd");
        return new String( Base64.getEncoder().encode((curUname+":"+pwd).getBytes()) );
    }

    public static void checkAuth(HttpServletRequest req) throws Exception {
        checkAuth(req.getParameter("uname"),req.getParameter("pwd"));
    }


    public static void checkAuth(String uname, String pwd) throws Exception {

        // db/shititype
        String accpath = storeFldr + "/" + "user_" + uname + ".json";
        ReversedLinesFileReader reversedLinesReader = new ReversedLinesFileReader(new File(accpath), Charset.defaultCharset());
        String uinfo_str = reversedLinesReader.readLine();
        JSONObject jo = JSONObject.parseObject(uinfo_str);
        if (!pwd.equals(jo.get("pwd")))
            throw new RuntimeException("pwd err");
        //gene token  uname,id,date,,exprise date
        //login dont need gene token,just use uname_pwd  as key to acc another api just ok..


    }
}
