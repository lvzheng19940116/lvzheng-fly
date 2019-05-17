package flylvzheng.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.util.List;

import flylvzheng.count.CountFilter;
import flylvzheng.utils.FileUtil;
import org.elasticsearch.search.suggest.completion.RegexOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import flylvzheng.bean.Emp;
import flylvzheng.bean.world.User;
import flylvzheng.repository.EmpRepository;
import flylvzheng.repository.worldRepository.UserRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
//	@Autowired
//	private UserFeign userFeign;
//	@GetMapping(value = "/getfeign")
//	public Object getfeign() {
//	Object	 list= userFeign.get();
//		return list;˜
//	}


    public static String pro() {
        String properties = FileUtil.getProperties("application.yml", "port");
        return properties;
    }


    @GetMapping(value = "/count")
    public Object count(@RequestParam("name") String name) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ipAddress = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        String localName = request.getLocalName();


        //"count:LV"   jedis 创建count文件夹  key 是 count:LV
     //   CountFilter.count("count:LVZHENG");
        //return "success";
        String str = "";
        String macAddress = "";
        try {
            System.out.println(ipAddress);
            Process p = Runtime.getRuntime()
                    .exec("nbtstat -A " + ipAddress);
            System.out.println("===process=="+p);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());

            BufferedReader br = new BufferedReader(ir);

            while ((str = br.readLine()) != null) {
                if(str.indexOf("MAC")>1){
                    macAddress = str.substring(str.indexOf("MAC")+9, str.length());
                    macAddress = macAddress.trim();
                    System.out.println("macAddress:" + macAddress);
                    break;
                }
            }
            p.destroy();
            br.close();
            ir.close();
        } catch (IOException ex) {
        }
        return macAddress;





        //获取自己的物理地址
//        InetAddress ia = InetAddress.getLocalHost();
//        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
//        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
//
//        // 下面代码是把mac地址拼装成String
//        StringBuffer sb = new StringBuffer();
//
//        for (int i = 0; i < mac.length; i++) {
//            if (i != 0) {
//                sb.append("-");
//            }
//            // mac[i] & 0xFF 是为了把byte转化为正整数
//            String s = Integer.toHexString(mac[i] & 0xFF);
//            sb.append(s.length() == 1 ? 0 + s : s);
//        }
//
//        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
//        return sb.toString().toUpperCase().replaceAll("-", "");


    }

    /**
     * @return
     */
    @GetMapping(value = "/get")
    public Object get() {

        String pro = pro();
        List<Emp> findAll = empRepository.findAll();
        logger.info("当前登陆人{},密码是{}", findAll.get(0).getName(), findAll.get(0).getName());
        return pro;
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
