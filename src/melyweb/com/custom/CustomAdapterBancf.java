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

public class CustomAdapterBancf extends BaseAdapter{
	database2 db;
	ArrayList<Table_cf> myList = new ArrayList<Table_cf>();
	LayoutInflater inflater;
	Context context;
	int pos;
	public CustomAdapterBancf(Context context,ArrayList<Table_cf> myList){
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
		TextView tvTenban,tvTrangthai,tvTienban;
		ImageView ivDel;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyViewHolder mViewHolder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_adapte_bancf, null);
			mViewHolder = new MyViewHolder();
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (MyViewHolder) convertView.getTag();
		}
		mViewHolder.tvTenban=detail(convertView, R.id.tvTenban,myList.get(position).getTenBan()+"");
		if(myList.get(position).getIdTrangthai()==0){
			mViewHolder.tvTrangthai=detail(convertView, R.id.tvTrangthai,"Chưa thanh toán");
		} else {
			mViewHolder.tvTrangthai=detail(convertView, R.id.tvTrangthai,"Đã thanh toán");
		}
		mViewHolder.ivDel=(ImageView) convertView.findViewById(R.id.ivDelete3);
		mViewHolder.tvTienban=detail(convertView, R.id.tvTongtien01,"Tiền:"+myList.get(position).getSoTien());
		mViewHolder.ivDel.setTag(position);
		mViewHolder.ivDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pos = Integer.parseInt(v.getTag().toString());
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);

				alertDialogBuilder.setTitle("Xác nhận");
				alertDialogBuilder.setIcon(R.drawable.ic_delete);
				alertDialogBuilder
						.setMessage("Bạn chắc muốn mục này?")
						.setCancelable(false)
						.setNegativeButton("Xóa",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										
										db=new database2(context);
										
										try {
											 Log.d("-------thu----------",pos+"");
										
												int e=db.xoaban(myList.get(pos).getMaBan());
												if(e==-1){
													Toast.makeText(context,
															"Xóa không thành công!",
															Toast.LENGTH_SHORT).show();
											
												} else{
													
													myList.remove(pos);
													notifyDataSetChanged();
												}
											
										
										
										} catch (Exception e) {

										}

										

									}

								})
						.setPositiveButton("Hủy",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										dialog.cancel();
									}
								});

				AlertDialog alertDialog = alertDialogBuilder.create();

				alertDialog.show();
			}
		});
		return convertView;
	}
	
	private TextView detail(View v, int resId, String text) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(text);
		return tv;
	}

}
