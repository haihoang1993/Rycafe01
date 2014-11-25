package melyweb.com.FragmentQuanly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import melyweb.com.FragmentCF.F_toTable;
import melyweb.com.custom.CustomAdapterBancf2;
import melyweb.com.database.database2;
import melyweb.com.java.DoUongban;
import melyweb.com.java.Table_cf;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class F_Thongke extends Fragment{
	private ListView lvBan;
	private ArrayList<Table_cf> arrBan;
	private BaseAdapter adapBan;
	Context ct;
	private int ngay,thang,nam;
	private Calendar cal;
	private Date dateFinish;
	TextView tvDay;
	View rootView ;
	
	database2 db2;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		rootView = inflater
				.inflate(R.layout.f_thongke, container, false);
		tvDay=(TextView) rootView.findViewById(R.id.tvDaytk);
		lvBan=(ListView) rootView.findViewById(R.id.lvBantrongngay);
		
		db2=new database2(getActivity().getApplicationContext());
		
		getDefaultInfor();
		toListview();
		tvDay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDatePickerDialog();
				
			}
		});
		
	    return rootView;
	}
	private static Fragment fragment = null;
	// toListview 
	void toListview(){
		arrBan=db2.getTabledate(tvDay.getText().toString());
		adapBan=new CustomAdapterBancf2(getActivity(), arrBan);
		lvBan.setAdapter(adapBan);
		lvBan.setOnItemClickListener(new OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> adapter, View view, int i, long arg) {
				  
				   		Log.d("------","click1");
				   		Table_cf tb=arrBan.get(i);
				   		ArrayList<DoUongban> arrL=db2.getDouongban(tb.getMaBan());
					  	fragment = new F_ChitietbanTK(tb,arrL);
						FragmentManager fragmentManager = getFragmentManager();						
						fragmentManager.beginTransaction().addToBackStack(null)
								.replace(R.id.frame_container, fragment).commit();
			   } 
			});
	}
	
	/**
	 * Hàm hiển thị DatePicker dialog
	 */
	public void showDatePickerDialog()
	{
		OnDateSetListener callback=new OnDateSetListener() {
			public void onDateSet(DatePicker view, int year,
					int monthOfYear,
					int dayOfMonth) {
				String s=new String();
				//Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
				if(dayOfMonth<10){
					s="0"+(dayOfMonth);
					if(monthOfYear<9) s=s+"/0"+(monthOfYear+1)+"/"+year;
					else s=s+"/"+(monthOfYear+1)+"/"+year;
					tvDay.setText(s);
				} else {
					s=dayOfMonth+"";
					if(monthOfYear<9) s=s+"/0"+(monthOfYear+1)+"/"+year;
					else s=s+"/"+(monthOfYear+1)+"/"+year;
					tvDay.setText(s);
				}
				//Lưu vết lại biến ngày hoàn thành
				cal.set(year, monthOfYear, dayOfMonth);
				dateFinish=cal.getTime();
				toListview();
			}
		};
		//các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
		//sẽ giống với trên TextView khi mở nó lên 
		String s=tvDay.getText()+"";
		String strArrtmp[]=s.split("/");
		int ngay=Integer.parseInt(strArrtmp[0]);
		int thang=Integer.parseInt(strArrtmp[1])-1;
		int nam=Integer.parseInt(strArrtmp[2]);
		DatePickerDialog pic=new DatePickerDialog(
			rootView.getContext(), 
				callback, nam, thang, ngay);
		pic.setTitle("Chọn ngày:");
		pic.show();
		
	}
	//------------------------------------------------------------------------
	/**
	 * Hàm lấy các thông số mặc định khi lần đầu tiền chạy ứng dụng
	 */
	public void getDefaultInfor()
	{
		//lấy ngày hiện tại của hệ thống
	    cal=Calendar.getInstance();
		SimpleDateFormat dft=null;
		//Định dạng ngày / tháng /năm
		dft=new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
		String strDate=dft.format(cal.getTime());
		//hiển thị lên giao diện
		tvDay.setText(strDate);
		//Định dạng giờ phút am/pm
		dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
		String strTime=dft.format(cal.getTime());
		//đưa lên giao diện
	
		//editCv.requestFocus();
		//gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
		dateFinish=cal.getTime();
//		hourFinish=cal.getTime();
	}
	
}
