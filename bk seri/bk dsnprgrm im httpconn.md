bk dsnprgrm im httpconn

http conn 
rds mq

        get("/m", (req, res) -> {

            //连接本地的 Redis 服务
            Jedis jedis = new Jedis("localhost");
            // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
            jedis.auth("xxx");
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