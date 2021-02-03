package flylvzheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients
//@EnableSwaggerBootstrapUI//doc.html    /swagger-ui.html
@EnableAsync
public class SpringcloudLvzhengApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudLvzhengApplication.class, args);
    }
}
