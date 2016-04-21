package com.example.qzjweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import db.QZJWeatherDb;

import util.HttpCallBackListener;
import util.HttpUtil;
import util.JsonUtils;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		/*
		 * 测试httputil工具
		 * */
		/*final String path="http://www.sinaimg.cn/dy/slidenews/1_img/2016_15/45272_679986_493684.jpg";
		
		HttpUtil.sendHttpRequest(path, new HttpCallBackListener() {
			
			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
				Log.d("testSendHttpRequest", ""+response);
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				Log.d("testSendHttpRequest", "error");
			}
		});*/
		
		
		
		/*
		 * 读取utf-8格式json数据,解析省级市级县级数据
		 * */
		/*String str=getFromAssets("areaForWeather");
		//Log.d("MainActivity", "中文"+str);
		
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(this);
		if(JsonUtils.handleProvincesData(qzjWeatherDb, str)){
			Log.d("MainActivity", "true");
		}else{
			Log.d("MainActivity", "false");
		}*/
		
		
		
	}
	
	/**从assets目录下加载json数据 s
	 * @param fileName
	 * @return
	 */
	public String  getFromAssets(String fileName){
		try {
			InputStream in= getResources().getAssets().open(fileName);
			BufferedReader reader=new BufferedReader(new InputStreamReader(in,"utf-8"));
			StringBuilder response=new StringBuilder();
			String line;
			while((line=reader.readLine())!=null){
				response.append(line);
			}
			return response.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
