package flylvzheng.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

/**
 * @author LvZheng
 * @date 2021/1/14
 */
public class Arrays8 {

    /**
     * 单线程：
     * 1.binarySearch:查找数组中为 key 的 下标 ：binarySearch 二分法查找，数组必须有序，且存在此数组中，否则返回负数下标
     * Arrays.binarySearch(Object[] a,Object key):int
     * Arrays.binarySearch(Object[] a, int fromIndex, int toIndex,Object key):int
     * 
     * 2.copyOf：复制出新的数组，复制长度由 newLength 决定,长度可大于被复制数组的长度
     * <p>
     * 　copyOfRange：复制指定下标范围内的值
     * <p>
     * copyOf(T[] original, int newLength):T[]
     * copyOfRange(T[] original, int from, int to):T[]
     * 
     * 3.sort：默认进行升序排序，可自定义排序
     * <p>
     * sort(Object[] a):void
     * sort(Object[] a, int fromIndex, int toIndex):void
     * sort(T[] a, Comparator<? super T> c):void
     * sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c):void
     * 
     * 4.toString：数组toString
     * <p>
     * deepToString：二维数组toString
     * <p>
     * toString(Object[] a):String
     * deepToString(Object[] a):String
     * 
     * 5.equals：比较一维数组内容是否相等
     * <p>
     * deepEquals：比较二维数组内容是否相等
     * <p>
     * equals(Object[] a, Object[] a2)
     * deepEquals(Object[] a1, Object[] a2)
     * 
     * 6.fill：将一个数组全部置为 val ，或在下标范围内将数组置为 val
     * <p>
     * fill(Object[] a, Object val):void
     * fill(Object[] a, int fromIndex, int toIndex, Object val):void
     * 
     * 7.setAll：一个数组全部做表达式操作
     * <p>
     * setAll(T[] array, IntFunction<? extends T> generator):void
     * 
     * 8.stream：将数组转为流式，对array进行流式处理，可用一切流式处理的方法
     * <p>
     * stream(T[] array)：Stream<T>
     * 
     * 多线程：
     * <p>
     * 9.parallelPrefix：二元迭代，对原数组内容进行二元操作
     * <p>
     * parallelPrefix(T[] array, BinaryOperator<T> op):void
     * parallelPrefix(T[] array, int fromIndex, int toIndex,BinaryOperator<T> op):void
     * 
     * 10.parallelSetAll：一个数组全部做表达式操作
     * <p>
     * parallelSetAll(T[] array, IntFunction<? extends T> generator):void
     * 
     * 11.parallelSort：对数组进行升序排序或自定义排序
     * <p>
     * parallelSort(T[] a):void
     * parallelSort(T[] a, int fromIndex, int toIndex,Comparator<? super T> cmp):void
     * 
     * 12.spliterator：返回一个Spliterator，进行Spliterator相关操作
     * <p>
     * spliterator(T[] array):Spliterator<T>
     * 
     */

