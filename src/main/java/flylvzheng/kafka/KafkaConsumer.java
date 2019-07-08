package flylvzheng.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author LvZheng
 * 创建时间：2019/7/8 10:04 AM
 */
@Component
@Slf4j
public class KafkaConsumer {

    /**
     * 有消息就读取
     * @param record
     */
    @KafkaListener(topics = {"lvzheng"})
    public void receiveMessage(ConsumerRecord<?, ?> record){

        long offset = record.offset();
        log.info("offset:{}",offset);
        log.info("consumer:{}",record.value());
    }
}

