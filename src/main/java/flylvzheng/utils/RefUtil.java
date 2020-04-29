package flylvzheng.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * @author LvZheng
 * 创建时间：2020/4/17 3:13 PM
 */
public class RefUtil {

    private Integer objCheckIsNull(Object object) {
        Integer num = 0;
        Class clazz = (Class) object.getClass(); // 得到类对象
        Field fields[] = clazz.getDeclaredFields(); // 得到所有属性
        boolean flag = true; //定义返回结果，默认为true
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(object); //得到属性值
                Type fieldType = field.getGenericType();//得到属性类型
                String fieldName = field.getName(); // 得到属性名
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldValue != null && !fieldValue.equals("")) {
                num++;
            }
        }
        return num;
    }


    public static void main(String[] args) {
        String str="aaaa1";
        System.out.println(str.substring(0,4));
    }
}
