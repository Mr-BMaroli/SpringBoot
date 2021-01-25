package com.OlaElectric.Qa_util;

import com.OlaElectric.Configuration.KafkaConfiguration;
import com.OlaElectric.Configuration.MqttConfiguration;
import com.OlaElectric.Constants.ConnectorType;
import com.OlaElectric.Constants.MessageType;
import com.OlaElectric.Kafka.KafkaConnector;
import com.OlaElectric.Mqtt.MqttConnector;
import org.eclipse.paho.client.mqttv3.MqttException;


public class Main {

    public static void main(String[] args) throws MqttException, InterruptedException {

        String source = args[0];

        if(source.equals(ConnectorType.MQTT.name())){
           System.out.println("<------------------ Source selected is MQTT----------------------->");
            MqttConfiguration configuration = new MqttConfiguration();
            configuration.setHost(args[1]);
            configuration.setTopic(args[2]);
            configuration.setSubscribeType(args[3]);
            configuration.setUserName(args[4]);
            configuration.setPassWord(args[5]);
            connectToMqtt(configuration);
        }
        else if (source.equals(ConnectorType.KAFKA.name())){
            System.out.println("<------------------ Source selected is KAFKA----------------------->");
            KafkaConfiguration configuration = new KafkaConfiguration();
            configuration.setHost(args[1]);
            configuration.setTopic(args[2]);
            configuration.setSubscribeType(args[3]);
            configuration.setStringSerialiser("org.apache.kafka.common.serialization.StringDeserializer");
            configuration.setByteArraySerialiser("org.apache.kafka.common.serialization.ByteArrayDeserializer");
            configuration.setGroupID("test-group_001");
            connectToKafka(configuration);
        }
        else {
            System.out.println("<------------------ Wrong Source selected----------------------->");
        }

    }


    public static void connectToMqtt(MqttConfiguration configuration) throws MqttException, InterruptedException {
        System.out.println("<------------------Starting MQTT Connection----------------------->");
        MqttConnector mqttConnection = new MqttConnector(configuration);
        mqttConnection.connect();
        System.out.println("<------------------Subscribing Mqtt messages----------------------->");

        while(true){
            mqttConnection.subscribe();
            Thread.sleep(45000);
        }
    }

    public static void connectToKafka(KafkaConfiguration configuration){
        System.out.println("<------------------Starting KAFKA Connection----------------------->");
        KafkaConnector kafkaConnector = null;
        if (configuration.getSubscribeType().equals(MessageType.TEXT.name())){
             kafkaConnector = new KafkaConnector<String,String>(configuration);
        } else {
            kafkaConnector = new KafkaConnector<String,byte[]>(configuration);
        }
        System.out.println("<------------------IGNORE THE SLF4J MESSAGES----------------------->");
        System.out.println("<------------------Subscribing KAFKA messages----------------------->");
        kafkaConnector.consume();
    }


}
