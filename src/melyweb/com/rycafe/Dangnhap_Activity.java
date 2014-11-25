package melyweb.com.rycafe;

import java.util.ArrayList;

import melyweb.com.ActivityQuanly.BanCF_Activity;
import melyweb.com.ActivityQuanly.Quanly_Activity;
import melyweb.com.database.database;
import melyweb.com.java.TaiKhoan;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dangnhap_Activity extends Activity {
	database db;
	ArrayList<TaiKhoan> arrTaikhoan=new ArrayList<TaiKhoan>();
	
	private EditText edtName,edtPass;
	private Button btnDangnhap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dangnhap);
		 db=new database(getApplicationContext());
	        //int e=db.addCACKHOAN(new TaiKhoan(0, "admin", "admin"));
	      //  Log.d("-----Size------",e+"");
	        arrTaikhoan=db.getTaikhoan();
	        
	        //khi kiểm tra trong cơ sở dữ liệu chưa có tài khoản nào
	        // Tự động tạo tài khoản cho Admin
	        if(arrTaikhoan.size()==0){
	        	int e=db.addCACKHOAN(new TaiKhoan(0, "admin", "admin"));
	        	Toast.makeText(getApplicationContext(),"Tài khoản đăng nhập người quản lý được mặc định là Usename:admin, Pass:Admin",Toast.LENGTH_LONG).show();
	        	arrTaikhoan=db.getTaikhoan();Log.d("-----Size------",arrTaikhoan.size()+"");
	        } else {
	        	Log.d("-----Size------",arrTaikhoan.size()+"");
			}
	       
	        edtName=(EditText) findViewById(R.id.edt_Name);
	        edtPass=(EditText) findViewById(R.id.edt_Pass);
	        btnDangnhap=(Button) findViewById(R.id.btnDangnhap);
	        arrTaikhoan=db.getTaikhoan();
	        btnDangnhap.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dangNhap();
					
				}
			});
	        
	        
	}
	
	//Hàm đăng nhập
	public void dangNhap(){
		int kt=0;
		String name=edtName.getText().toString();
		String pass=edtPass.getText().toString();
		
		if(name.equals(arrTaikhoan.get(0).getTenTK()+"") & pass.equals(arrTaikhoan.get(0).getMatKhau())){
			Intent chuyen=new Intent(getApplicationContext(),Quanly_Activity.class);
			startActivity(chuyen);
			
		
		} else {
			for(int i=0;i<arrTaikhoan.size();i++){
				if(name.equals(arrTaikhoan.get(i).getTenTK()+"") & pass.equals(arrTaikhoan.get(i).getMatKhau()+"")){
					Intent chuyen=new Intent(getApplicationContext(),BanCF_Activity.class);
					startActivity(chuyen);
				    kt=1;
					break;
				} 
			}
			if(kt==0){
				Toast.makeText(getApplicationContext(),"Tên đăng nhập hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
			}
		}
		
		
	}
}
