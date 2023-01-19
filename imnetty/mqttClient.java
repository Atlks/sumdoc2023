import org.eclipse.paho.client.mqttv3.*;

public class mqttClient {
    public static void main(String[] args) throws MqttException, InterruptedException {


        //configure MQTT connection
        final MqttConnectOptions options = new MqttConnectOptions();
//        options.setUserName("<>/<>");
//        options.setPassword("<>".toCharArray());
        options.setCleanSession(false);
        final MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId(), null);

        //connect to the Cumulocity
        client.connect(options);

        MqttMessage message = new MqttMessage();
        message.setPayload("Hello world from Java".getBytes());
         client.publish("topic123", message);

      String  QUEUE_NAME="/queue/que888";
        client.subscribe(QUEUE_NAME);
        client.subscribe("topic888");
        client.setCallback( new SimpleMqttCallBack() );
    //
        Thread.sleep(5000);client.disconnect();

    }
}
  class SimpleMqttCallBack implements MqttCallback {

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message received:\n\t"+ new String(mqttMessage.getPayload()) );
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // not used in this example
    }
}