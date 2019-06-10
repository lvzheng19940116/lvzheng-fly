package flylvzheng.static_value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
 * 创建时间：2019/1/21 下午2:52
 */


@Component
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource(value = "classpath:application.yml")
public class RedisConfig {
    private static String host;
    private static int port;
    private static String password;

    public static String getHost() {
        return host;
    }

    @Value("${spring.redis.host}")
    public void setHost(String host) {
        RedisConfig.host = host;
    }

    public static int getPort() {
        return port;
    }

    @Value("${spring.redis.port}")
    public void setPort(int port) {
        RedisConfig.port = port;
    }

    public static String getPassword() {
        return password;
    }

    @Value("${spring.redis.password}")
    public void setPassword(String password) {
        RedisConfig.password = password;
    }
}


