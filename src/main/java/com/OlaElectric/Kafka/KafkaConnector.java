package com.OlaElectric.Kafka;

import com.OlaElectric.Configuration.KafkaConfiguration;
import com.OlaElectric.Constants.MessageType;
import com.OlaElectric.Subscribers.KafkaSubscriber;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


public class KafkaConnector<U,V> {

    private KafkaConfiguration kafkaConfiguration;

    public KafkaConnector(KafkaConfiguration configuration){
        this.kafkaConfiguration = configuration;
    }

    static Logger logger;

    public void consume(){
        KafkaSubscriber subscriber = null;
        if (kafkaConfiguration.getSubscribeType().equals(MessageType.TEXT.name())) {
            subscriber = new KafkaSubscriber<String>();
        } else {
            subscriber = new KafkaSubscriber<byte[]>();
        }
        KafkaConsumer kafkaConsumer = null;
        try{
            kafkaConsumer = new KafkaConsumer<U,V>(kafkaProperties());

            List topics = new ArrayList();
            topics.add(kafkaConfiguration.getTopic());

            kafkaConsumer.subscribe(topics);

            while (true){
                ConsumerRecords<U,V> records = kafkaConsumer.poll(10);
                for (ConsumerRecord<U,V> record: records){
                    logger.info("<-------------------- this is the receiver printing --------------->");

                    subscriber.printText(record.value());

                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
