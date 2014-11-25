package melyweb.com.FragmentQuanly;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import melyweb.com.database.database2;
import melyweb.com.java.DoUongban;
import melyweb.com.java.Table_cf;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class F_HomeQuanly extends Fragment {
	database2 db;
	Activity root;
	private int ngay,thang,nam,gio;
	private ArrayList<DoUongban> arrDouong;
	private ArrayList<Table_cf> arrBan=new ArrayList<Table_cf>();
	public F_HomeQuanly() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setDay();
		root = getActivity();
		db=new database2(getActivity());
		arrBan=db.getTabledate(ngay+"/"+thang+"/"+nam);
		int doanhThu=doThu(arrBan);
		int tienLai=doLai(arrBan);
		
		
		View rootView = inflater
				.inflate(R.layout.flasment_quanly_, container, false);
		TextView tvSoban=(TextView) rootView.findViewById(R.id.tvSoban0);
		tvSoban.setText(arrBan.size()+"");
		TextView tvDoanhthu=(TextView) rootView.findViewById(R.id.tvDoanhthu0);
		tvDoanhthu.setText(doanhThu+"");
		TextView tvTienlai=(TextView) rootView.findViewById(R.id.tvTienlai0);
		tvTienlai.setText(tienLai+"");
		return rootView;
	}
	
	int doThu(ArrayList<Table_cf> arr){
		int dt=0;
		for(int i=0;i<arr.size();i++){
			dt=dt+db.tongTienban(arr.get(i).getMaBan()+"");
		}
		return dt;
	}	
	
	 int doLai(ArrayList<Table_cf> arr){
		int dt=0;
		for(int i=0;i<arr.size();i++){
			dt=dt+db.tienLaiban(arr.get(i).getMaBan()+"");
		}
		return dt;
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
}
