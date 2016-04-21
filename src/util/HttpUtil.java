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
	 * �ڷ����ж���ı���ʱ�ֲ�����������������ʱ���ֲ�����(str1,str2)��Ӧ��ջ�ͱ������ˣ��������ڲ���ȥ���ʾֲ�����ʱ�ͻᷢ������
	 * ���ڱ���ǰ����finalʱ
	 * �������Ͳ�������ı����ˣ����˳����������ڱ��������б���ʱ(������׶�)�ͻ��ñ�����ֵ����������������Ͳ�����ֱ���������ٷ��ʱ����Ĵ���
	 * @param path
	 * @param listener  ����ʱ��дonFinish��onError�ص��������ڴ����ص�String����Ϊ�����̺߳��޷�����String��
	 * ͨ����http���� 6666
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
