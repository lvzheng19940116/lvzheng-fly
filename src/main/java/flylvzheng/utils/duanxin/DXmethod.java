package flylvzheng.utils.duanxin;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;

public class DXmethod {
	
	//短信服务给不同的项目分配的productId
	private final static String sendposition = "3bde9a2e-aec4-4682-93ce-4dccdbd66ef2_bankCard4";
	
	//key
	private final static String key = "zzzzzzzzzzzzzzzzzzzzzzz";
	
	// 接口地址，参见接口文档
	public static String url = "https://wedrive.mapbar.com/sendbox/sms/send.do";
	
	
	/**
     * Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String > sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(new DXmethod.MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
 
    /**
     * 比较器类
     *
     * @Description:
     * @Author: zhangfengchao
     * @CreateTime: 2017/4/28 14:57
     */
    private static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
	
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		
		
		
		
		Map<String, String> map = new HashMap<>();
		map.put("sendposition", "bbbb");//短信服务给不同的项目分配的productId
		map.put("phone", "aaaa");//手机号
		map.put("content", "cccc");//短信内容。 汉字需要url转码（UTF8格式）。
		map.put("timestamp", "eeee");//当前的时间戳
		map.put("message_sign", "");//短信签名，如果此字段不为空，则在短信后方拼接【 + message_sign +　】
		
		
		Map<String, String> newmap = DXmethod.sortMapByKey(map);
		System.out.println(newmap+"    排序好的map"); //排序好的map
		
		StringBuffer param = new StringBuffer();
		for (Map.Entry<String, String> p : newmap.entrySet()) {
			param.append(p.getKey());
			param.append("=");
			param.append(p.getValue());
			param.append("&");
		}
		if (param.length() > 0) {
			param.deleteCharAt(param.length() - 1);
		}
		String params= param.toString();
		System.out.println(params+"格式拼接");  //格式拼接
		
		//HMAC_SHA1 sha1 = new HMAC_SHA1();
		String result = HMAC_SHA1.genHMAC(params, key);
		System.out.println(result+"    HmacSHA1 加密后字符");
		
		final Base64 base64 = new Base64();
		final byte[] textByte = result.getBytes("UTF-8");
		//编码
		final String user_sign = base64.encodeToString(textByte);
		
		
		map.put("user_sign", user_sign);
		System.out.println("请求报文:"+map);
		
		
		String resultStr = HttpsFunction.httpsRequest(url, "GET",map.toString());
		//String resultStr = HttpClientUtil.doGet(url, map);
		
		System.out.println(resultStr);
		
		
		
	}
	
	
	
}
