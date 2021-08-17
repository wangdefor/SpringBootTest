package org.example.config;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Iterator;
import java.util.Map;

/**
 * @Classname ConsumerInterceptorTest
 * @Description TODO
 * @Date 2021/2/25 19:15
 * @Created wangyong
 */
public class ConsumerInterceptorTest implements ConsumerInterceptor<String,String> {

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
        while (iterator.hasNext()){
            ConsumerRecord<String, String> next = iterator.next();
            if(next.value().contains("好不好")){
                System.out.println("当前消息不合法");
                //iterator.remove();
            }
        }
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
