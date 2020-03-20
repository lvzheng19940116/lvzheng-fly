/**  
 * Title: HttpClientUtil.java
 * Description:   
 * Copyright: Copyright (c) 2019 
 * Company: www.samsundot.com
 * @author Ace Nada  
 * @date 2019年11月19日  
 * @version 1.0  
 */
package flylvzheng.utils.https;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: HttpClientUtil Description: Copyright: Copyright (c) 2019 Company:
 * www.samsundot.com
 * 
 * @author Ace Nada
 * @date 2019年11月19日
 * @version 1.0
 */

@Component
public class HttpClientUtil {

	public static String host;

	public static String id;

	public static String secret;

	@Value("${face.host}")
	public void setHost(String host) {
		HttpClientUtil.host = host;
	}

	@Value("${face.id}")
	public void setId(String id) {
		HttpClientUtil.id = id;
	}

	@Value("${face.secret}")
	public void setSecret(String secret) {
		HttpClientUtil.secret = secret;
	}

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private HttpClientUtil() {
	}

	public static CloseableHttpClient createSSLClientDefault() {

		try {

			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(new TrustStrategy() {

				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();

		} catch (Exception e) {
		}

		return HttpClients.createDefault();
	}

	public static String doGet(String url, Map<String, String> param, boolean haveSecret,
			Map<String, String> headerParams) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = createSSLClientDefault();
		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 有密钥的，将密钥设置到 Header 中
			if (haveSecret) {
				for (String key : headerParams.keySet()) {
					httpGet.setHeader(key, headerParams.get(key));
				}
				httpGet.setHeader("Content-Type", "application/x-www-form-urlencode");
			}
			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			logger.info("Get 请求之后返回的数据： " + resultString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	// 不携带密钥get 请求
	public static String doGet(String url) {
		return doGet(url, null, false, null);
	}

	// 不带密钥带参数的get 请求
	public static String doGet(String url, Map<String, String> param) {
		return doGet(url, param, false, null);
	}

	// 携带密钥 get 请求
	public static String doGet(String url, boolean haveSecret, Map<String, String> secretParams) {
		return doGet(url, null, haveSecret, secretParams);
	}

	public static String doPost(String url, Map<String, String> param, boolean isUploadFile,
			MultipartEntity multipartEntity, Map<String, String> headerParams) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = createSSLClientDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 带密钥的情况下
			if (headerParams != null) {
				if (headerParams.size() > 0) {
					for (String key : headerParams.keySet()) {
						httpPost.setHeader(key, headerParams.get(key));
					}
				}
			}

			// 上传文件
			if (isUploadFile) {
				httpPost.setEntity(multipartEntity);
			}
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
				httpPost.setEntity(entity);
			}
			logger.info("请求的路径： " + httpPost.getURI());
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			logger.info("Post 请求后的内容 ： " + resultString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	// 无密钥的情况下
	public static String doPost(String url) {
		return doPost(url, null, false, null, null);
	}

	// 无密钥的情况下
	public static String doPost(String url, Map<String, String> param) {
		return doPost(url, param, false, null, null);
	}

	// 带密钥的情况下,上传文件的情况
	public static String doPost(String url, MultipartEntity multipartEntity, Map<String, String> headerParams) {
		return doPost(url, null, true, multipartEntity, headerParams);
	}

	// 需要设置http 头的情况
	public static String doPost(String url, Map<String, String> param, Map<String, String> headerParams) {
		return doPost(url, param, false, null, headerParams);
	}

	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = createSSLClientDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json);
			httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
			httpPost.setEntity(entity);

			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doDelete(String url) {
		return doDelete(url, null);
	}

	public static String doDelete(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = createSSLClientDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpDelete httpDelete = new HttpDelete(url);
			httpDelete.addHeader("Content-type", "application/json;charset=UTF-8");
			// 创建参数列表
//            if (param != null) {
//                List<NameValuePair> paramList = new ArrayList<>();
//                for (String key : param.keySet()) {
//                    paramList.add(new BasicNameValuePair(key, param.get(key)));
//                }
//                // 模拟表单
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
////                httpDelete.set
//            }
			// 执行http请求
			response = httpClient.execute(httpDelete);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			logger.info("Delete 请求后的内容 ： " + resultString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String getSign(Map<String, String> param, Map<String, String> secretParam) {
		String timeStamp = String.valueOf(System.currentTimeMillis());
		//String paramStr = SignUtil.createLinkString(SignUtil.paraFilter(param));
		String paramStr ="";
		StringBuffer sb = new StringBuffer();
		// 参数排序后，拼接X-AppId，X-Timestamp 和secret
		if (!paramStr.isEmpty()) {
			sb.append(paramStr);
		}
		for (String key : secretParam.keySet()) {
			if (!sb.toString().endsWith("&")) {
				sb.append("&").append(key).append("=").append(secretParam.get(key));
			}
		}
		sb.append("&secret=").append(HttpClientUtil.secret);
		// 对拼接好的字符串进行 MD5 编码
		String sign = DigestUtils.md5DigestAsHex(sb.toString().getBytes()).toLowerCase();
		return sign;
	}

	public static Map<String, String> getHeaderMap(Map<String, String> param) {
		Map<String, String> secretParam = new HashMap<>();
		secretParam.put("X-AppId", id);
		secretParam.put("X-Timestamp", new Long(System.currentTimeMillis()).toString());
		String sign = getSign(param, secretParam);
		secretParam.put("X-Sign", sign);
		return secretParam;
	}

}
