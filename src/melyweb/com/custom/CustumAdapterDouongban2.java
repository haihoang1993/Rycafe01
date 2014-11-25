package melyweb.com.custom;

import java.util.ArrayList;

import melyweb.com.FragmentCF.F_toTable;
import melyweb.com.java.DoUongban;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi") public class CustumAdapterDouongban2 extends BaseAdapter {
	
	
	Context context;
	ArrayList<DoUongban> myList;
	LayoutInflater inflater;
	
	public CustumAdapterDouongban2(Context context,ArrayList<DoUongban> myList){
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
	public DoUongban getItem(int position) {
		// TODO Auto-generated method stub
		return myList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
	public class MyViewHolder {
		TextView tvTen, tvSoluong,tvSotien;
	}
   
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyViewHolder mViewHolder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_do_uong_ban, null);
			mViewHolder = new MyViewHolder();
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (MyViewHolder) convertView.getTag();
		}
		
		mViewHolder.tvTen=detail(convertView,R.id.tvTendo01,myList.get(position).getTenDouong()+"");
		mViewHolder.tvSoluong=detail(convertView, R.id.tvSoluong1,myList.get(position).getSoLuong()+"");
		mViewHolder.tvSotien=detail(convertView, R.id.tvSongtien01,myList.get(position).getSoTien()+"");
		return convertView;
	}
	
	private TextView detail(View v, int resId, String text) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(text);
		return tv;
	}

}
