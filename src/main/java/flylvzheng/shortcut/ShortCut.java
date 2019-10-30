package flylvzheng.shortcut;

import lombok.var;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @author LvZheng
 * 创建时间：2019/7/31 10:52 AM
 */
public class ShortCut {
    private String lvzheng;
    private String nihao;




    public static void main(String[] args) {
        String lvzheng = "lvzheng";
        var lvzhe = 111;
        var lvzh = 111;
        String lv=lvzhe+lvzheng;
        System.out.println(lv);
        System.out.println(lvzhe+lvzh);

        Optional.of("lvzheng");

    }
}
