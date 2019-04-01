package flylvzheng.restupload;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * 以动手实践为荣,以只看不练为耻.
 * 以打印日志为荣,以出错不报为耻.
 * 以局部变量为荣,以全局变量为耻.
 * 以单元测试为荣,以手工测试为耻.
 * 以代码重用为荣,以复制粘贴为耻.
 * 以多态应用为荣,以分支判断为耻.
 * 以定义常量为荣,以魔法数字为耻.
 * 以总结思考为荣,以不求甚解为耻.
 *
 * @author LvZheng
 * 创建时间：2019/3/12 下午6:22
 */
@Component
public class MadpUploadUtil {


    public String upload(String token, File file) {
        String url = "http://madp.lenovo.cn/v1/tenants/lenovo/apps/BBFCF62349554B518EB5CFB4C052B791/service/image/uploadFile";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "multipart/form-data;charset=UTF-8");
        headers.add("accessToken", token);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        FileSystemResource resource = new FileSystemResource(file);
        map.add("image", resource);
        HttpEntity<MultiValueMap<String, Object>> httpHeaders = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpHeaders, String.class);
        String body = exchange.getBody();
        return body;

    }
}
