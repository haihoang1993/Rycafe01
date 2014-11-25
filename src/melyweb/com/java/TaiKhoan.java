package melyweb.com.java;

public class TaiKhoan {
	int idTaikhoan;
	String tenTK;
	String matKhau;
	public TaiKhoan(){
		
	}
	public TaiKhoan(int maTaikhoan,String tenTK,String matKhau){
		this.idTaikhoan=maTaikhoan;
		this.tenTK=tenTK;
		this.matKhau=matKhau;
	}
	public int getMaTaikhan() {
		return idTaikhoan;
	}
	public void setMaTaikhan(int maTaikhan) {
		this.idTaikhoan = maTaikhan;
	}
	public String getTenTK() {
		return tenTK;
	}
	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	
}
