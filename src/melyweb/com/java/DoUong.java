package melyweb.com.java;

public class DoUong {
	private String maDouong;
	private String tenDouong;
	private int giaNhap;
	private int giaBan;
	private String chuThich;
	
	public DoUong(){
		chuThich="0";
		
	}
	public DoUong(String maDouong,String tenDouong, int giaNhap,int giaBan,String chuThich){
		this.maDouong=maDouong;
		this.tenDouong=tenDouong;
		this.giaNhap=giaNhap;
		this.giaBan=giaBan;
		this.chuThich=chuThich;
	}
	public String getMaDouong() {
		return maDouong;
	}
	public void setMaDouong(String maDouong) {
		this.maDouong = maDouong;
	}
	public String getTenDouong() {
		return tenDouong;
	}
	public void setTenDouong(String tenDouong) {
		this.tenDouong = tenDouong;
	}
	
	public int getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(int giaNhap) {
		this.giaNhap = giaNhap;
	}
	public int getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}
	public String getChuThich() {
		return chuThich;
	}
	public void setChuThich(String chuThich) {
		this.chuThich = chuThich;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return tenDouong+"";
	}
	
	
}
