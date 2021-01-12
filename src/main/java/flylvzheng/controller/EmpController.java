package flylvzheng.controller;

import com.alibaba.fastjson.JSON;
import flylvzheng.bean.Emp;
import flylvzheng.bean.world.User;
import flylvzheng.exception.Code;
import flylvzheng.exception.MyException;
import flylvzheng.exception.Response;
import flylvzheng.form.EmpForm;
import flylvzheng.repository.EmpRepository;
import flylvzheng.service.Emp12Service;
import flylvzheng.service.EmpService;
import flylvzheng.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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


    @GetMapping("/redis")
    public void reids() {

    }

    @PostMapping("/save")
    public Object save() {
        Emp emp = new Emp();
        emp.setDate1(new Date());
        return emp;
    }

    /**
     * collection.stream().forEach() → collection.forEach()
     * collection.stream().collect(toList/toSet/toCollection()) → new CollectionType<>(collection)
     * collection.stream().toArray() → collection.toArray()
     * Arrays.asList().stream() → Arrays.stream() or Stream.of()
     * IntStream.range(0, array.length).mapToObj(idx -> array[idx]) → Arrays.stream(array)
     * IntStream.range(0, list.size()).mapToObj(idx -> list.get(idx)) → list.stream()
     * Collections.singleton().stream() → Stream.of()
     * Collections.emptyList().stream() → Stream.empty()
     * stream.filter().findFirst().isPresent() → stream.anyMatch()
     * stream.collect(counting()) → stream.count()
     * stream.collect(maxBy()) → stream.max()
     * stream.collect(mapping()) → stream.map().collect()
     * stream.collect(reducing()) → stream.reduce()
     * stream.collect(summingInt()) → stream.mapToInt().sum()
     * stream.mapToObj(x -> x) → stream.boxed()
     * stream.map(x -> {...; return x;}) → stream.peek(x -> ...)
     * !stream.anyMatch() → stream.noneMatch()
     * !stream.anyMatch(x -> !(...)) → stream.allMatch()
     * stream.map().anyMatch(Boolean::booleanValue) -> stream.anyMatch()
     * IntStream.range(expr1, expr2).mapToObj(x -> array[x]) -> Arrays.stream(array, expr1, expr2)
     * Collection.nCopies(count, ...) -> Stream.generate().limit(count)
     * stream.sorted(comparator).findFirst() -> Stream.min(comparator)
     *
     *  parallelStream和Stream
     *   size = 10 串行代码执行速度是并行的8倍
     *   size = 100 速度相等
     *   size=10000 并行是串行的2.5倍
     */

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



        //分组求和
        Map<String, BigDecimal> mapRec = all.stream().collect(Collectors.groupingBy(Emp::getEffort, Collectors.reducing(BigDecimal.ZERO, Emp::getMoney, BigDecimal::add)));
        mapRec.forEach((a, b) -> {
            log.info("根据门派去重复{}", a + b);
        });
        //一个集合变成两个集合 满足条件的是true
        Map<Boolean, List<Emp>> listMap = all.stream().collect(Collectors.partitioningBy(a -> a.getEffort() != null));
        //求最小
        Emp emp = all.stream().collect(Collectors.maxBy(Comparator.comparing(a -> a.getEid()))).get();

        Emp emp1 = all.stream().max(Comparator.comparing(Emp::getEid)).get();
        //求平均值
        Double averagingInt = all.stream().collect(Collectors.averagingInt(a -> a.getEid()));
        //字符串拼接
        all.stream().map(Emp::getEffort).collect(Collectors.joining(","));

        all.stream().parallel().map(a->a.getEffort()).collect(Collectors.toList());

        //根据某个字段去重复
        List<Emp> empList = all.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Emp::getEffort))), ArrayList::new));
        log.info("根据门派去重复{}", JSON.toJSONString(empList));
        //并行
        empList.parallelStream().collect(Collectors.toList());

        return map;
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
