package com.OlaElectric.Subscribers;
import com.OlaElectric.Constants.MessageType;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MqttSubscriber implements MqttCallback {

    private String subscribeType;

    public MqttSubscriber(String subscribeType){
        this.subscribeType = subscribeType;
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        if (subscribeType.equals(MessageType.TEXT.name())) {
            regularMessage(mqttMessage.getPayload());
        }

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    public void regularMessage(byte[] mqttMessage){
        System.out.println( new String(mqttMessage));
    }



}
