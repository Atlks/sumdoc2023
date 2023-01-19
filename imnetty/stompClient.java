import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSession.Receiptable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;


public class stompClient {

    public sta

    public void main(String[] args) {

              WebSocketHttpHeaders headers = new WebSocketHttpHeaders();//请求头
          WebSocketStompClient client=null;//stomp客户端
          SockJsClient SockJsClient=null;//socket客户端
          ThreadPoolTaskScheduler Ttask=null;//连接池
          StompSession session=null;//连接会话


//连接到对应的endpoint点上，也就是建立起websocket连接


        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());

        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        //设置对应的解码器，理论支持任意的pojo自带转json格式发送，这里只使用字节方式发送和接收数据
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        stompClient.setReceiptTimeLimit(300);

        stompClient.setDefaultHeartbeat(new long[]{10000l,10000l});

        ThreadPoolTaskScheduler task=new ThreadPoolTaskScheduler();

        task.initialize();

        stompClient.setTaskScheduler(task);

        client=stompClient;
        SockJsClient=sockJsClient;
        Ttask=task;


        ListenableFuture<StompSession> f =stompClient.connect("stomp://DESKTOP-5QO1SBJ:61613", headers, new MyHandler(), "localhost", 8080);

            myClient.runStompClient(myClient,
                    "ws://localhost:8080/StompWithSSM/stomp",
                    "/topic/message","/app/send",sendMsg);


        while (!myClient.RecvFlag) {
            //持续等待返回标志位为true
            LOGGER.info("-------------------持续等待返回验证消息中……，当前flag:"+myClient.RecvFlag);
            Thread.sleep(3000);
        }
        //关闭所有连接终止程序
        myClient.Ttask.destroy();
        myClient.SockJsClient.stop();
        myClient.client.stop();
        myClient.session.disconnect();
    }
}
