package flylvzheng.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import flylvzheng.bean.Emp;
import flylvzheng.bean.world.User;
import flylvzheng.feign.UserFeign;
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
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserFeign userFeign;
	@GetMapping(value = "/getfeign")
	public Object getfeign() {
	Object	 list= userFeign.get();
		return list;
	}
	
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/get")
	public Object get() {
		List<Emp> findAll = empRepository.findAll();
		logger.info("当前登陆人{},密码是{}",findAll.get(0).getName(),findAll.get(0).getName());
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
