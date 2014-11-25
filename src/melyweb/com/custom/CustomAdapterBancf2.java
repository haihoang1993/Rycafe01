package melyweb.com.custom;

import java.util.ArrayList;

import melyweb.com.database.database;
import melyweb.com.database.database2;
import melyweb.com.java.Table_cf;
import melyweb.com.rycafe.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapterBancf2 extends BaseAdapter{
	database2 db;
	ArrayList<Table_cf> myList = new ArrayList<Table_cf>();
	LayoutInflater inflater;
	Context context;
	int pos;
	public CustomAdapterBancf2(Context context,ArrayList<Table_cf> myList){
		this.context=context;		
		this.myList=myList;
		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myList.size();
	}

	@Override
	public Table_cf getItem(int position) {
		// TODO Auto-generated method stub
		return myList.get(position);
	}
   	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private class MyViewHolder {
		TextView tvTenban,tvTrangthai,tvSotien,tvGio,tvNgay;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyViewHolder mViewHolder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_adapte_bancf_thongke, null);
			mViewHolder = new MyViewHolder();
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (MyViewHolder) convertView.getTag();
		}
		mViewHolder.tvTenban=detail(convertView, R.id.tvTenbanTK2,myList.get(position).getTenBan()+"");
		if(myList.get(position).getIdTrangthai()==0){
			mViewHolder.tvTrangthai=detail(convertView, R.id.tvTrangthai_tk2,"Chưa thanh toán");
		} else {
			mViewHolder.tvTrangthai=detail(convertView, R.id.tvTrangthai_tk2,"Đã thanh toán");
		}
		mViewHolder.tvNgay=detail(convertView, R.id.tvNgay_tk1,"Ngày:"+myList.get(position).getDaTe());
		mViewHolder.tvGio=detail(convertView, R.id.tvGio_TK1,"Giờ:"+myList.get(position).getTiMe());
		mViewHolder.tvSotien=detail(convertView, R.id.tvSotienTK2,"Số tiền: "+myList.get(position).getSoTien());
		return convertView;
	}
	
	private TextView detail(View v, int resId, String text) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(text);
		return tv;
	}

}
