package flylvzheng.jdk8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toCollection;

/**
 * @author LvZheng
 * 创建时间：2020/5/26 12:29 下午
 */
public class FunctionInterface {

    public static void main(String[] args) {

        //parallelSort方法对数组进行排序，这方法与传统的sort()方法大致相似，该方法的功能与传统的sort()方法大致相似
        //只是在多cpu上有更好的性能
        int[] arr1 = new int[]{3, -4, 25, 16, 30, 18};
        //对数组进行并发排序
        Arrays.parallelSort(arr1);
        System.out.println(Arrays.toString(arr1));


        int[] arr2 = new int[]{3, -4, 25, 16, 30, 18};
        Arrays.parallelPrefix(arr2, new IntBinaryOperator() {

            public int applyAsInt(int left, int right) {
                //left代表数组中前一个索引处的元素，计算第一个元素时，left为1
                //right代表数组中当前索引处的元素
                return left * right;
            }
        });
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = new int[5];
        Arrays.parallelSetAll(arr3, new IntUnaryOperator() {

            public int applyAsInt(int operand) {
                //operand代表正在计算的元素的索引
                return operand * 5;
            }
        });
        System.out.println(Arrays.toString(arr3));


        // add();
        int n = 5;
        double[] values = {1, 2, 3};
        double[] sums = Arrays.copyOf(values, values.length);
        System.out.println(JSON.toJSONString(sums));

        Arrays.parallelPrefix(sums, Double::sum);
        System.out.println(JSON.toJSONString(sums));

        Arrays.parallelSetAll(sums, a -> a + 3);
        System.out.println(JSON.toJSONString(sums));

        Arrays.parallelSort(sums);
        System.out.println(JSON.toJSONString(sums));

        int start = n - 1;
        double[] doubles = IntStream.range(start, sums.length).mapToDouble(i -> {
            System.out.println(i);
            double prfix = i == start ? 0 : sums[i - n];
            System.out.println("---" + prfix);
            return (sums[i] - prfix) / n;
        }).toArray();
        System.out.println(JSON.toJSONString(doubles));

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
