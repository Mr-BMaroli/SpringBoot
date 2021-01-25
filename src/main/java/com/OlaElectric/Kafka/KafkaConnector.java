package com.OlaElectric.Kafka;

import com.OlaElectric.Configuration.KafkaConfiguration;
import com.OlaElectric.Constants.MessageType;
import com.OlaElectric.Subscribers.KafkaSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class KafkaConnector<U,V> {

    private KafkaConfiguration kafkaConfiguration;

    public KafkaConnector(KafkaConfiguration configuration){
        this.kafkaConfiguration = configuration;
    }

    public void consume(){
        KafkaSubscriber subscriber = new KafkaSubscriber<V>();
        KafkaConsumer kafkaConsumer = null;
        try{
            kafkaConsumer = new KafkaConsumer<U,V>(kafkaProperties());

            List topics = new ArrayList();

            topics.add(kafkaConfiguration.getTopic());

            kafkaConsumer.subscribe(topics);
            System.out.println("<------------------KAFKA Connection is successful and is Subscribing----------------------->");
            while (true){
                ConsumerRecords<U,V> records = kafkaConsumer.poll(10);
                for (ConsumerRecord<U,V> record: records){
                    System.out.println("<------------------Message Received----------------------->");
                    subscriber.printText(record.value());
                }
            }
        }catch (Exception e){
            System.out.println("An Exception has occured!");
            e.printStackTrace();
        }finally {
            kafkaConsumer.close();
        }
    }

    public Properties kafkaProperties(){
        Properties properties = new Properties();

        properties.put("bootstrap.servers", kafkaConfiguration.getHost());

        properties.put("key.deserializer",kafkaConfiguration.getStringSerialiser());

        if (kafkaConfiguration.getSubscribeType().equals(MessageType.TEXT.name())) properties.put("value.deserializer",kafkaConfiguration.getStringSerialiser());
        else properties.put("value.deserializer",kafkaConfiguration.getByteArraySerialiser());

        properties.put("group.id",kafkaConfiguration.getGroupID());

        return properties;
    }




}
