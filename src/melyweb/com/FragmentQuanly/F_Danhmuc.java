package melyweb.com.FragmentQuanly;

import java.util.ArrayList;

import melyweb.com.custom.CustomAdapterDanhmuc;
import melyweb.com.database.database;
import melyweb.com.java.DoUong;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
public class F_Danhmuc extends Fragment{
    private ArrayList<DoUong> arrDouong=new ArrayList<DoUong>();
	
	
    private database db;
    private ImageButton btThem;
    private ListView lvDanhmuc;
    private Context context;
    private BaseAdapter adap;
	public F_Danhmuc(){
		
	}
	public F_Danhmuc(ArrayList<DoUong> arrDoUong){
			this.arrDouong=arrDouong;
		}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater
				.inflate(R.layout.f_qldanhmuc, container, false);
		context=(Context) rootView.getContext();
		db=new database(getActivity().getApplicationContext());
		btThem =(ImageButton) rootView.findViewById(R.id.btThemdouong);
		lvDanhmuc=(ListView) rootView.findViewById(R.id.lvDanhmuc);
		arrDouong=db.getDanhmuc();
		Log.d("--------",arrDouong.size()+"");
		adap=new CustomAdapterDanhmuc(getActivity(), arrDouong);
		lvDanhmuc.setAdapter(adap);
		btThem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				themDou();
			}
		});
		return rootView;
	}
	
	
	public void themDou(){
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.popup_douong, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setTitle("Thêm đồ uống");
        
        final EditText edMa=(EditText) promptsView.findViewById(R.id.edMadouong);
        final EditText edTen=(EditText) promptsView.findViewById(R.id.edTendouong);
		final EditText edGianhap=(EditText) promptsView.findViewById(R.id.edGianhap);
		final EditText edGiaban=(EditText) promptsView.findViewById(R.id.edGiaban);
		final EditText edGhichu=(EditText) promptsView.findViewById(R.id.edGhichu);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
							DoUong du=new DoUong(edMa.getText().toString()
									,edTen.getText().toString(), Integer.parseInt(edGianhap.getText().toString())
									, Integer.parseInt(edGiaban.getText().toString()),edGhichu.getText().toString());
							int e=db.addDouong(du);
													
							
										
								if(e==-1){
									Toast.makeText(context,
											"Lỗi, thêm tài khoản không được!",
											Toast.LENGTH_SHORT).show();
								} else{
									arrDouong.add(du);
									adap.notifyDataSetChanged();
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
