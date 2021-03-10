package flylvzheng.redis.stock;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author LvZheng
 * 创建时间：2021/3/10 下午4:37
 */
@Data
public class RedisLock {

    private RedisTemplate redisTemplate;
    private String key;


    public RedisLock() {

    }

    public RedisLock(RedisTemplate redisTemplate, String key) {
        this.redisTemplate = redisTemplate;
        this.key = key;
    }

    public Boolean tryLock() {
        return true;
    }

    public void unlock() {
    }

}
