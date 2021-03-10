package flylvzheng.es.client;

import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LvZheng
 * 创建时间：2021/3/10 上午11:13
 */
@Configuration
public class ESConfig {


    // 高版本客户端
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        // 创建Client连接对象
        String[] ips = {"127.0.0.1:9200"};
        HttpHost[] httpHosts = new HttpHost[ips.length];
        for (int i = 0; i < ips.length; i++) {
            httpHosts[i] = HttpHost.create(ips[i]);
        }
        RestClientBuilder builder = RestClient.builder(httpHosts);
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

}
