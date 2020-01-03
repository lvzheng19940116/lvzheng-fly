package flylvzheng.controller;

import flylvzheng.bean.Emp;
import flylvzheng.repository.EmpRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LvZheng
 * 创建时间：2019/12/31 2:36 PM
 */
@RestController
public class ConditionQueryController {

    @Autowired
    private EmpRepository empRepository;
    @Resource
    private EntityManager entityManager;


/**
    Pageable pageRequest = PageRequest.of(kabAndGabForm.getIndex() - 1, kabAndGabForm.getSize(), new Sort(Sort.Direction.ASC, "id"));
    Specification<BaseGab> specification = (root, query, cb) -> {
        List<Predicate> list = new ArrayList<Predicate>();
        if (StringUtils.isNotBlank(kabAndGabForm.getSoldtoCode())) {
            list.add(cb.and(cb.like(root.get("gabSoldtoCode"), "%" + kabAndGabForm.getSoldtoCode() + "%")));

        }
        if (StringUtils.isNotBlank(kabAndGabForm.getSoldtoName())) {
            list.add(cb.and(cb.like(root.get("gabSoldtoName"), "%" + spaceUtil.del(kabAndGabForm.getSoldtoName()) + "%")));
        }
        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    };
    Page<BaseGab> all = baseGabRepository.findAll(specification, pageRequest);
 */



    @PostMapping("/query")
    public Object save() {
        //创建CriteriaBuilder安全查询工厂
        //CriteriaBuilder是一个工厂对象,安全查询的开始.用于构建JPA安全查询.
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //创建CriteriaQuery安全查询主语句
        //CriteriaQuery对象必须在实体类型或嵌入式类型上的Criteria 查询上起作用。
        CriteriaQuery<Emp> query = criteriaBuilder.createQuery(Emp.class);
        //Root 定义查询的From子句中能出现的类型
        Root<Emp> root = query.from(Emp.class);
        //Predicate 过滤条件 构建where字句可能的各种条件
        //这里用List存放多种查询条件,实现动态查询
        List<Predicate> list = new ArrayList<>();

        list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), "吕正")));

        Predicate[] predicates = list.toArray(new Predicate[list.size()]);

        //where()拼接查询条件
        query.where(predicates);
        TypedQuery<Emp> emps = entityManager.createQuery(query);
        List<Emp> resultList = emps.getResultList();
        return resultList;
    }

}
