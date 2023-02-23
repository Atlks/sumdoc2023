netty svr clnt exmpl code

<!-- TOC -->

- [1. svr](#1-svr)
- [2. clnt code](#2-clnt-code)

<!-- /TOC -->

# 1. svr   


class MyBizHandler extends ChannelInboundHandlerAdapter {
    @Override   // messageReceived
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        String msg_str = byteBuf.toString(Charset.forName("utf-8"));
        System.out.println(new Date() + ": 服务端读到数据 -> " + msg_str);
        //here can save ctx chanel conn and uid...

        if (msg_str.trim().startsWith("{")) {
            JSONObject jo = JSONObject.parseObject(msg_str);
            NettyServer.connCtxMap.put(jo.get("uid"), ctx);
        }


        //if msg to other,forword to another ctx
        //  保留服务段 ctx线程
    }
}

public class NettyServer {

    public   static Map connCtxMap = new HashMap();


    public static void main(String[] args) {
               ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(new NioEventLoopGroup(),  new NioEventLoopGroup()).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new MyBizHandler());
            }
        });



        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            protected void initChannel(NioServerSocketChannel ch) {
                System.out.println("svr booting...服务端启动中");

            }
        });

        serverBootstrap
                //开启TCP底层心跳机制
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //开启Nagle算法，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                .childOption(ChannelOption.TCP_NODELAY, true);

        serverBootstrap.bind(8000);


    }


    public static void sendMsg(String msg,String connId) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);


                    ChannelHandlerContext ctx = (ChannelHandlerContext) connCtxMap.get(connId);

                    ByteBuf buffer = ctx.alloc().buffer();
                    buffer.writeBytes(("from svr msg" + new Date().toString()).getBytes());
                    ctx.channel().writeAndFlush(buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }



# 2. clnt code

class MyBizHandler33 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //conn finish ,,,save ctx_conn
        // channelActive  method after   connect()  ....
        System.out.println(" client conn ok");
        NettyClient.curConn_ctx = ctx;



        //save ctx for easy invlk outside
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        //接收服务端的消息并打印 rcv msg
        System.out.println(byteBuf.toString(Charset.forName("utf-8")));
    }

}

public class NettyClient {

    public   static ChannelHandlerContext curConn_ctx;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {


                             @Override
                             public void initChannel(SocketChannel ch) {
                                 ch.pipeline().addLast(new MyBizHandler33());
                             }
                         }

                );
        // 4.建立连接
        bootstrap.connect("127.0.0.1", 8000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");


            } else {
                System.err.println("连接失败!");
                //重新连接
            }
        });
    }
}


class sendMsgCls{
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("客户端发送消息...");
            // 1. 获取数据
            ByteBuf buffer =NettyClient. curConn_ctx.alloc().buffer();
            // 2. 准备数据，指定字符串的字符集为 utf-8
            Map m = Maps.newConcurrentMap();
            m.put("dt", new Date());
            m.put("uid", 2);
            byte[] bytes = JSONObject.toJSONString(m).getBytes();
            // 3. 填充数据到 ByteBuf
            buffer.writeBytes(bytes);
            // 2. 写数据
            NettyClient.curConn_ctx.channel().writeAndFlush(buffer);
        }).start();
    }
}