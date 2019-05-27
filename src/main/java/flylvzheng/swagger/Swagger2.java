package flylvzheng.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
 * 创建时间：2019/2/20 下午2:21
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //可以使用多个区分不同的controller
                .groupName("模块")
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("flylvzheng"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("电子合同")
                //描述
                .description("Restful-API-Doc")
                //这里配置的是服务网站，我写的是我的博客园站点~欢迎关注~
                .termsOfServiceUrl("https://www.cnblogs.com/viyoung")
                //创建人 三个参数依次是姓名，个人网站，邮箱
                .contact(new Contact("esc", "http://www.xxx.cn", ""))
                //版本号
                .version("1.0.0")
                .build();
    }

}