    public static void main(String[] args) {

        Integer[] arrayTest = {6, 1, 9, 2, 5, 7, 6, 10, 6, 12};

        //直接创建一个List
        List<Integer> intList = Arrays.asList(5, 7, 9);

        //以下是单线程算法，处理数组
        //1.
        //查找数组中为 key 的 下标 ：binarySearch 二分法查找，数组必须有序，且存在此数组中，否则返回负数下标
        Integer arr[] = {1, 2, 3, 4, 5, 6};
        int binarySearch = Arrays.binarySearch(arr, 3);
        System.out.println(binarySearch);
        //在指定范围内查找 key 的 下标
        int binarySearch0 = Arrays.binarySearch(arr, 0, 3, 3);
        System.out.println(binarySearch0);
        //2.
        //复制出新的数组，复制长度由 newLength 决定,长度可大于被复制数组的长度
        Integer[] copyArray1 = Arrays.copyOf(arrayTest, 5);
        arrayPrint(copyArray1);
        //复制指定下标范围内的值,含头不含尾
        Integer[] copyArray2 = Arrays.copyOfRange(arrayTest, 2, 7);
        arrayPrint(copyArray2);
        //3.
        //在指定下标内，对数组进制默认升序排序，这将改变原数组，下标含头不含尾
        Integer[] sortArray1 = Arrays.copyOf(arrayTest, arrayTest.length);
        Arrays.sort(sortArray1, 0, 5);
        arrayPrint(sortArray1);
        //数组内全部排序
        Arrays.sort(sortArray1);
        arrayPrint(sortArray1);
        Integer[] sortArray2 = Arrays.copyOf(arrayTest, arrayTest.length);
        //使用比较器降序排序，在指定下标范围内
        Arrays.sort(sortArray2, 0, 5, (x, y) -> y.compareTo(x));
        arrayPrint(sortArray2);
        //使用比较器全部降序排序
        Arrays.sort(sortArray2, (x, y) -> y.compareTo(x));
        arrayPrint(sortArray2);
        //4.
        //数组toString
        System.out.println(Arrays.toString(arrayTest));
        Integer[][] stuGrades = {{1, 3, 5, 7, 9}, {2, 4, 6, 8}, {1, 5, 10}};
        //二维数组toString
        System.out.println(Arrays.deepToString(stuGrades));
        //5.
        Integer[] equals1 = Arrays.copyOf(arrayTest, arrayTest.length);
        //比较一维数组内容是否相等
        System.out.println(Arrays.equals(equals1, arrayTest));

        Integer[][] equals2 = Arrays.copyOf(stuGrades, stuGrades.length);
        //比较二维数组内容是否相等
        System.out.println(Arrays.deepEquals(stuGrades, equals2));
        //6.
        Integer[] fillArr = new Integer[5];
        //将一个数组置为 val(5)
        Arrays.fill(fillArr, 5);
        arrayPrint(fillArr);
        //将一个数组指定范围内置为 val(10)  含头不含尾
        Arrays.fill(fillArr, 2, 3, 10);
        arrayPrint(fillArr);
        //7.
        Integer[] setAllArr = Arrays.copyOf(arrayTest, arrayTest.length);
        //一个数组全部做表达式操作
        Arrays.setAll(setAllArr, a -> a * 3);
        System.out.println(setAllArr);
        //8.
        //Java8新特性，对array进行流式处理，可用一切流式处理的方法(将专门一篇来讲Lambda与Stream)
        Arrays.stream(arrayTest)
                .map(a -> a * 2)
                .filter(a -> a > 10)
                .sorted()
                .distinct()
                .limit(6)
                .forEach(a -> System.out.print(a + " "));
        System.out.println();


        //以下是多线程算法，处理数组(大多以parallel做为开头的方法，可以充分利用现代CPU多核，处理大规模庞大的数组很有效)
        //9.
        Integer[] arrayPP1 = Arrays.copyOf(arrayTest, arrayTest.length);
        arrayPrint(arrayPP1);
        //二元迭代，对原数组内容进行二元操作
        Arrays.parallelPrefix(arrayPP1, (x, y) -> x * y);
        arrayPrint(arrayPP1);
        Integer[] arrayPP2 = Arrays.copyOf(arrayTest, arrayTest.length);
        //在指定下标范围内，对原数组内容进行二元操作，下标含头不含尾
        Arrays.parallelPrefix(arrayPP2, 0, 5, (x, y) -> x * y);
        arrayPrint(arrayPP2);
        //10.
        Integer[] arrayPSA = Arrays.copyOf(arrayTest, arrayTest.length);
        //对原有数组对每个元素重新赋值，下面例子是 下标*5 然后赋到数组对应元素
        Arrays.parallelSetAll(arrayPSA, a -> a * 5);
        arrayPrint(arrayPSA);

//11.
        Integer[] arrayPS1 = Arrays.copyOf(arrayTest, arrayTest.length);
        //对数组进行升序排序
        Arrays.parallelSort(arrayPS1);
        arrayPrint(arrayPS1);
        //对指定下标范围内的元素进行指定排序方法的排序，含头不含尾
        Arrays.parallelSort(arrayPS1, 0, 5, (x, y) -> y.compareTo(x));
        arrayPrint(arrayPS1);

        //12.
        //返回一个Spliterator进行其它操作
        Spliterator<Integer> spliterator = Arrays.spliterator(arrayPS1);
        //将原有Spliterator切出一部分为新的Spliterator，不可切时返回null
        Spliterator<Integer> integerSpliterator = spliterator.trySplit();
        // estimateSize 还有几个元素要处理
        while (spliterator.estimateSize() > 0) {
            //对每个元素进行处理
            spliterator.tryAdvance(x -> System.out.print(x * 2 + " "));
        }

        System.out.println();

        //对Spliterator遍历操作
        integerSpliterator.forEachRemaining(a -> System.out.print(a + " "));
        System.out.println();

        //还有几个要遍历
        System.out.println(integerSpliterator.getExactSizeIfKnown());

        //表示该Spliterator有那些特性，用于优化
        System.out.println(spliterator.characteristics());


    }

    //懒人癌晚期
    public static void arrayPrint(Object[] oArray) {
        System.out.println(Arrays.toString(oArray));
    }

}
