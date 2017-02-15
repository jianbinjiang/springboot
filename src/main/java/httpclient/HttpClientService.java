package httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientService {
	  private static RequestConfig requestConfig; 

	  public static String doPost(String apiUrl, String json) {  
	         CloseableHttpClient httpClient = HttpClients.createDefault();  
	         String httpStr = null;  
	         HttpPost httpPost = new HttpPost(apiUrl);  
	         CloseableHttpResponse response = null;  
	   
	         try {  
	        	 httpPost.setConfig(requestConfig);  
	             StringEntity stringEntity = new StringEntity(json,"UTF-8");//解决中文乱码问题  
	             stringEntity.setContentEncoding("UTF-8");  
	             stringEntity.setContentType("application/json");  
	             httpPost.setEntity(stringEntity); 
	             //返回值
	             response = httpClient.execute(httpPost);  
	             HttpEntity entity = response.getEntity();  
	             httpStr = EntityUtils.toString(entity, "UTF-8");  
	         } catch (IOException e) {  
	             e.printStackTrace();  
	         } finally {  
	             if (response != null) {  
	                 try {  
	                     EntityUtils.consume(response.getEntity());  
	                 } catch (IOException e) {  
	                     e.printStackTrace();  
	                 }  
	             }  
	         }  
	         return httpStr;  
	     }  
	}