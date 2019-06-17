package flylvzheng.service.impl;

import flylvzheng.bean.Emp;
import flylvzheng.form.EmpForm;
import flylvzheng.repository.EmpRepository;
import flylvzheng.service.EmpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


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
 * 创建时间：2019/6/14 下午5:42
 */
@Service
public class EmpServiceImpl  implements EmpService {
    
    @Autowired
    private EmpRepository empRepository;
    @Override
    public Page<Emp> findAll(EmpForm empForm) {

        Pageable of = PageRequest.of(empForm.getIndex()-1, empForm.getSize(), new Sort(Sort.Direction.DESC, "date1", "date2"));


        Specification<Emp> specification=(root ,query ,cb)->{
//            Predicate predicate=null;
//            if (!StringUtils.isBlank(empForm.getName())){
//                predicate=cb.equal(root.get("name"),empForm.getName());
//            }
//            if (!StringUtils.isBlank(empForm.getEffort())){
//                predicate=cb.and(predicate,cb.equal(root.get("effort"),empForm.getEffort()));
//            }
//
//            return predicate;


            List<Predicate> list = new ArrayList<Predicate>();
            if (!StringUtils.isBlank(empForm.getName())){
                list.add(cb.and(cb.equal(root.get("name"),empForm.getName())));
            }
            if (!StringUtils.isBlank(empForm.getEffort())){
                list.add(cb.and(cb.equal(root.get("effort"),empForm.getEffort())));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));


        };

        Page<Emp> all = empRepository.findAll(specification, of);

        return all;
    }
}
