package melyweb.com.FragmentQuanly;

import java.util.ArrayList;

import melyweb.com.ActivityQuanly.Quanly_Activity;
import melyweb.com.custom.BaseAdapterTaikhoan;
import melyweb.com.database.database;
import melyweb.com.java.DoUong;
import melyweb.com.java.TaiKhoan;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi") 
public class F_qlyTaikhoan extends Fragment {
	 private Activity root;
	private Context context ;
	private ListView lvTaikhoan;

    private database db;
    private BaseAdapter adap;
    private ArrayList<TaiKhoan> arrTaikhoan=new ArrayList<TaiKhoan>();
    
    EditText edName, edPass;
	public F_qlyTaikhoan() {
	}
	public F_qlyTaikhoan(ArrayList<TaiKhoan> arrTaikhoan) {
		this.arrTaikhoan=arrTaikhoan;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root=getActivity();
		//root = getActivity();

		View rootView = inflater
				.inflate(R.layout.f_ql_taikhoan, container, false);
		context=(Context) rootView.getContext();
		lvTaikhoan=(ListView) rootView.findViewById(R.id.lvTaikhoan);
        db=new database(getActivity().getApplicationContext());
       // arrTaikhoan=db.getTaikhoan();
        adap=new BaseAdapterTaikhoan(root,arrTaikhoan);
        lvTaikhoan.setAdapter(adap);
        
        ImageButton btnThemtaikhoan=(ImageButton) rootView.findViewById(R.id.btnThemtk);
        btnThemtaikhoan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupThemtaitk();
			}
		});
		return rootView;
	}
	
	
	//popup thêm tài khoản
	public void popupThemtaitk(){
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.popup_taikhoan, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setTitle("Thêm Tài Khoản:");
        
        final EditText edTen=(EditText) promptsView.findViewById(R.id.edTenDN);

		final EditText edPass=(EditText) promptsView.findViewById(R.id.edMatkhau);


		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								TaiKhoan tk=new TaiKhoan(0
										,edTen.getText().toString(),edPass.getText().toString());
								int e=db.addCACKHOAN(tk);
										
								if(e==-1){
									Toast.makeText(context,
											"Lỗi, thêm tài khoản không được!",
											Toast.LENGTH_SHORT).show();
								} else{
									
									arrTaikhoan=db.getTaikhoan();
									adap=new BaseAdapterTaikhoan(root,arrTaikhoan);
									lvTaikhoan.setAdapter(adap);
								}
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
}
