package util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpUtil {

	public HttpUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * 在方法中定义的变量时局部变量，当方法返回时，局部变量(str1,str2)对应的栈就被回收了，当方法内部类去访问局部变量时就会发生错误。
	 * 当在变量前加上final时
	 * ，变量就不在是真的变量了，成了常量，这样在编译器进行编译时(即编译阶段)就会用变量的值来代替变量，这样就不会出现变量清除后，再访问变量的错误
	 * @param path
	 * @param listener  调用时复写onFinish和onError回调函数用于处理返回的String（因为开启线程后无法返回String）
	 * 通用型http请求 6666
	 */
	public static void sendHttpRequest(final String path,final HttpCallBackListener listener){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					HttpClient httpClient=new DefaultHttpClient();
					HttpGet httpGet=new HttpGet(path);
					HttpResponse httpResponse=httpClient.execute(httpGet);			
					int code=httpResponse.getStatusLine().getStatusCode();	
					if(code==200){
						HttpEntity entity=httpResponse.getEntity();
						String str=EntityUtils.toString(entity,"utf-8");
						if(listener!=null){
							listener.onFinish(str);
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					listener.onError(e);
				}
			
			}
		}).start();	
	}
	
	
	
}
