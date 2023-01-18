long conn design prcs pcd 长连接连接流程设计



啊第三方控件的思考

# stard prcs
setHandler(xxxHdler) 
bind(port)

// Create the acceptor  
        IoAcceptor acceptor = new NioSocketAcceptor();  
 // Attach the business logic to the server  
        acceptor.setHandler( new HelloWorldServerHandler() );  
 // And bind !  
        acceptor.bind( new InetSocketAddress(PORT) );  

 
# biz hadlr just recv msg...and send msg

public class HelloWorldServerHandler 
  @Override  
    public void messageReceived( IoSession session, Object message )
{


 session.write(helloWorld);  
 session.write(helloWorld);  

}

# mina d code is better easy under stand than netty...

如果看不懂代码可以看类似的lib代码可能更加简单 mina vs netty

