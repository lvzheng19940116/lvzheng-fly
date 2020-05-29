package flylvzheng.jdk8;

import com.google.common.collect.Lists;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import com.sun.xml.internal.rngom.ast.builder.GrammarSection;

import javax.validation.constraints.Max;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author LvZheng
 * 创建时间：2020/5/26 12:29 下午
 */
public class FunctionInterface {

    public static void main(String[] args) {
        add();
    }

    public static void add() {
        /**
         *  第10页
         */

        Predicate<Integer> predicate = x -> x > 5;
        System.out.println(predicate.test(5));
        BinaryOperator<Long> binaryOperator = (x, y) -> x + y;
        System.out.println(binaryOperator.apply(3L, 5L));


        ArrayList<String> strings = Lists.newArrayList("a", "b");
        TreeSet<String> treeSet = strings.stream().collect(toCollection(TreeSet::new));
        //根据条件分组
        Map<Boolean, List<String>> a1 = strings.stream().collect(partitioningBy(a -> a.equals("a")));
        //todo 若key对应的value为空，会将第二个参数的返回值存入并返回
        List<String> strings1 = a1.computeIfAbsent(false, k -> Lists.newArrayList("c"));
//        a2.computeIfPresent("",k->new Object());
        a1.forEach((key, value) -> {
            System.out.println(key + "----" + value);
        });

        Map<Boolean, List<String>> a2 = strings.stream().collect(groupingBy(a -> a.equals("a")));


        String collect = strings.stream().collect(joining(",", "[", "]"));
        System.out.println(collect);
        // todo 取出List中所有人的姓名放到一个新的List中去
//        List<String> userNames = listUser.stream().collect(Collectors.mapping((user)->{return user.getName();}, Collectors.toList()));
//        List<String> userNames = listUser.stream().collect(Collectors.mapping(User::getName, Collectors.toList()));
        //  strings.stream().collect(groupingBy(a -> a.equals("a"),mapping("",toList())));
        new StringJoiner("");


    }
}
