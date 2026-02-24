import com.lixiaoyue.JavaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;

@SpringBootTest(classes = JavaApplication.class)
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void hashTest() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        //存
        hashOperations.put("002", "name", "zhangsan");
        hashOperations.put("002", "age", "20");
        hashOperations.put("002", "addr", "beijing");

        //取
        Object age = hashOperations.get("002", "age");
//        System.out.println((String) age);

        //获取所有字段
        Set keys = hashOperations.keys("002");
        for (Object key : keys) {
//            System.out.println(key);
        }

        //获得hash结构中的所有值
        List values = hashOperations.values("002");
        for (Object value : values) {
            System.out.println(value);
        }
    }

}
