package kafka.Controller;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.Config.AliyunConfig;
import kafka.Config.KafkaConfig;
import kafka.Entity.regsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassNameregser
 * @Author ${张有鹏}
 * @Description
 * @Date 2020/12/9 19:46
 * @Param
 * @return
 */
@RestController
public class Regser {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private AliyunConfig aliyunConfig;
    @Autowired
    private KafkaConfig kafkaConfig;


    @PostMapping("/Kafka/verification")
    public Map verification(@RequestBody String username){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        try {
            String string = RandomUtil.randomString("0123456789", 6);//生成验证码

            //将对象转为JSON
            regsert regsert = new regsert(username,string);
            ObjectMapper objectMapper = new ObjectMapper();
            String string1 = objectMapper.writeValueAsString(regsert);

            // 调用kafka生产者
            kafkaConfig.kafkaProducer(string1);
            //调用消费者发送短信类-->>在测试类中永久运行
            //kafkaConfig.kafka();


            // 将验证码和用户名存入session中做注册判断
            httpSession.setAttribute("code", string);
            httpSession.setAttribute("username", username);

            objectObjectHashMap.put("success","True");
        }catch (Exception exception){
            objectObjectHashMap.put("success","False");
            throw new RuntimeException(exception);
        }
        return objectObjectHashMap;
    }


    @PostMapping("/Kafka/register")
    public Map register(@RequestBody String usernmae){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        try {
            System.out.println(usernmae);
            String[] split = usernmae.split("&");

            String[] split1 = split[0].split("=");
            String[] split2 = split[1].split("=");
            String[] split3 = split[2].split("=");
            String s2 = split1[1];//手机号
            String s3 = split2[1];//密码
            String s4 = split3[1];
            //System.out.println("s3 = " + s3);
            //System.out.println("s2 = " + s2);
            //System.out.println("s4 = " + s4);
            String code = (String) httpSession.getAttribute("code");
            String username = (String) httpSession.getAttribute("username");

            System.out.println("code = " + code);
            System.out.println("username = " + username);

            if (s4.equals(code)&&s2.equals(username)){
                objectObjectHashMap.put("register","True");
            }else {
                objectObjectHashMap.put("register", "TrueF");
            }
        }catch (Exception e) {
            objectObjectHashMap.put("register", "False");
            throw new RuntimeException(e);
        }
        return objectObjectHashMap;
    }






}
