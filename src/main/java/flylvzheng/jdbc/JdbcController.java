package flylvzheng.jdbc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

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
 * 创建时间：2019/6/20 11:27 AM
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    @GetMapping("/save")
    public void save() {
        //开始总计时
        long bTime1 = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement pstm = null;
        //加载jdbc驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql   rewriteBatchedStatements 开启批处理
            conn = DriverManager.getConnection("jdbc:mysql://10.122.30.102:3306/bank_collection?characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true", "ordercenter_testuser", "abcd-1234");
            conn.setAutoCommit(false);
            //insert语句
            String sql = "insert into biz_money_records_tst(id,used_status) values(?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);

            long bTime = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                pstm.setString(1, UUID.randomUUID().toString());
                pstm.setString(2, "测试数据");
                pstm.addBatch();  //批量处理
            }
            pstm.executeBatch();
            conn.commit();
            //关闭分段计时
            long eTime = System.currentTimeMillis();
            //输出
            System.out.println("成功插入5000条数据耗时：" + (eTime - bTime));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

        //关闭总计时
        long eTime1 = System.currentTimeMillis();
        //输出
        System.out.println("插入" + 10000 + "数据共耗时：" + (eTime1 - bTime1));
    }
}
