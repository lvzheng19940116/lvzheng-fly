package flylvzheng.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author LvZheng
 * 创建时间：2019/7/8 10:08 AM
 */
@RestController
@Slf4j
public class KafkaController {
    @Autowired
    private KafkaSender kafkaSender;
    @GetMapping("/kafka")
    public void send(){
        String string = LocalDateTime.now().toString();
        kafkaSender.send(string);
        log.info("send:{}",string);
    }
}
