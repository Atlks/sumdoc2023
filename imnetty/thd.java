import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

public class thd {

    public static void main(String[] args) throws InterruptedException {

        new Thread( ()-> {
            System.out.println("22");
        }) .start();

        System.out.println("11");

        Map m = Maps.newConcurrentMap();
        m.put("dt", new Date());
        m.put("uid", 2);
      String s = JSONObject.toJSONString(m);
        JSONObject jo = JSONObject.parseObject(s);
        System.out.println(jo);
    }
}
