package activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import util.AssetsDatabaseManager;
import util.JsonUtils;

import model.City;
import model.County;
import model.Province;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qzjweather.R;

import db.QZJWeatherDb;
import db.WeatherDb;

public class ChooseAreaActiviity extends Activity {
	
	public static final int LEVEL_PROVINCE=0;
	public static final int LEVEL_CITY=1;
	public static final int LEVEL_COUNTY=2;
	
	private ListView listView;
	private TextView titleText;
	private ArrayAdapter<String> adapter;
	private List<String> dataList=new ArrayList<String>();
	
	//private QZJWeatherDb qzjWeatherDb;
	//使用AssetsDatebaseManager
	private AssetsDatabaseManager manager;
	private WeatherDb weatherDb;
	private SQLiteDatabase database;
	
	
	
	private  int currentLevel;
	private Province selectedProvince;
	private City selectedCity;
	
	
	private List<Province> provinceList;
	private List<City> cityList;
	private List<County> countyList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_area);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		listView=(ListView)findViewById(R.id.list_view);
		titleText=(TextView)findViewById(R.id.title_text);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		
		/*qzjWeatherDb=QZJWeatherDb.getInstance(this);
		String str=getFromAssets("areaForWeather");
		if(JsonUtils.handleProvincesData(qzjWeatherDb, str)){
			Log.d("ChooseAreaActivity", "true");
		}else{
			Log.d("ChooseAreaActivity", "false");
		}*/
		
		/*
		 * 神奇啊 ，还没测试s  哈哈哈 叼叼叼 太方便了ss日后再研究
		 * */
		AssetsDatabaseManager.initManager(getApplication());
		manager=AssetsDatabaseManager.getManager();
		database=manager.getDatabase("qzj_weather888.db");
		weatherDb=new WeatherDb(database);
	
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(currentLevel==LEVEL_PROVINCE){
					selectedProvince=provinceList.get(position);
					queryCities();
				}else if(currentLevel==LEVEL_CITY){
					selectedCity=cityList.get(position);
					queryCounties();
				}else if(currentLevel==LEVEL_COUNTY){
					String countyCode=countyList.get(position).getCountyCode();
					Intent intent=new Intent(ChooseAreaActiviity.this, WeatherActivity.class);
					intent.putExtra("county_code", countyCode);
					startActivity(intent);
					finish();
				}
			}
		});
		
		queryProvinces();//加载省级数据
	}
	
	
	/**
	 * 从数据库查询全国的省  
	 */
	private void queryProvinces(){
		//provinceList=qzjWeatherDb.loadProvinces();
		provinceList=weatherDb.loadProvinces();
		if(provinceList.size()>0){
			dataList.clear();
			for(Province province:provinceList){
				dataList.add(province.getProvinceName());
			}
			adapter.notifyDataSetChanged();  //通知关联的视图，后台数据已经改变，视图需要刷新.
			listView.setSelection(0);
			titleText.setText("中国");
			currentLevel=LEVEL_PROVINCE;
		}
		
	}
	
	/**
	 * 查询市
	 */
	private void queryCities(){
		//cityList=qzjWeatherDb.loadCities(selectedProvince.getId());
		cityList=weatherDb.loadCities(selectedProvince.getId());
		if(cityList.size()>0){
			dataList.clear();
			for(City city:cityList){
				dataList.add(city.getCityName());
			}
			adapter.notifyDataSetChanged();  //?????
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvinceName());
			currentLevel=LEVEL_CITY;
		}
	}
	
	
	/**
	 * 查询县
	 */
	private void queryCounties(){
		//countyList=qzjWeatherDb.loadCounties(selectedCity.getId());
		countyList=weatherDb.loadCounties(selectedCity.getId());
		if(countyList.size()>0){
			dataList.clear();
			for(County county:countyList){
				dataList.add(county.getCountyName());
			}
			adapter.notifyDataSetChanged();  //?????
			listView.setSelection(0);
			titleText.setText(selectedCity.getCityName());
			currentLevel=LEVEL_COUNTY;
		}
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		if(currentLevel==LEVEL_COUNTY){
			queryCities();
		}else if(currentLevel==LEVEL_CITY){
			queryProvinces();
		}else {
			finish();
		}
	}
	
	
	
	

	
	/**从assets目录下加载json数据 s
	 * @param fileName
	 * @return
	 */
	public String  getFromAssets(String fileName){
		try {
			InputStream in=  getResources().getAssets().open(fileName);
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

}
