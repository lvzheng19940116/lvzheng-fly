package flylvzheng.count;

import com.alibaba.fastjson.JSONObject;
import flylvzheng.utils.RedisUtil;
import redis.clients.jedis.Jedis;

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
 * 创建时间：2019/1/21 下午3:08
 */
public class CountFilter {

    public static void count(String username) {



        Jedis jedis = RedisUtil.getJedis();
        String name = jedis.get(username);
        CountDTO countDTO = new CountDTO();
        if (null == name) {
            //开始时间
            long stime = System.currentTimeMillis();
            String s = String.valueOf(stime);
            countDTO.setName(username);
            countDTO.setCount(1);
            countDTO.setFirsttime(s);
            countDTO.setLasttiem(s);
            jedis.set(username, JSONObject.toJSONString(countDTO));
            jedis.expire(username, 600);
        } else {
            JSONObject jsonObject = JSONObject.parseObject(name);
            Integer count = Integer.parseInt(jsonObject.get("count").toString());
            countDTO.setName(username);
            countDTO.setCount(count + 1);
            String stime = jsonObject.get("firsttime").toString();
            countDTO.setFirsttime(stime);
            //最后时间
            Long l = System.currentTimeMillis();
            String ltime = String.valueOf(l);
            countDTO.setLasttiem(ltime);

            Long aLong = Long.valueOf(stime);

            Long time = l - aLong;
            jedis.set(username, JSONObject.toJSONString(countDTO));
            jedis.expire(username, 600);
           //需要填写验证码
            if(countDTO.getCount()>=3){
                System.out.println("调用验证码接口");
            }
            //如果一分钟之内登录超过三次锁定一分钟
            if(60000L<time && countDTO.getCount()>3){
                //账号锁定一分钟
            }

        }
        jedis.close();
    }


}
