package flylvzheng.controller;

import flylvzheng.bean.Telephone;
import flylvzheng.repository.TelephoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 创建时间：2019/7/4 11:18 AM
 */
@Slf4j
@RestController
@RequestMapping("/phone")
public class TelephoneController {

    @Autowired
    private TelephoneRepository telephoneRepository;


    @GetMapping("/getall")
    public Object getall() {
        long l = System.currentTimeMillis();
        List<Telephone> all = telephoneRepository.findAll();
        long l1 = System.currentTimeMillis();
        long l2=l1 - l;
        log.info("时间:{}",l2);
        return all;
    }
}
