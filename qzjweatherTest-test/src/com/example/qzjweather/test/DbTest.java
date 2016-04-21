package com.example.qzjweather.test;

import java.util.List;

import util.HttpCallBackListener;
import util.HttpUtil;
import util.JsonUtils;

import model.City;
import model.County;
import model.Province;
import android.test.AndroidTestCase;
import android.util.Log;
import db.QZJWeatherDb;

public class DbTest extends AndroidTestCase {

	public DbTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	
	
	
	/**
	 * 测试  将province实例存储到数据库s
	 */
	public void testDb1(){
		
			QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
			Province province=new Province();
			
			province.setProvinceName("shanghai");
			province.setProvinceCode("021");
			qzjWeatherDb.saveProvince(province);
			
			province.setProvinceName("beijing");
			province.setProvinceCode("010");
			qzjWeatherDb.saveProvince(province);
			
			province.setProvinceName("hangzhou");
			province.setProvinceCode("0574");
			qzjWeatherDb.saveProvince(province);
				
	}
	
	/**
	 * 测试从数据库读取所有省的信息
	 */
	public void testDb2(){
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
		List<Province> list= qzjWeatherDb.loadProvinces();
		Log.d("testDb2", ""+list.toString());
		
		
	}
	
	
	/**
	 * 测试  将City实例存储到数据库s
	 */
	public void testDb3(){
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
		City city=new City();
		city.setProvinceId(1);
		city.setCityName("pudong");
		city.setCityCode("110");
		qzjWeatherDb.saveCity(city);
		city.setCityName("minhang");
		city.setCityCode("119");
		qzjWeatherDb.saveCity(city);
		city.setCityName("yangpu");
		city.setCityCode("120");
		qzjWeatherDb.saveCity(city);
		city.setCityName("hongkou");
		city.setCityCode("114");
		qzjWeatherDb.saveCity(city);
		
		
		
	}
	
	
	
	/**
	 * 测试从数据库读取某省下所有城市的信息
	 */
	public void testDb4(){
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
		List<City> list=qzjWeatherDb.loadCities(9);
		Log.d("testDb4", ""+list.toString());
		
	}
	
	
	/**
	 * 测试将county'实例加载到数据库
	 */
	public void testDb5(){
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
		County county=new County();
		county.setCityId(1);
		county.setCountyName("beicai");
		county.setCountyCode("5843");
		qzjWeatherDb.saveCounty(county);
		county.setCountyName("huamu");
		county.setCountyCode("5891");
		qzjWeatherDb.saveCounty(county);
		county.setCountyName("sanlin");
		county.setCountyCode("5877");
		qzjWeatherDb.saveCounty(county);
		county.setCountyName("kangqiao");
		county.setCountyCode("5858");
		qzjWeatherDb.saveCounty(county);

	}
	
	/**
	 * 测试从数据库读取某城市下所有的县信息
	 */
	public void testDb6(){
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
		List<County> list=qzjWeatherDb.loadCounties(1);
		Log.d("testDb6", ""+list.toString());
		
	}
	
	/**单元测试不能访问网络 d
	 * 测试Http请求util
	 */
	public void testSendHttpRequest(){
		final String path="http://www.sinaimg.cn/dy/slidenews/1_img/2016_15/45272_679986_493684.jpg";
		
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
		});

	}
	

}
