package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Utils {
	
	
	
	
	
	/**解析服务器返回的json数据并存入本地shareprefences
	 * @param context
	 * @param response
	 */
	public static void HandleWeatherResponse(Context context,String response){
		if(response.contains("weather_callback(")){
			response=response.substring(response.indexOf("(")+1, response.indexOf(")"));
			try {
				JSONObject jsonObject=new JSONObject(response);
				String update_time=jsonObject.getString("update_time");
				JSONObject weatherinfo=jsonObject.getJSONObject("weatherinfo");
				String countyName=weatherinfo.getString("city");
				String countyCode=weatherinfo.getString("cityid");
				String weatherDesp=weatherinfo.getString("weather1");
				String windDesp=weatherinfo.getString("wind1");
				String date=weatherinfo.getString("date_y");
				String pm=weatherinfo.getString("pm")+"~"+weatherinfo.getString("pm-level")+"~"+weatherinfo.getString("pm-pubtime");
				String temp=weatherinfo.getString("temp1");
				saveWeatherInfo(context, update_time, countyName, countyCode, weatherDesp, windDesp, date, pm, temp);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void saveWeatherInfo(Context context, String update_time,
			String countyName, String countyCode, String weatherDesp,
			String windDesp, String date, String pm,String temp) {
		SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(context).edit();
		editor.putBoolean("county_selected", true);
		editor.putString("update_time", update_time);
		editor.putString("countyName", countyName);
		editor.putString("countyCode", countyCode);
		editor.putString("weatherDesp", weatherDesp);
		editor.putString("windDesp", windDesp);
		editor.putString("date", date);
		editor.putString("pm", pm);
		editor.putString("temp", temp);
		editor.commit();
		

	}
	

	

}
