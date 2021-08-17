package org.example.config;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ProducerFastStart
 * @Description ProducerFastStart
 * @Date 2021/2/25 15:28
 * @Created wangyong
 */
public class ProducerFastStart {

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "www.kafka.com:9092");
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyDefaultPartitioner.class);
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String,String> record = new ProducerRecord<>("test","kafka-demo","你到底好不好");

        for (int i = 0; i < 1000; i++) {
            producer.send(record, (metadata, exception) -> {
                if(exception == null){
                    System.out.println("发送成功");
                }
            });
            TimeUnit.SECONDS.sleep(1);
        }
        producer.close();
    }
}
