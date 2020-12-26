package kafka.Service;

import kafka.Config.AliyunConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassNameSercvicelet
 * @Author ${张有鹏}
 * @Description
 * @Date 2020/12/10 8:47
 * @Param
 * @return
 */
@Service
public class Sercvicelet {
    @Autowired
    private AliyunConfig aliyunConfig;
    @Async
    public String ma(){

        aliyunConfig.aliyun("1634865616","12346");

        return "asd";
    }
}
