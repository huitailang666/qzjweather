package activity;


import util.HttpCallBackListener;
import util.HttpUtil;
import util.Utils;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.qzjweather.R;

public class WeatherActivity extends Activity {
	
	private TextView cityNameText;
	private TextView currentDateText;
	private TextView weatherDespText;
	private TextView windDespText;
	private TextView tempText;

	private TextView pmText;
	private TextView publishTimeText;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_layout);
		
		cityNameText=(TextView)findViewById(R.id.city_name);
		currentDateText=(TextView)findViewById(R.id.current_date);
		weatherDespText=(TextView)findViewById(R.id.weather_desp);
		windDespText=(TextView)findViewById(R.id.wind_desp);
		tempText=(TextView)findViewById(R.id.temp);
		
		pmText=(TextView)findViewById(R.id.pm2_5);
		publishTimeText=(TextView)findViewById(R.id.publish_time);
		
		String countyCode=getIntent().getStringExtra("county_code");
		if(!TextUtils.isEmpty(countyCode)){
			//��intent��choosearea���������ѯcounty����
			publishTimeText.setText("���ڸ��¡�����");
			
			queryWeatherCode(countyCode);
			
		}else{
			//û�о�ֱ����ʾ��������
			
		}
		
		
		
	}
	
	
	/**��ѯ�ؼ����ŵ����� 
	 * @param countyCode
	 */
	private void queryWeatherCode(String countyCode){
		String address="http://weather.123.duba.net/static/weather_info/"+countyCode+".html";
		queryFromServer(address);
		
	}
	
	/**���ݵ�ַȥ���������ѯ������Ϣ �����߳�
	 * @param address
	 */
	private void queryFromServer(final String address){
		HttpUtil.sendHttpRequest(address, new HttpCallBackListener() {
			
			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
					if(!TextUtils.isEmpty(response)){
						//����ӷ��������ص�����
						Utils.HandleWeatherResponse(WeatherActivity.this, response);
						
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								showWeather();
							}
						});
					}
					
					
				
				
				
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						publishTimeText.setText("����ʧ��");
					}
				});
			}
		});
	}
	
	
	
	
	private void showWeather(){
		SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
		cityNameText.setText(prefs.getString("countyName", ""));
		currentDateText.setText(prefs.getString("date", ""));
		weatherDespText.setText(prefs.getString("weatherDesp", ""));
		windDespText.setText(prefs.getString("windDesp", ""));
		tempText.setText(prefs.getString("temp", ""));
		pmText.setText(prefs.getString("pm", ""));
		publishTimeText.setText(prefs.getString("update_time", ""));
		

		
	}

}
