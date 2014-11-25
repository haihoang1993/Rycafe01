package melyweb.com.java;

public class Table_cf {
	String maBan;
	String tenBan;
	String daTe;
	String tiMe;
	int soTien;
	int idTrangthai;
	
	public Table_cf(){
		
		
	}
	
	public Table_cf(String maBan,String tenBan,String daTe,String tiMe,int soTien,int idTrangthai){
		this.maBan=maBan;
		this.tenBan=tenBan;
		this.daTe=daTe;
		this.tiMe=tiMe;
		this.idTrangthai=idTrangthai;
		this.soTien=soTien;
	}

	public String getMaBan() {
		return maBan;
	}

	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}

	public String getTenBan() {
		return tenBan;
	}

	public void setTenBan(String tenBan) {
		this.tenBan = tenBan;
	}

	public String getDaTe() {
		return daTe;
	}

	public void setDaTe(String daTe) {
		this.daTe = daTe;
	}

	public String getTiMe() {
		return tiMe;
	}

	public void setTiMe(String tiMe) {
		this.tiMe = tiMe;
	}

	public int getSoTien() {
		return soTien;
	}

	public void setSoTien(int soTien) {
		this.soTien = soTien;
	}

	public int getIdTrangthai() {
		return idTrangthai;
	}

	public void setIdTrangthai(int idTrangthai) {
		this.idTrangthai = idTrangthai;
	}
	
}
