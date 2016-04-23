package util;

import model.City;
import model.County;
import model.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import db.QZJWeatherDb;

public class JsonUtils {

	public JsonUtils() {
		// TODO Auto-generated constructor stub
	}
	
	/**��data�ַ��������json���� ͨ��QZJWeather �������������ݿ�
	 * @param qzjWeatherDb
	 * @param data
	 * @return
	 */
	public synchronized static boolean handleProvincesData(QZJWeatherDb qzjWeatherDb,String data){
		if(!TextUtils.isEmpty(data)){
			try {
				JSONArray jsonArray=new JSONArray(data);
				//ʡ����
				int city_count=0;
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject=jsonArray.getJSONObject(i);
					Province province=new Province();
					province.setProvinceName(jsonObject.getString("name"));
					qzjWeatherDb.saveProvince(province);
					
					JSONArray jsonArray2= jsonObject.getJSONArray("cityList");
					//�б��� 
					for(int j=0;j<jsonArray2.length();j++){
						JSONObject jsonObject2=jsonArray2.getJSONObject(j);
						City city=new City();
						city.setCityName(jsonObject2.getString("name"));
						city.setProvinceId(i+1);
						qzjWeatherDb.saveCity(city);
						city_count++;
						JSONArray jsonArray3=jsonObject2.getJSONArray("areaList");
						//�ر��� 
						for(int k=0;k<jsonArray3.length();k++){
							JSONObject jsonObject3=jsonArray3.getJSONObject(k);
							County county=new County();
							county.setCountyCode(jsonObject3.getString("id"));
							county.setCountyName(jsonObject3.getString("name"));
							county.setCityId(city_count);
							
							qzjWeatherDb.saveCounty(county);
						}
						
					}
					
					
				}
				return true;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		return false;
	}
	
	
	
	

}
