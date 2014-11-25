package melyweb.com.FragmentQuanly;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import melyweb.com.custom.CustomAdapterBancf2;
import melyweb.com.database.database2;
import melyweb.com.java.DoUongban;
import melyweb.com.java.Table_cf;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class F_ThongkeThang extends Fragment{
	TextView tvLai,tvDoanthu;
	private ImageView ivNext,ivBack;
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
				.inflate(R.layout.f_thongkethang, container, false);
		tvDay=(TextView) rootView.findViewById(R.id.tvDaythang);
		lvBan=(ListView) rootView.findViewById(R.id.lvBan03);
		ivBack=(ImageView) rootView.findViewById(R.id.ivBack);
		ivNext=(ImageView) rootView.findViewById(R.id.ivNext);
		tvLai=(TextView) rootView.findViewById(R.id.tvTLai);
		tvDoanthu=(TextView) rootView.findViewById(R.id.tvTdoanhthu);
		db2=new database2(getActivity().getApplicationContext());
		
		getTime();
		toListview();
		
		ivBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(thang==1){
					thang=13;
					nam=nam-1;
				}
				thang=thang-1;
				tvDay.setText(thang+"/"+nam);
				toListview();
			}
		});
		
		ivNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(thang==12){
					thang=0;
					nam=nam+1;
				}
				thang=thang+1;
				tvDay.setText(thang+"/"+nam);
				toListview();
			}
		});
	    return rootView;
	}
	private static Fragment fragment = null;
	// toListview 
	void toListview(){
		arrBan=queryDate(tvDay.getText()+"");
		Log.d("-----size----",arrBan.size()+"");
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
		int l=db2.toLai(arrBan);
		tvLai.setText("Lãi:"+l);
		int q=db2.toDoanhthu(arrBan);
		tvDoanthu.setText("Doanh thu:"+q);
	}
	
	//hàm lấy ra tất cả giao dịch trong thánng
		public ArrayList<Table_cf> queryDate(String thang){
			ArrayList<Table_cf> arr1=db2.getTable();
			ArrayList<Table_cf> arrTo=new ArrayList<Table_cf>();
			
			for(int i=0;i<arr1.size();i++){
				Log.d("-----", arr1.get(i).getDaTe().toString());
				String s=arr1.get(i).getDaTe().substring(3,arr1.get(i).getDaTe().length());
				Log.d("----------",s);
				if(thang.equals(s)){
					arrTo.add(arr1.get(i));
				}
			}
			
			return arrTo;
		}
		
		//Hàm lấy thời gian, ngày,tháng, năm hiện tại
		public void getTime()
		{
			 Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		      
		        //Date currentTime = localCalendar.getTime();
		        int currentDay = localCalendar.get(Calendar.DATE);
		        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
		        int currentYear = localCalendar.get(Calendar.YEAR);
		         ngay=currentDay;
		         nam=currentYear;
		         thang=currentMonth;
		         tvDay.setText(thang+"/"+nam);
//		        int currentDayOfWeek = localCalendar.get(Calendar.DAY_OF_WEEK);
//		        int currentDayOfMonth = localCalendar.get(Calendar.DAY_OF_MONTH);
//		        int CurrentDayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);

		        System.out.println("Current Date and time details in local timezone");
		       // System.out.println("Current Date: " + currentTime);
		        System.out.println("Current Day: " + currentDay);
		        System.out.println("Current Month: " + currentMonth);
		        System.out.println("Current Year: " + currentYear);
		}
	
}
