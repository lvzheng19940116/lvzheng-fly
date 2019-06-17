package flylvzheng.form;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
 * 创建时间：2019/6/14 下午5:43
 */
@Data
public class EmpForm {

    @NotNull(message="每页多少条不能为空")
    private Integer size;
    @NotNull(message="当前页不能为空")
    private Integer index;
    private String name;
    private String sect;
    private String effort;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date2;
}
