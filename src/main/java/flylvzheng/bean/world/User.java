package flylvzheng.bean.world;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import flylvzheng.bean.Emp;
import lombok.Data;


@Data
@Entity
public class User {
	@Id
	private int id;
	private String name;
	private String age;
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	//private List<Emp> list;
	
	
}
