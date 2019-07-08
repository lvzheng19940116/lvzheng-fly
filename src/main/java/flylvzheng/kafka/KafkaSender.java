package flylvzheng.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author LvZheng
 * 创建时间：2019/7/8 10:03 AM
 */
@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息到kafka
     */
    public void send(String message) {
        kafkaTemplate.send("lvzheng", message);
    }
}
