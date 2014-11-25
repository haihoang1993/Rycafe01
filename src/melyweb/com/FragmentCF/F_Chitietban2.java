package melyweb.com.FragmentCF;

import java.util.ArrayList;

import melyweb.com.custom.CustumAdapterDouongban2;
import melyweb.com.database.database2;
import melyweb.com.java.DoUongban;
import melyweb.com.java.Table_cf;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class F_Chitietban2 extends Fragment{
	
	private Table_cf tb;
	private ArrayList<DoUongban> arrayDouong;
	private TextView tvTenban,tvNgay,TvGio,tvTong,tvTienlai;
	BaseAdapter adap;
	ListView lvBanch;
	private database2 db;
	public F_Chitietban2(){
		
	}
	public F_Chitietban2(Table_cf tb,ArrayList<DoUongban> arrDouong){
			this.tb=tb;
			this.arrayDouong=arrDouong;
		}
	private View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		db=new database2(getActivity());
		rootView = inflater
				.inflate(R.layout.f_chitiet_table, container, false);
		khoiTao();
		lvBanch=(ListView) rootView.findViewById(R.id.lvDouongban00);
		adap=new CustumAdapterDouongban2(getActivity(), arrayDouong);
		lvBanch.setAdapter(adap);
		return rootView;
	}
	public void khoiTao(){
		tvTenban=detail(rootView, R.id.tvTenban00,tb.getTenBan());
		tvNgay=detail(rootView, R.id.tvNgay00,"Ngay:"+tb.getDaTe());
		TvGio=detail(rootView, R.id.tvThoigian00,"Giờ:"+tb.getTiMe());
		int t=db.tongTienban(tb.getMaBan());
	     tvTong=detail(rootView,R.id.tvTongtienban00,"Tiền bàn:"+t);
	     int l=0;
	     if(t==0){
	    	 l=0;
	     }	else {
		     l=db.tienLaiban(tb.getMaBan());
		     Log.d("-----L--",l+"");
		}
	     tvTienlai=detail(rootView, R.id.tvTienlaiban00,"Hãy đăng nhập vào tk admin để xem chi tiết hơn");
	}
	
	
	private TextView detail(View v, int resId, String text) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(text);
		return tv;
	}
}
