package flylvzheng.jdk8;

import flylvzheng.bean.Emp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author:LvZheng
 * Date:2018/7/5
 */


public class Jdk {
    public static void aaa() {
        //排序
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });
//        for (String name : names) {
//            System.out.println(name);
//        }
        //1.8
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        Collections.sort(
                names, (String a, String b) -> b.compareTo(a)
        );
        for (String name : names) {
            System.out.println(name);
        }


        List<Emp> list = Arrays.asList(
                new Emp()

        );

        Stream<Emp> stream = list.stream();//创建流

        stream
                .filter(e -> e.getEid() > 25)//过滤符合条件的流元素
                .limit(5)//只取5个
                .skip(4)//跳过4个
                .distinct()//去重，需重写hashcode和equals方法
                .forEach(System.out::println);//终止操作，获取流


        list.stream()
                .sorted((e1, e2) -> {//定制排序
                    if (e1.getEid() == e2.getEid()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getEid() - e2.getEid();
                    }
                }).forEach(System.out::println);
        /**
         * Stream的终止操作
         - allMatch – 检查是否匹配所有元素
         - anyMatch – 检查是否至少匹配一个元素
         - noneMatch – 检查是否没有匹配所有元素
         - findFirst – 返回第一个元素
         - count – 返回流中元素的总个数
         - max – 返回流中最大值
         - min – 返回流中最小值
         */
        List<Emp> emps = Arrays.asList(

        );
        boolean b = emps.stream()
                .allMatch(emp -> emp.getName().equals(emp.getName()));
        System.out.println(b);

        boolean b1 = emps.stream().
                anyMatch(emp -> emp.getName().equals(emp.getName()));
        System.out.println(b1);

        boolean b2 = emps.stream()
                .noneMatch(emp -> emp.getEid() > 16000);
        System.out.println(b2);

        Optional<Emp> any = emps.parallelStream()//并行流
                .findAny();
        System.out.println(any.get());

        /**
         * 需求1：
         *给定一个数字，如何返回一个有每个数的平方构成的列表？
         * 给定【1,2,3,4,5】，返回【1,4,9,16,25】
         */
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list2.stream().map(integer -> integer * integer).collect((Collectors.toList()));
        System.out.println(collect);

        /**
         * 需求2：
         * 用reduce和map数一数流中的元素个数
         */
        Optional<Integer> reduce = list2.stream()
                .map(e -> 1)//巧妙之处
                .reduce(Integer::sum);
        System.out.println(reduce);

        /**
         * 常用方法：
         - Optional.of(T t) : 创建一个Optional 实例
         - Optional.empty() : 创建一个空的Optional 实例
         - Optional.ofNullable(T t):若t 不为null,创建Optional 实例,否则创建空实例
         - isPresent() : 判断是否包含值
         - orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
         - orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s 获取的值
         - map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
         - flatMap(Function mapper):与map 类似，要求返回值必须是Optional
         */

    }

}
