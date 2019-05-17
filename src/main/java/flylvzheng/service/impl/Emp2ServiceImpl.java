package flylvzheng.service.impl;

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
 * 创建时间：2019/4/23 下午2:56
 */

import flylvzheng.service.EmpService;
import org.springframework.stereotype.Service;


@Service
public class Emp2ServiceImpl implements EmpService {
    @Override
    public String list() {
        return "Emp2ServiceImpl";
    }
}
