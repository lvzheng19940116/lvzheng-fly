package flylvzheng.repository;

import flylvzheng.bean.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* @author LvZheng
* 创建时间：2018年2月2日 下午3:26:45
*/
public interface EmpRepository extends JpaRepository<Emp, Integer>{


    List<Emp> findDistinctById();

}
