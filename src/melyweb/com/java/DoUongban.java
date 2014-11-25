package melyweb.com.java;

public class DoUongban {
	private String maBan;
	private String maDouong;
	private String tenDouong;
	private int giaNhap;
	private int giaBan;
	private int soLuong;
	private int soTien;
	private String daTe;
	
	public DoUongban(){
		
	}
	public DoUongban(String maBan,String maDouong,String tenDouong, int giaNhap, int giaBan,int soLuong,int soTien,String date ){
		this.maBan=maBan;
		this.maDouong=maDouong;
		this.tenDouong=tenDouong;
		this.giaNhap=giaNhap;
		this.giaBan=giaBan;
		this.soLuong=soLuong;
		this.soTien=soTien;
		this.daTe=date;
	}
	public String getMaBan() {
		return maBan;
	}
	public void setMaBan(String maBan) {
		this.maBan = maBan;
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
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getSoTien() {
		return soTien;
	}
	public void setSoTien(int soTien) {
		this.soTien = soTien;
	}
	public String getDaTe() {
		return daTe;
	}
	public void setDaTe(String daTe) {
		this.daTe = daTe;
	}
	
}
