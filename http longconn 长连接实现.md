http longconn 长连接实现

原理 使用     CountDownLatch1.await(); 来阻塞读取



也可使用线程notify等方法

一、前言 主流的Web端即时通讯方案大致有4种：传统Ajax短轮询、Comet技术、WebSocket技术、SSE（Server-sent Events）。  Ajax短轮询	Comet	WebSocket	SSE 概念	http端轮询是服务器收到请求不管是否有数据都直接响应 http 请求; 浏览器受到 http 响应隔一段时间在发送同样的http 请求查询是否有数据;	

http 长轮询是服务器收到请求后如果有数据, 立刻响应请求; 如果没有数据就会 hold 一段时间,这段时间内如果有数据立刻响应请求; 如果时间到了还没有数据, 则响应 http 请求;浏览器受到 http 响应后立在发送一个同样http 请求查询是否有数据;	

WebSocket的实现了一次连接，双方通信的功能。首先由客户端发出WebSocket请求，服务器端进行响应，实现类似TCP握手的动作。这个连接一旦建立起来，就保持在客户端和服务器之间，两者之间可以直接的进行数据的互相传送。

​	在 sse 的场景下，客户端发起请求，连接一直保持，服务端有数据就可以返回数据给客户端，这个返回可以是多次间隔的方式。sse 是单通道，只能服务端向客户端发消息 协议	http	http	wbSocket	 http  短轮询	长轮询	  二、SpringBoot实现本文来自：【皇冠云】 链接地址：https://www.idchg.com/info/302899/

# [调用者阻塞直到 getFoo() 准备好一个值？] 



`wait()`而且`notifyAll()`我有 95% 的机会做对  一般来说 notify() 和 notifyAll() 是你想要的方法

[`CountDownLatch`](http://java.sun.com/javase/6/docs/api/java/util/concurrent/CountDownLatch.html)则 a 非常适合。

使用 中的任何一个[`BlockingQueue`](http://java.sun.com/j2se/1.5.0/docs/api/java/util/concurrent/BlockingQueue.html)更`java.util.concurrent`具体地说

您可以使用 wait() 和 notify() 方法：

```
public class longconnHttp {
      static CountDownLatch CountDownLatch1 ;

    public static void main(String[] args) throws Exception {


        Spark.exception(RuntimeException.class, (exception, request, response) -> {
            //exception.printStackTrace();
            throw exception;
        });
        port(8888);
        get("/h", (req, res) -> "Hel..");
        get("/hh", (req, res) -> "Hel222.");
        // get("/hh", extracted());
        get("/longconn",

                (req, res2) -> {
                    CountDownLatch1= new CountDownLatch(1);
                    HttpServletRequest req2 = req.raw();


                  //  return "my txt";

                 return getMq();
                });
```

```
CountDownLatch


import org.eclipse.paho.client.mqttv3.*;

import java.util.concurrent.CountDownLatch;

public        class Handler_Http {

    CountDownLatch CountDownLatch1 ;
    Object curmsg;
    public Object getMq() throws  Exception {

        CountDownLatch1=new CountDownLatch(1); ;

        //configure MQTT connection
        final MqttConnectOptions options = new MqttConnectOptions();
//        options.setUserName("<>/<>");
//        options.setPassword("<>".toCharArray());
        final MqttClient client = new MqttClient("tcp://localhost:1883", "MqttClient.generateClientId22", null);

        //connect to the Cumulocity
        client.connect(options);

        MqttMessage message = new MqttMessage();
        message.setPayload("Hello world from Java".getBytes());
        //    client.publish("topic123", message);

        client.subscribe("topic123");
        client.setCallback( new MqttCallback(){
            public void connectionLost(Throwable throwable) {
                System.out.println("Connection to MQTT broker lost!");
            }

            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                String msg= new String(mqttMessage.getPayload());
                System.out.println("Message received:\n\t"+ msg);

                curmsg=msg;
                CountDownLatch1.countDown();
            }

            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                // not used in this example
            }
        } );
        CountDownLatch1.await();
        //阻塞获取某个变量
        client.disconnect();
        return curmsg;
    }
}

```