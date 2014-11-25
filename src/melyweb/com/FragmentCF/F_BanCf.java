package melyweb.com.FragmentCF;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import melyweb.com.custom.CustomAdapterBancf;
import melyweb.com.database.database2;
import melyweb.com.java.Table_cf;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi") 
public class F_BanCf extends Fragment {
	private static Fragment fragment = null;
	private Context context ;
	
	private int ngay,thang,nam,gio,phut,giay;
	
	
	private Activity root;
	private ListView lvBancf;
	private ImageButton btThemban;
	ArrayList<Table_cf> arrBancf=new ArrayList<Table_cf>();
	database2 db;
	
	BaseAdapter adapBancf;
	public F_BanCf(){
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		root=getActivity();
		View rootView = inflater
				.inflate(R.layout.f_bancf, container, false);
		context=(Context) rootView.getContext();
		lvBancf=(ListView) rootView.findViewById(R.id.lvBan);
		btThemban=(ImageButton) rootView.findViewById(R.id.btThemban);
		db=new database2(getActivity().getApplicationContext());
	//	db.addBancf(new Table_cf("00111","Bi","20/14/21","21:21",15000, 1));		
		setLv();
		ImageButton btThemban=(ImageButton) rootView.findViewById(R.id.btThemban);
		btThemban.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupThemban();
			}
		});
		return rootView;
	}
	
	
	public void setDay(){
		 	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
			Date currentTime = localCalendar.getTime();
	        int currentDay = localCalendar.get(Calendar.DATE);
	        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
	        int currentYear = localCalendar.get(Calendar.YEAR);
	        ngay=currentDay;
	        thang=currentMonth;
	        nam=currentYear;
	}
	
	public void setTime(){
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		Date currentTime = localCalendar.getTime();
		gio=currentTime.getHours();
		giay=currentTime.getSeconds();
		phut=currentTime.getMinutes();
	}
	

	public void setLv(){
		arrBancf= db.getTable1(0);
		Log.d("---bàn cf:---",arrBancf.size()+"");
		adapBancf=new CustomAdapterBancf(root, arrBancf);
		lvBancf.setAdapter(adapBancf);
		lvBancf.setOnItemClickListener(new OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> adapter, View view, int i, long arg) {
				  
				   		Log.d("------","click1");
				   		Table_cf tb=arrBancf.get(i);
					  	fragment = new F_toTable(tb);
						FragmentManager fragmentManager = getFragmentManager();						
						fragmentManager.beginTransaction().addToBackStack(null)
								.replace(R.id.frame_container1, fragment).commit();
			   } 
			});
	}
	public void PopupThemban(){
		setDay();
		setTime();
		String eNgay=ngay+"/"+thang+"/"+nam;
		String eTime=gio+":"+phut+":"+giay;
		
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.popup_ban_cf, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setTitle("Thêm Bàn mới:");
        final EditText edMaban=(EditText) promptsView.findViewById(R.id.edMaban);
        edMaban.setText(ngay+""+thang+""+nam+""+gio+""+phut+""+giay+"");
        final EditText edTenban=(EditText) promptsView.findViewById(R.id.edTenban);
        edTenban.setText("Bàn mới");
		final EditText edNgay=(EditText) promptsView.findViewById(R.id.edNgay);
		edNgay.setText(eNgay);
		final EditText edGio=(EditText) promptsView.findViewById(R.id.edTime);
		edGio.setText(eTime);
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								Table_cf tb=new Table_cf(edMaban.getText()+"",edTenban.getText()+"",edNgay.getText()+"",edGio.getText()+"",0,0);
								
								int e=db.addBancf(tb);
										
								if(e==-1){
									Toast.makeText(context,
											"Lỗi, thêm tài khoản không được!",
											Toast.LENGTH_SHORT).show();
								} else{
									setLv();
									
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
