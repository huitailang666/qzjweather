package model;

public class City {
	
	private int id;
	private String cityName;
	private String cityCode;
	private int provinceId;

	public City() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string="city id:"+id+"/name:"+cityName+"/code:"+cityCode+"/of ProvinceId:"+provinceId;
		return string;
	}
}
