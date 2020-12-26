package kafka;

import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.Config.AliyunConfig;
import kafka.Entity.regsert;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootTest
public class KafkaApplicationTests {

    @Test
    public void contextLoads() {
        String str = RandomUtil.randomString (4);
        System.out.println(str);
        String string = RandomUtil.randomString("0123456789", 6);
        System.out.println("string = " + string);
    }

    /**
     * 创建生产者
     */
    @Test
    public void CustomProducer() throws JsonProcessingException {
        // 1. 初始化参数信息
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        // ProducerConfig ：获取所需的一系列配置参数
        objectObjectHashMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hahzk30:9092");
        objectObjectHashMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        objectObjectHashMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 2. 创建生产者
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(objectObjectHashMap);

        regsert regsert = new regsert("16638505740","123465");
        //ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        String string1 = objectMapper.writeValueAsString(regsert);
        //3. 发送数据
        for (int i=0;i<10;i++){
            // KafkaProducer ：需要创建一个生产者对象，用来发送数据
            kafkaProducer.send(new ProducerRecord<>("topicb",string1), new Callback(){
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e == null){
                        System.out.println("发送成功："+recordMetadata.partition());//数据所在分区
                        System.out.println("发送成功："+recordMetadata.topic());//数据所对应的topic
                        System.out.println("发送成功："+recordMetadata.offset());//数据的offset
                    }
                }
            });
        }
        kafkaProducer.close();
    }

    @Autowired
    private AliyunConfig aliyunConfig;

    @Test
    public void ma() throws IOException {
        //1. 初始化配置信息
        Map<String,Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hahzk31:9092,hahzk32:9092");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        map.put(ConsumerConfig.GROUP_ID_CONFIG,"g3");

        /**
         * earliest 从最早的数据开始消费
         * 从最早的数据开始消费-->>前提
         *
         */
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //2. 创建Consumer
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer(map);
        //订阅 topic-user的数据
        kafkaConsumer.subscribe(Arrays.asList("topicb"));

        while (true){
            //3. 消费数据
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);// 多长时间取一次数据
            //Iterator<ConsumerRecord<String, String>> iterator = consumerRecords.iterator();
            for (ConsumerRecord consumerRecord : consumerRecords) {
                Object value = consumerRecord.value();
                System.out.println("value = " + value);

//                ObjectMapper objectMapper = new ObjectMapper();
//                regsert regsert = objectMapper.readValue((String) value, regsert.class);
//                //JSONObject.fromObject();
//                aliyunConfig.aliyun(regsert.getUsername(), regsert.getPassword());
            }
        }
    }


    @Test
    public void mb() throws JsonProcessingException {
        regsert regsert = new regsert("asd","asd");
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(regsert);

        System.out.println(string);

        kafka.Entity.regsert regsert1 = objectMapper.readValue(string, regsert.class);

        System.out.println("regsert1 = " + regsert1.getPassword()+regsert1.getUsername());
    }

}
