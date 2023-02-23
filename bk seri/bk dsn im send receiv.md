bk dsn im send receiv


<!-- TOC -->

- [im 整体架构](#im-整体架构)
- [消息发送http send   werite to rdcs](#消息发送http-send---werite-to-rdcs)
    - [client use api](#client-use-api)
    - [api       sendmsg](#api-------sendmsg)
- [http long conn recv](#http-long-conn-recv)
    - [client  利用http长连接获取http消息通知，如有消息立即返回，否则维持连接等待](#client--利用http长连接获取http消息通知如有消息立即返回否则维持连接等待)
    - [recv api   利用redis list 阻塞式api brpoplpush  读取信息](#recv-api---利用redis-list-阻塞式api-brpoplpush--读取信息)
- [可靠性设计 自动化资源释放与定时心跳重试](#可靠性设计-自动化资源释放与定时心跳重试)

<!-- /TOC -->


# im 整体架构

使用http 长连接+ redis来实现。。简单方便。。性能扩展方便。。。也可同时入库到mongodb更加强大的查询功能。。。



# 消息发送http send   werite to rdcs

## client use api 
 

                String content = EntityUtils.toString(HttpClients.createDefault().execute(new HttpGet("http://localhost:8080/sendmsg?msg="+new DateTime())).getEntity(), "UTF-8");


                System.out.println("========>lst len:"+content);
       

     


## api       sendmsg  

 setGetM( "/sendmsg", (req, res) -> {
            //连接本地的 Redis 服务
            Jedis jedis = new Jedis("localhost");
            // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
            jedis.auth("mmmm");
            System.out.println("连接成功");
            //查看服务是否运行
            System.out.println("服务正在运行: " + jedis.ping());

            System.out.println(jedis.get("fgfkkk"));

//while(true){
            Object list_len = jedis.lpush("msgx",req.getParameter("msg"));


            System.out.println(list_len);

            return list_len;
        },tomcat, ctx_webapp);



# http long conn recv




## client  利用http长连接获取http消息通知，如有消息立即返回，否则维持连接等待


 public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {

            Thread.sleep(20);
            try {


    String content = EntityUtils.toString(HttpClients.createDefault().execute(new HttpGet("http://localhost:8080/rcv")).getEntity(), "UTF-8");


                System.out.println(content);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        

## recv api   利用redis list 阻塞式api brpoplpush  读取信息

brpoplpush用来交换消息到历史记录表msgxHstry。。后记也可通过历史记录表来查询持久化消息。。
为了消息通知，所以msgx这个list只是暂时存储消息。。

  
    setGetM( "/rcv", (req, res) -> {
            //连接本地的 Redis 服务
            Jedis jedis = new Jedis("localhost");
            // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
            jedis.auth("mmm");
            System.out.println("连接成功");
            //查看服务是否运行
            System.out.println("服务正在运行: " + jedis.ping());

            System.out.println(jedis.get("fgfkkk"));

 
            Object msgx = jedis.brpoplpush("msgx", "msgxHstry", 10);

            String msg = JSONObject.toJSONString(msgx);
            System.out.println(msg);

            return msg;
        },tomcat, ctx_webapp);


# 可靠性设计 自动化资源释放与定时心跳重试

设置brpoplpush 超时10s返回。。增强了可靠些。以防长连接占据过长时间连接，消耗资源。。