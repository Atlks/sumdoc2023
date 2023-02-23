java mqtt





```
import org.eclipse.paho.client.mqttv3.*;

public class mqttClient {
    public static void main(String[] args) throws MqttException {


        //configure MQTT connection
        final MqttConnectOptions options = new MqttConnectOptions();
//        options.setUserName("<>/<>");
//        options.setPassword("<>".toCharArray());
        final MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId(), null);

        //connect to the Cumulocity
        client.connect(options);

        MqttMessage message = new MqttMessage();
        message.setPayload("Hello world from Java".getBytes());
    //    client.publish("topic123", message);

        client.subscribe("topic123");
        client.setCallback( new SimpleMqttCallBack() );
     //   client.disconnect();

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
```