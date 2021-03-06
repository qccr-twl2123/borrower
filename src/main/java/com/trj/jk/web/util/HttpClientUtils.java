/**    
 * 文件名：HttpClientUtils.java    
 *    
 * 版本信息：    
 * 日期：2014年12月11日    
 * Copyright xxx Corporation 2014
 * 版权所有    
 *    
 */
package com.trj.jk.web.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**    
 *     
 * 项目名称：xhh_admin    
 * 类名称：HttpClientUtils    
 * 类描述： 发送Http请求的工具类
 * 创建人："zhuaijun"    
 * 创建时间：2014年12月11日 下午7:33:13    
 * 修改人："zhuaijun"    
 * 修改时间：2014年12月11日 下午7:33:13    
 * 修改备注：    
 * @version     
 *     
 */
public class HttpClientUtils {
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
	private static final int retryTime = 3;	
	private static final long sleep_interval = 3 * 1000;
	
	@SuppressWarnings("static-access")
	public static String httpPost(String url, String postData, Map<String, String> paraMap, Map<String, String>reqHeadMap, String encodeCharset) {
		String responseBody = null;
		CloseableHttpClient client = null;		
		HttpPost post = null;
		for (int i = 0; i < retryTime; i++) {

			try {
				if (i > 0) {
					Thread.currentThread().sleep(sleep_interval);
				}
				// 创建HttpClient客户端
	            client = HttpClients.createDefault();
				post = new HttpPost(url);
		        // 设定头部信息
		        if (reqHeadMap != null) {
		            for (String key : reqHeadMap.keySet()) {
		                post.addHeader(key, reqHeadMap.get(key));
		            }
		        }			
		        
		        if(!StringUtils.isEmpty(postData)){
					post.setEntity(new StringEntity(postData, encodeCharset == null ? "UTF-8" : encodeCharset));		        	
		        }else{
		        	if(paraMap != null) {
						List<NameValuePair> formParams = new ArrayList<NameValuePair>();
						for (Map.Entry<String, String> entry : paraMap.entrySet()) {
							formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
						}					
						post.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));

					}          		        	
		        }
		        CloseableHttpResponse response = client.execute(post);
		        int responseCode = response.getStatusLine().getStatusCode();
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if (responseCode == HttpStatus.SC_OK) {
						if (entity != null) {
							responseBody = EntityUtils.toString(entity, "UTF-8");
						}
					} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
						return null;
					} else {
						log.warn(url + " Http code when send HttpPost : " + response.getStatusLine().getStatusCode() + " --> RETRY TIME : "
								+ (i + 1));
						continue;
					}
				}

				if (StringUtils.isNotBlank(responseBody)) {
					return responseBody;
				}
			} catch (Exception e) {
				log.error("Error occurred when send HttpPost: " + url, e);
			} finally {
				if (post != null && !post.isAborted()) {
					post.abort();
				}
				post.releaseConnection();
				
				if (client != null) {
		            try {
		                client.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }	
				
				break;
			}
		}
		return responseBody;
	}	
	
	public static String uploadFile(InputStream is,String filename,String url){  
        String result = null;  
        HttpClient httpclient = HttpClients.createDefault();  
        HttpPost httppost = new HttpPost(url);  
        //防止文件名乱码  
        InputStreamBody fileis = new InputStreamBody(is,ContentType.create("text/plain", Consts.UTF_8),filename);  
        HttpEntity reqEntity = null;  
        HttpResponse responses = null;  
        try {  
            //BROWSER_COMPATIBLE 设置浏览器为兼容模式  随便设置编码防止乱码  
            reqEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)  
                    .addPart("file", fileis).setCharset(CharsetUtils.get("utf-8")).build();  
            httppost.setEntity(reqEntity);  
            // 如果需要登陆加上sessionId  
//            httppost.addHeader(new BasicHeader("Cookie", "sessionId=" + clientUser.getSessionId()));  
            responses = httpclient.execute(httppost);  
            HttpEntity entity = responses.getEntity();  
            if(entity != null){  
                result = EntityUtils.toString(entity, Charset.forName("utf-8")); 
                log.info("调用图片上传服务返回结果："+result);
            }  
        } catch (UnsupportedEncodingException e) { 
        	log.error("Error occurred when send uploadFile: " + url, e);
        } catch (ClientProtocolException e) {  
        	log.error("Error occurred when send uploadFile: " + url, e);
        } catch (IOException e) {  
        	log.error("Error occurred when send uploadFile: " + url, e); 
        }  
          
        return result;  
    }

	@SuppressWarnings("static-access")
	public static String httpGetByUrl(String url) {
		String str = new String();
		HttpGet method = null;
		CloseableHttpClient client = null;
		for (int i = 0; i < retryTime; i++) {
			try {
				if (i > 0) {
					Thread.currentThread().sleep(sleep_interval);
				}
				// 创建HttpClient客户端
				client = HttpClients.createDefault();
				method = new HttpGet(url);
				CloseableHttpResponse response = client.execute(method);
				int responseCode = response.getStatusLine().getStatusCode();
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					if (responseCode == HttpStatus.SC_OK) {
						str = EntityUtils.toString(entity, "UTF-8");
					} else if (responseCode == HttpStatus.SC_NOT_FOUND) {
						log.info(url + " http response code : " + responseCode + " --> RETRY TIME : " + (i + 1)) ;
						return null;
					} else {

						log.info(url + " Http code when send HttpGet : " + responseCode + " --> RETRY TIME : " + (i + 1));
						continue;
					}
				}

				if (StringUtils.isNotBlank(str)) {
					return str;
				}
			} catch (Exception e) {
				log.error("Error occurred when send HttpGet: " + url, e);
			} finally {
				if (method != null && !method.isAborted()) {
					method.abort();
				}
				method.releaseConnection();

				if (client != null) {
					try {
						client.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}


	/**
	 *
	 * @param url
	 * @param body
	 * @param appKey
	 * @param appSecret
	 * @return
	 * @throws Exception
	 */
	public static String doCfdCmsPost(String url, String body, String appKey, String appSecret) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		int statusCode = 200;
		try {
			URL realUrl = new URL(url);
			String method = "POST";
			String accept = "application/json;charset=utf-8";
			String contentType = "application/json;charset=utf-8";
			String timestamp = String.valueOf(System.currentTimeMillis());
			String nonce = UUID.randomUUID().toString().replace("-", "");

			// 1.HMACSha256加密
			Map<String, String> headers = new HashMap<>(3);
			headers.put("X-Ca-Timestamp", timestamp);
			headers.put("X-Ca-Nonce", nonce);
			headers.put("X-Ca-Key", appKey);
			String signature = DigestUtil.hmacsha256(appSecret, headers);

			// 2.调用服务
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setRequestMethod(method);
			// 设置通用的请求属性
			conn.setRequestProperty("Accept", accept);
			conn.setRequestProperty("Content-Type", contentType);
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("X-Ca-Timestamp", timestamp);
			conn.setRequestProperty("X-Ca-Nonce", nonce);
			conn.setRequestProperty("X-Ca-Key", appKey);
			conn.setRequestProperty("X-Ca-Signature", signature);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
			out = new PrintWriter(osw);
			// 发送请求参数
			out.print(body);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			statusCode = ((HttpURLConnection) conn).getResponseCode();
			if (statusCode != 200) {
				in = new BufferedReader(new InputStreamReader(((HttpURLConnection) conn).getErrorStream(), "utf-8"));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			}
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				throw ex;
			}
		}
		if (statusCode != 200) {
			throw new IOException("\nHttp StatusCode: " + statusCode + "\nErrorMessage: " + result);
		}
		return result.toString();
	}



}
