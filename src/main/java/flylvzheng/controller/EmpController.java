package flylvzheng.controller;

import flylvzheng.bean.Emp;
import flylvzheng.bean.world.User;
import flylvzheng.exception.Code;
import flylvzheng.exception.MyException;
import flylvzheng.exception.Response;
import flylvzheng.form.EmpForm;
import flylvzheng.repository.EmpRepository;
import flylvzheng.service.Emp12Service;
import flylvzheng.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
 * 创建时间：2019/4/23 上午10:09
 */
@RestController
@RequestMapping("/emp")
@Slf4j
public class EmpController {


    @Autowired
    private EmpRepository empRepository;

    @Qualifier("emp1ServiceImpl")
    @Autowired
    private Emp12Service empService1;

    @Qualifier("emp2ServiceImpl")
    @Autowired
    private Emp12Service empService2;


    @Autowired
    private EmpService empService;


    @PostMapping("/save")
    public Object save() {
        Emp emp = new Emp();
        emp.setDate1(new Date());
        return emp;
    }

    @GetMapping("/list")
    public Object getAll(@RequestBody EmpForm empForm) {

        Page<Emp> all = empService.findAll(empForm);
        List<Emp> content = all.getContent();

        //多添件过滤
        List<Emp> collect = content.stream().filter(emp -> emp.getEffort().equals("") && emp.getAge().equals("")).collect(Collectors.toList());
        // 取某个字段
        List<String> list = content.stream().map(Emp::getEffort).collect(Collectors.toList());

        //返回新的对象
        List<User> users = content.stream().map(emp -> {
            User user = new User();
            user.setHobby(emp.getEffort());
            return user;
        }).collect(Collectors.toList());

        //根据条件取多少条
        List<Emp> emps = content.stream().filter(emp -> emp.getEffort().equals(""))
                .limit(5)
                .collect(Collectors.toList());

        //list to  map
        Map<String, String> string = all.stream().collect(Collectors.toMap(Emp::getEffort, Emp::getEffort
                , (key1, key2) -> key1));

        //  list to map  value是对象
        Map<String, User> map = all.stream().collect(Collectors.toMap(Emp::getEffort, emp -> {
            User user = new User();
            user.setHobby(emp.getEffort());
            return user;
        }, (key1, key2) -> key1));


        return all;
    }

    @GetMapping("/emp")
    public Response<?> get() {

        List<Emp> bySectIsNotNull = empRepository.findBySectIsNotNull();

        Emp emp = empRepository.findById(1).orElseThrow(() -> new MyException("id not ex"));
        bySectIsNotNull.add(emp);
        Response<List<Emp>> response = new Response<>();
        response.setCode(Code.SUCCESSED);
        response.setResult(bySectIsNotNull);
        return response;

    }

    @GetMapping("str")
    public Page<Emp> string() {
//        //多态
//        Emp12Service e=new Emp1ServiceImpl();
//        Emp12Service e2=new Emp2ServiceImpl();
//
//        String list = empService1.list();
//        String list1 = empService2.list();
//        return list + list1;

        PageRequest pageRequest = PageRequest.of(0,
                1);
        Page<Emp> aa = empRepository.findAllByName(pageRequest, "吕正");
        log.info("start");
        return aa;
    }


    @GetMapping("desc")
    public Emp desc() {

        return empRepository.findFirstByOrderByAgeDesc();
    }

}
