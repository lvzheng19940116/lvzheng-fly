package flylvzheng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import flylvzheng.excel.Emp;
import flylvzheng.excel.ExcelUtil;

/**
 * 以动手实践为荣,以只看不练为耻. 以打印日志为荣,以出错不报为耻. 以局部变量为荣,以全局变量为耻. 以单元测试为荣,以手工测试为耻.
 * 以代码重用为荣,以复制粘贴为耻. 以多态应用为荣,以分支判断为耻. 以定义常量为荣,以魔法数字为耻. 以总结思考为荣,以不求甚解为耻.
 *
 * @author LvZheng 创建时间：2018年7月27日 上午10:48:00
 */
@RestController
public class ExcelTest {
	@GetMapping("getexcel")
	public void aaa(HttpServletResponse response) {
		List<Emp> list = new ArrayList<Emp>();
		Emp emp = new Emp(1, "tom", new Date(), new Date());
		Emp emp2 = new Emp(2, "li", new Date(), new Date());
		list.add(emp);
		list.add(emp2);
		ExcelUtil.exportExcel(list, Emp.class, "lv.xls", response);
		// 第二种调用
		// ExcelUtil.exportExcel(personList,"花名册","草帽一伙",Person.class,"海贼王.xls",response);
	}

	@GetMapping("importExcel")
	public void importExcel() {
		String filePath = "F:\\海贼王.xls";
		// 解析excel，
		// List<Person> personList = FileUtil.importExcel(filePath,1,1,Person.class);
		// 也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer
		// titleRows, Integer headerRows, Class<T> pojoClass)导入
		// System.out.println("导入数据一共【"+personList.size()+"】行");

		// TODO 保存数据库
	}

}
