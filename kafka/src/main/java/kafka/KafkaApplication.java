package kafka;

import kafka.Config.AliyunConfig;
import kafka.Config.KafkaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }


    @Bean
    public AliyunConfig aliyunConfig(){
        AliyunConfig aliyunConfig = new AliyunConfig();
        return aliyunConfig;
    }
    @Bean
    public KafkaConfig kafkaConfig(){
        KafkaConfig kafkaConfig = new KafkaConfig();
        return kafkaConfig;
    }
}
