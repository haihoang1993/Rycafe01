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

@SuppressLint("NewApi") public class CustumAdapterDouongban extends BaseAdapter {
	
	
	Context context;
	ArrayList<DoUongban> myList;
	LayoutInflater inflater;
	
	public CustumAdapterDouongban(Context context,ArrayList<DoUongban> myList){
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
		TextView tvTen, tvSoluong;
		ImageView ivXoa;
	}
   
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyViewHolder mViewHolder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_do_uong_ban2, null);
			mViewHolder = new MyViewHolder();
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (MyViewHolder) convertView.getTag();
		}
		
		mViewHolder.tvTen=detail(convertView,R.id.tvTendo01i,myList.get(position).getTenDouong()+"");
		mViewHolder.tvSoluong=detail(convertView, R.id.tvSoluong1i,myList.get(position).getSoLuong()+"");
		mViewHolder.ivXoa=(ImageView) convertView.findViewById(R.id.ivXoa1i);
//		mViewHolder.ivXoa.setTag(position);
//		mViewHolder.ivXoa.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				notifyDataSetChanged();
//			}
//		});
		
		return convertView;
	}
	
	private TextView detail(View v, int resId, String text) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(text);
		return tv;
	}

}
