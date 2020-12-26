package kafka.Config;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @ClassNamealiyun
 * @Author ${张有鹏}
 * @Description
 * @Date 2020/12/10 8:18
 * @Param
 * @return
 */
public class AliyunConfig {
    public void aliyun(String username, String string){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GAKz3gHifa4kBiRYY9E", "4oBTVd8ox4YhjoKVjaFkMTvp5oxxVg");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", username);
        request.putQueryParameter("SignName", "小阿鹏商城");
        request.putQueryParameter("TemplateCode", "SMS_206538359");
        request.putQueryParameter("TemplateParam", "{"+"code"+":"+string+"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
