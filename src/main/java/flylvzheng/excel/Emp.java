package flylvzheng.excel;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 以动手实践为荣,以只看不练为耻. 以打印日志为荣,以出错不报为耻. 以局部变量为荣,以全局变量为耻. 以单元测试为荣,以手工测试为耻.
 * 以代码重用为荣,以复制粘贴为耻. 以多态应用为荣,以分支判断为耻. 以定义常量为荣,以魔法数字为耻. 以总结思考为荣,以不求甚解为耻.
 *
 * @author LvZheng 创建时间：2018年7月27日 上午9:56:34
 */
@Data
public class Emp {
	@Excel(name = "员工编号", width = 20)
	private int eid;
	@Excel(name = "姓名")
	private String name;
	@Excel(name = "入职日期", exportFormat = "yyyy-MM-dd HH:mm:ss")
	private Date date1;
	@Excel(name = "离职日期", exportFormat = "yyyy-MM-dd HH:mm:ss")
	private Date date2;
	// @Excel(name = "缺件扣款金额", numFormat = "0.00", type = 10, width = 15)
	// @Excel(name = "审批状态", replace = {"通过_true", "拒绝_false", "_null"})
	public Emp(int eid, String name, Date date1, Date date2) {
		super();
		this.eid = eid;
		this.name = name;
		this.date1 = date1;
		this.date2 = date2;
	}

}
