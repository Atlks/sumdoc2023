package testkg;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import static testkg.ImUtil.*;
import static testkg.TmctUtil.*;
import static testkg.AuthUtil.*;


public class imCls_codlib {
    //shiti user msg grp

    public static String storeFldr = "d:/dbstore_codelib";


    public static void main(String[] args) throws Exception {

        AuthUtil.storeFldr=storeFldr;
         String v= new String( Base64.getEncoder().encode(("curUname:pwd").getBytes()) );

        Tomcat tomcat = getTomcat(8888);

        Context ctx_webapp = tomcat.addContext("/", "D:\\im-server-core\\im-biz\\target\\");


        // http://localhost:8888/reg?uname=ati&pwd=ppp
        setGetM("/reg", regv3(), tomcat, ctx_webapp);

        // http://localhost:8888/login?uname=ati&pwd=ppp
        setGetM("/login", loginv3(), tomcat, ctx_webapp);

        // http://localhost:8888/login?uname=ati&pwd=ppp
        setGetM("/addFrd", addFrdv2(), tomcat, ctx_webapp);
        setGetM("/frdLst", frdLst(), tomcat, ctx_webapp);

        setGetM("/sendmsgToFrd", sendmsgToFrd(), tomcat, ctx_webapp);
        setGetM("/showChatMsgWithFrd", showChatMsgWithFrd(), tomcat, ctx_webapp);

        setGetM("/sendmsgToGrp", sendmsgToGrp(), tomcat, ctx_webapp);
        setGetM("/showChatMsgFrmGrp", showChatMsgFrmGrp(), tomcat, ctx_webapp);

        tomcat_start_await(tomcat);





    }



    private static Route showChatMsgFrmGrp() {


        return (req, res) -> {



            String coll = "msg_grpmsg_" +  req.getParameter("grp")+"/"+getDate();



            String accpath = storeFldr + "/" + coll ;
            return getListFrmCollpath(   accpath   );



        };
    }

    private static Route showChatMsgWithFrd() {



  //        /// ajax can trans   ?accpath=msgs_$cur_wz_frd_msgs|msgs_frd_wz_$cur_msgs
        return (req, res) -> {

            String curUname = req.getParameter("uname");     //aesDecode_cookie();

            checkAuth(req);

            String frd = req.getParameter("frd");


         return getListFrmCollpath(      wzFrdMsgDir(curUname, frd));



        };
    }

    private static Route sendmsgToFrd() {




        return (req, res) -> {

            String curUname = req.getParameter("uname");     //aesDecode_cookie();

            checkAuth(req);


            String toFrd = req.getParameter("frd");


            // send and rvcv same msg dir..
            String coll_dir = wzFrdMsgDir(curUname, toFrd);
            String msgFilename = "msg_" + filNameTimebase();
            String accpath =coll_dir+ "/" + msgFilename + ".json";

            writeStrToFilFrmObj(accpath,new HashMap(){{

                put("from", curUname);
                put("to", toFrd);
                put("msg", req.getParameter("msg"));
                put("time", new Date());
                put("addTime_rdable", getDatetimex());

            }});
return 0;

        };
    }



    private static String wzFrdMsgDir(String curUname, String toFrd) {


        String dir=storeFldr + "/msgs_" + toFrd + "_wz_"+ curUname +"_msgs";

        return new File((dir)).exists()?dir:storeFldr + "/msgs_" + curUname + "_wz_"+ toFrd +"_msgs";
    }

    private static Route frdLst() {

     /// ajax can trans   ?accpath=user_&curuser_frds
        return (req, res) -> {

            checkAuth(req);

        String accpath = storeFldr + "/" + "user_" + req.getParameter("uname") + "_frds";
            return    getListFrmCollpath(accpath);
        };


    }


    private static Route addFrdv2() {
        return (req, res) -> {


            String curUname = req.getParameter("uname");     //aesDecode_cookie();

            checkAuth(req);

            //add rlt ...is use jo obj txt too troulbe  ,,fldr mode is better easy

            String frdName = req.getParameter("frd");
            String accpath = storeFldr + "/user_" + curUname + "_frds" + "/" + frdName + ".json";

            return  writeStrToFilFrmObj(accpath, new HashMap() {{

                put("uname", frdName);
                put("addFrdTime", new Date());
                put("addFrdTime_rdable", getDatetimex());

            }});
        };
    }



    private static Route sendmsgToGrp() throws IOException {



        return (req, res) -> {


            String curUname = req.getParameter("uname");     //aesDecode_cookie();

            checkAuth(req);

            //add rlt ...is use jo obj txt too troulbe  ,,fldr mode is better easy

            String grp1 = req.getParameter("grp");
            String msgFilename = "msg_" +filNameTimebase();

            String coll = "msg_grpmsg_" + grp1+"/"+getDate();
            String accpath = storeFldr + "/" + coll + "/" + msgFilename + ".json";



            return  writeStrToFilFrmObj(accpath, new HashMap() {{

                put("msg", req.getParameter("msg"));
                put("from", curUname);
                put("to", "_grp_" + grp1);
                put("time", new Date());
                put("addTime_rdable", getDatetimex());

            }});
        };
    }



    private static void addGrp(String grp12) throws IOException {

        String curUname = "ati";     //aesDecode_cookie();


        Map m = Maps.newConcurrentMap();
        m.put("grpname", grp12);
        m.put("addTime", new Date());
        m.put("addTime_rdable", getDatetimex());

        String accpath = storeFldr + "/" + curUname + "/grpLst" + "/" + grp12 + ".txt";
        FileUtils.writeStringToFile(new File(accpath), JSONObject.toJSONString(m) + "\r\n");
    }



    private static List showGrpList() {


        String curUname = "ati";     //aesDecode_cookie();

        String coll = "grpLst";
        List li = Lists.newArrayList();
        String accpath = storeFldr + "/" + curUname + "/" + coll;
        li = getListFrmCollpath(accpath);
        //  System.out.println( JSONObject.toJSONString(li) );
        return li;
    }




}

