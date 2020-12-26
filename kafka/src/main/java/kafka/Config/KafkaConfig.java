package kafka.Config;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;


public class KafkaConfig {

    public void kafkaProducer(String stringkafka){
        // 1. 初始化参数信息
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        // ProducerConfig ：获取所需的一系列配置参数
        objectObjectHashMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hahzk30:9092");
        objectObjectHashMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        objectObjectHashMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 2. 创建生产者
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(objectObjectHashMap);
        //3. 发送数据
        // KafkaProducer ：需要创建一个生产者对象，用来发送数据

        kafkaProducer.send(new ProducerRecord<>("topicb", stringkafka)/*, new Callback(){
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e == null){
                    System.out.println("发送成功："+recordMetadata.partition());//数据所在分区
                    System.out.println("发送成功："+recordMetadata.topic());//数据所对应的topic
                    System.out.println("发送成功："+recordMetadata.offset());//数据的offset
                }
            }
        }*/);
        kafkaProducer.close();

    }


    /*@Autowired
    private AliyunConfig aliyunConfig;

    public void kafka() throws IOException {
        //1. 初始化配置信息
        Map<String,Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hahzk31:9092,hahzk32:9092");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        map.put(ConsumerConfig.GROUP_ID_CONFIG,"g1");
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
                ObjectMapper objectMapper = new ObjectMapper();
                Regsert Regsert = objectMapper.readValue((String) value, Regsert.class);
                aliyunConfig.aliyun(Regsert.getUsername(), Regsert.getUsername());
            }
        }
    }*/

}
