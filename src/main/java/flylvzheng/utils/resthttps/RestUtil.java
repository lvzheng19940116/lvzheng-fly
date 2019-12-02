package flylvzheng.utils.resthttps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author wangmengLong
 * ECC冻结or解冻
 */
@Slf4j
@Component
public class RestUtil {



    private String rest(String uri,String partnerRoleCode,String tenantId){
        //将请求头和请求体进行拼接
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        requestHeaders.add("tenantId",tenantId);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        log.info("调用ECC冻结代理接口,冻结代理编号为:{}", partnerRoleCode);
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        String result = restTemplate.postForObject(uri, requestEntity, String.class);

        return "";
    }
}
