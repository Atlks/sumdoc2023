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
