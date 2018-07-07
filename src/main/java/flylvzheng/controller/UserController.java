package flylvzheng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import flylvzheng.bean.Emp;
import flylvzheng.bean.world.User;
import flylvzheng.repository.EmpRepository;
import flylvzheng.repository.worldRepository.UserRepository;

/**
 * @author LvZheng 2018年1月19日下午3:22:43
 */
@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmpRepository empRepository;

	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "get")
	public Object get() {
		List<Emp> findAll = empRepository.findAll();
		return findAll;
	}

	@PostMapping(value = "save")

	public Object save(@RequestBody Emp emp) {

		Emp save = empRepository.save(emp);

		return save;
	}

	@PutMapping(value = "up")

	public Object up(@RequestBody Emp emp) {

		Emp save = empRepository.save(emp);

		return save;
	}

	@RequestMapping(value = "get1/{name}/{age}")
	public List<User> get1(@PathVariable("name") String name, @PathVariable("age") String age) {
		List<User> findByLike = userRepository.findByLike(name, age);
		return findByLike;
	}

	@RequestMapping(value = "get2")
	public List<User> get2() {
		String name = "380";
		String age = "";
		List<User> findByLike = userRepository.findByLike(name, age);
		return findByLike;
	}


}
