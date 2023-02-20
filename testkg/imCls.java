package testkg;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class imCls {

    public static String dbPath = "d:/dbstore";

    public static void main(String[] args) throws Exception {
        // reg("woshish", "pwd123");
        login("ati", "pwd123");


        addFrdList("frd1");
        addFrdList("frd2");
        addFrdList("frd3");


        System.out.println(JSONObject.toJSONString(showFrdList()));


        addGrp("grp11");
        addGrp("grp12");

        System.out.println(JSONObject.toJSONString(showGrpList()));


        sendmsg("duifeo1", "msg11");
        sendmsg("duifeo1", "msg12222");
        sendmsg("duifeo2", "msg11");
        System.out.println(JSONObject.toJSONString(showChatMsg("duifeo1")));
        System.out.println(JSONObject.toJSONString(showChatMsg("duifeo2")));
        sendmsgToGrp("grp1","msgx......");
        System.out.println(JSONObject.toJSONString(showChatMsgGroup("grp1")));


    }

    private static void sendmsgToGrp(String grp1, String msg11) throws IOException {

        String curUname = "ati";     //aesDecode_cookie();
        String db = dbPath + "/" + curUname;
        String coll = "msg_grp_" + grp1;

        Map m = Maps.newConcurrentMap();
        m.put("msg", msg11);
        m.put("from", curUname);
        m.put("to", "_grp_"+grp1);
        m.put("time", new Date());
        m.put("addTime_rdable", getDatetimex());

        String msgFilename = new Date().toString().replaceAll(":", ".");
        FileUtils.writeStringToFile(

                new File(db + "/" + coll + "/" + msgFilename + ".txt"), JSONObject.toJSONString(m) + "\r\n"
        );
    }

    private static void sendmsg(String duifeo1, String msg11) throws IOException {
        String curUname = "ati";     //aesDecode_cookie();
        String db = dbPath + "/" + curUname;
        String coll = "msg_" + duifeo1;

        Map m = Maps.newConcurrentMap();
        m.put("msg", msg11);
        m.put("from", curUname);
        m.put("to", duifeo1);
        m.put("time", new Date());
        m.put("addTime_rdable", getDatetimex());

        String msgFilename = new Date().toString().replaceAll(":", ".")+" "+System.currentTimeMillis();
        FileUtils.writeStringToFile(

                new File(db + "/" + coll + "/" + msgFilename + ".txt"), JSONObject.toJSONString(m) + "\r\n"
        );

    }

    private static void addGrp(String grp12) throws IOException {

        String curUname = "ati";     //aesDecode_cookie();
        String db = dbPath + "/" + curUname;
        String coll = "grpLst";

        Map m = Maps.newConcurrentMap();
        m.put("grpname", grp12);
        m.put("addTime", new Date());
        m.put("addTime_rdable", getDatetimex());

        FileUtils.writeStringToFile(
                new File(db + "/" + coll + "/" + grp12 + ".txt"), JSONObject.toJSONString(m) + "\r\n"
        );
    }

    private static void addFrdList(String frd2) throws IOException {
        String curUname = "ati";     //aesDecode_cookie();
        String db = dbPath + "/" + curUname;
        String coll = "frdLst";

        Map m = Maps.newConcurrentMap();
        m.put("uname", frd2);
        m.put("addFrdTime", new Date());
        m.put("addFrdTime", new Date());


        String dttm = getDatetimex();
        m.put("addFrdTime_rdable", dttm);

        FileUtils.writeStringToFile(
                new File(db + "/" + coll + "/" + frd2 + ".txt"), JSONObject.toJSONString(m) + "\r\n"
        );


    }


    private static Object showChatMsgGroup(String grpName) {


        String curUname = "ati";     //aesDecode_cookie();
        String db = dbPath + "/" + curUname;
        String coll = "msg_grp_" + grpName;

        List li  = getListFrmCollpath(db + "/" + coll);


        //   System.out.println( JSONObject.toJSONString(li) );
        return li;
    }

    private static Object showChatMsg(String duifeoUname) {


        String curUname = "ati";     //aesDecode_cookie();
        String db = dbPath + "/" + curUname;
        String coll = "msg_" + duifeoUname;

        List li  = getListFrmCollpath(db + "/" + coll);


        //   System.out.println( JSONObject.toJSONString(li) );
        return li;
    }

    private static List showGrpList() {


        String curUname = "ati";     //aesDecode_cookie();
        String db = dbPath + "/" + curUname;
        String coll = "grpLst";

        List li = Lists.newArrayList();
        String collpath = db + "/" + coll;

        li = getListFrmCollpath(collpath);


        //  System.out.println( JSONObject.toJSONString(li) );
        return li;
    }



    private static Object showFrdList() throws IOException {

        String curUname = "ati";     //aesDecode_cookie();
        String db = dbPath + "/" + curUname;
        String coll = "frdLst";

        List li = getListFrmCollpath(db + "/" + coll);


        //   System.out.println( JSONObject.toJSONString(li) );
        return li;
    }

    private static void login(String uname, String pwdd) throws Exception {
        String tb = dbPath + "/" + uname + "/uinfo.txt";
        ReversedLinesFileReader reversedLinesReader = new ReversedLinesFileReader(new File(tb), Charset.defaultCharset());
        String uinfo_str = reversedLinesReader.readLine();
        JSONObject jo = JSONObject.parseObject(uinfo_str);
        if (!pwdd.equals(jo.get("pwd")))
            throw new RuntimeException("pwd err");

    }

    private static void reg(String uname, String pwd) throws Exception {
        if (new File(dbPath + "/" + uname).exists())
            throw new RuntimeException("alread reged");
        Map m = Maps.newConcurrentMap();
        m.put("uname", uname);
        m.put("pwd", pwd);
        FileUtils.writeStringToFile(
                new File(dbPath + "/" + uname + "/uinfo.txt"), JSONObject.toJSONString(m));
    }


    private static List getListFrmCollpath(String collpath) {
        List li;
        File[] lst_fls = new File(collpath).listFiles();
        li = Arrays.stream(lst_fls).map((File f) -> {
            try {
                String t = FileUtils.readFileToString(f);
                JSONObject jo = JSONObject.parseObject(t);
                return jo;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }).collect(Collectors.toList());
        return li;
    }


    private static String getDatetimex() {
        DateFormat df3 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);

        DateFormat df7 = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA);
        String date3 = df3.format(new Date());
        String time3 = df7.format(new Date());

        String dttm = date3 + " " + time3;
        return dttm;
    }
}

