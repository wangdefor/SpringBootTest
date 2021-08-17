package org.example.config;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Properties;

/**
 * @Classname ConsumerFastStart
 * @Description TODO
 * @Date 2021/2/25 18:40
 * @Created wangyong
 */
public class ConsumerFastStart {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RoundRobinAssignor.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test.group");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "www.kafka.com:9092");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Lists.newArrayList("test"));
        while (true){
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(1000));
            poll.forEach(l -> System.out.println("消费" + l.value()));
        }

    }
}
