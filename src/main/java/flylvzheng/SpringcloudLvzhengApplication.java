package flylvzheng;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients
@EnableSwagger2
@EnableSwaggerBootstrapUI//doc.html    /swagger-ui.html
public class SpringcloudLvzhengApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudLvzhengApplication.class, args);
    }
}
