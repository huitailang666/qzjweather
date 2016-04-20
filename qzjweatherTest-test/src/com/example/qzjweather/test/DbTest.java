package com.example.qzjweather.test;

import java.util.List;

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
	 * ����  ��provinceʵ���洢�����ݿ�s
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
	 * ���Դ����ݿ��ȡ����ʡ����Ϣ
	 */
	public void testDb2(){
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
		List<Province> list= qzjWeatherDb.loadProvinces();
		Log.d("testDb2", ""+list.toString());
		
		
	}
	
	
	/**
	 * ����  ��Cityʵ���洢�����ݿ�s
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
	 * ���Դ����ݿ��ȡĳʡ�����г��е���Ϣ
	 */
	public void testDb4(){
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
		List<City> list=qzjWeatherDb.loadCities(1);
		Log.d("testDb4", ""+list.toString());
		
	}
	
	
	/**
	 * ���Խ�county'ʵ�����ص����ݿ�
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
	 * ���Դ����ݿ��ȡĳ���������е�����Ϣ
	 */
	public void testDb6(){
		QZJWeatherDb qzjWeatherDb=QZJWeatherDb.getInstance(getContext());
		List<County> list=qzjWeatherDb.loadCounties(1);
		Log.d("testDb6", ""+list.toString());
		
	}
	
}
