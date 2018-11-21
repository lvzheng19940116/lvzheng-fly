package flylvzheng.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
 * 创建时间：2018/10/30 下午2:31
 */
@RestController
public class MongoController {

    @Autowired
    private  MongoDORepository mongoDORepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/mongo")
    public void save(){
        MongoDO mongoDO=new MongoDO("12345","第三页","吕正添加","郝雪云","www.aaa.com");
       // mongoTemplate.save(mongoDO);
        mongoDORepository.save(mongoDO);
    }
    @PostMapping("/getmongo")
    public List<MongoDO> get(){

        List<MongoDO> all = mongoDORepository.findAll();
        for (MongoDO mongoDO : all) {
            String title = mongoDO.getTitle();
            System.out.println(title);
        }
        return all;
    }
}
