package flylvzheng.repository;

import flylvzheng.bean.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
* @author LvZheng
* 创建时间：2018年2月2日 下午3:26:45
*/
public interface EmpRepository extends JpaRepository<Emp, Integer>,JpaSpecificationExecutor<Emp> {

    List<Emp> findBySectIsNotNull();


    Page<Emp> findAllByName(Pageable pageable, String add);

}
