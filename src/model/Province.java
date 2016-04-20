package model;

public class Province {
	
	private int id;
	private String provinceName;
	private String provinceCode;
	

	public Province() {
		// TODO Auto-generated constructor stub
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string="province id:"+id+"/name:"+provinceName+"/code:"+provinceCode;
		return string;
	}
	
}
