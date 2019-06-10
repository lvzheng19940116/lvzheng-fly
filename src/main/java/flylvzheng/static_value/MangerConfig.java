package flylvzheng.static_value;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

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
 * 创建时间：2018/12/5 下午2:30
 */
public class MangerConfig {

    static MangerName mangerName;
    static Properties pro;

    static {
        pro = new Properties();
        InputStream in=null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.yml");
            pro.load(new InputStreamReader(in, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mangerName = new MangerName();
        mangerName.setGrant_type(pro.getProperty("grant_type"));
        mangerName.setClient_id(pro.getProperty("client_id"));
        mangerName.setClient_secret(pro.getProperty("client_secret"));
        mangerName.setUsername(pro.getProperty("username"));
        mangerName.setPassword(pro.getProperty("password"));
        mangerName.setJdvop(pro.getProperty("jdvop"));
    }

    public static String getPropertiesByKey(String key) {
        return pro.getProperty(key);
    }

    public static MangerName getAll() {
        return mangerName;
    }

}
