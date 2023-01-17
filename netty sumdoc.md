netty sumdoc

<!-- TOC -->

- [1. channelHandler的生命周期](#1-channelhandler的生命周期)
- [总体思想 ，登陆后 客户端保留 conn,,,同时给服务的发个msg ，服务的收到后save conn和uid对应表](#总体思想-登陆后-客户端保留-conn同时给服务的发个msg-服务的收到后save-conn和uid对应表)
- [保存conn供外部使用](#保存conn供外部使用)
- [消息粘连解决](#消息粘连解决)

<!-- /TOC -->


我们知道一个连接对应一个channel，这个channel的所有处理逻辑在一个ChannelPipeline对象里，就是上图中的pipeline，这是它的对象名。然后这个对象里面是一个双向链表结构，每个节点是一个ChannelHandlerContext对象。这个对象能拿到与channe


# 1. channelHandler的生命周期

　　我们在重写ChannelInboundHandlerAdapter或者SimpleChannelInboundHandler里的方法的时候，只用到了读和Active，其他一大堆没用上的方法是干嘛的？现在就一一说明一下，这些方法运作的整个过程，我们可以理解为这个channelHandler的生命周期。以ChannelInboundHandlerAdapter为例，SimpleChannelInboundHandler继承于ChannelInboundHandlerAdapter，所以也差不多，个别方法名不一样而已。下面的API，从上到下，就是触发的顺序。

　　handlerAdded():逻辑处理器被添加后触发。

　　channelRegistered():channel绑定到线程组后触发。

　　channelActive():channel准备就绪，或者说连接完成。 相当于conn方法

　　channelRead()：channel有数据可读。    读取conn chanl 事件触发。。。

　　channelReadComplete()：channel某次数据读完了。

　　channelInactive()：channel被关闭

　　channelUnregistered()：channel取消线程的绑定

　　handlerRemoved()：逻辑处理器被移除。

   System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();



client .conn() is first exe,,then chanelact,,then read meth
  channelActive  方法先执行，一旦链接完成。。。      
  channelRead  读取数据后执行

#  总体思想 ，登陆后 客户端保留 conn,,,同时给服务的发个msg ，服务的收到后save conn和uid对应表 

  # 保存conn供外部使用

  客户端  在登录uid在开启conn后，发送一个msg过去，表明当前uid


     public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {


                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                //conn finish ,,,save ctx_conn
                                // channelActive  method after   connect()  ....
                                System.out.println(" client conn ok");
                                curConn_ctx = ctx;

                                new Thread(() -> {
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }

                                    System.out.println("客户端发送消息...");
                                    // 1. 获取数据
                                    ByteBuf buffer = curConn_ctx.alloc().buffer();
                                    // 2. 准备数据，指定字符串的字符集为 utf-8
                                    Map m = Maps.newConcurrentMap();
                                    m.put("dt", new Date());
                                    m.put("uid", 2);
                                    byte[] bytes = JSONObject.toJSONString(m).getBytes(Charset.forName("utf-8"));
                                    // 3. 填充数据到 ByteBuf
                                    buffer.writeBytes(bytes);
                                    // 2. 写数据
                                    curConn_ctx.channel().writeAndFlush(buffer);
                                }).start();

                                //save ctx for easy invlk outside
                            }


服务段读取消息后保存到map中conn 和 uid对应表


  ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {


                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                String msg_str = byteBuf.toString(Charset.forName("utf-8"));
                                System.out.println(new Date() + ": 服务端读到数据 -> " + msg_str);
                                //here can save ctx chanel conn and uid...

                                if(msg_str.trim().startsWith("{")){
                                    JSONObject jo = JSONObject.parseObject(msg_str);
                                    connCtxMap.put(jo.get("uid"),ctx);
                                }


                                //if msg to other,forword to another ctx
                                //  保留服务段 ctx线程
                            }
                        });
                    }



测试外部异步方法可以调用conn——ctx                    


#  消息粘连解决

加分隔符，表明消息边界即可。。

回车换行模式，使用字符串flush模式，，强制刷新cache

消息头模式不好复杂。。
