package melyweb.com.custom;

import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;

import melyweb.com.database.database;
import melyweb.com.java.DoUong;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapterDanhmuc extends BaseAdapter{
	database db;
	ArrayList<DoUong> myList = new ArrayList<DoUong>();
	LayoutInflater inflater;
	Context context;
	int pos;
	public CustomAdapterDanhmuc(Context context,ArrayList<DoUong>myList){
		this.myList = myList;
		this.context = context;
		inflater = LayoutInflater.from(this.context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myList.size();
	}

	@Override
	public DoUong getItem(int position) {
		// TODO Auto-generated method stub
		return myList.get(position);
	}
   	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	private class MyViewHolder {
		TextView tvTendouong,tvGianhap,tvGiaban,tvGhichu,tvMadouong;
		
		ImageView ivDele,ivSua;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyViewHolder mViewHolder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_adapter_danhmuc, null);
			mViewHolder = new MyViewHolder();
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (MyViewHolder) convertView.getTag();
		}
		mViewHolder.tvTendouong=detail(convertView,R.id.tvTendouong,myList.get(position).getTenDouong());
		mViewHolder.tvMadouong=detail(convertView, R.id.tvMadoduong,"ID:"+myList.get(position).getMaDouong());
		mViewHolder.tvGianhap=detail(convertView, R.id.tvGianhap,"Giá Nhập:"+myList.get(position).getGiaNhap());
		mViewHolder.tvGiaban=detail(convertView, R.id.tvGiaban,"Giá bán:"+myList.get(position).getGiaBan());
		mViewHolder.tvGhichu=detail(convertView, R.id.tvGhichu,"Ghi chú:"+myList.get(position).getChuThich());
		mViewHolder.ivDele=(ImageView) convertView.findViewById(R.id.ivDelete2);
		mViewHolder.ivSua=(ImageView) convertView.findViewById(R.id.ivEdit2);
		mViewHolder.ivDele.setTag(position);
		mViewHolder.ivSua.setTag(position);
		mViewHolder.ivDele.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pos = Integer.parseInt(v.getTag().toString());
		        Log.d("-------thu----------",pos+"");
		        
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
										
										db=new database(context);
										
										try {
											 Log.d("-------thu----------",pos+"");
										
												int e=db.xoaDouong(myList.get(pos).getMaDouong());
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
		
		mViewHolder.ivSua.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pos = Integer.parseInt(v.getTag().toString());
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.popup_douong, null);
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setView(promptsView);
				alertDialogBuilder.setTitle("Sửa Tài Khoản:");
				alertDialogBuilder.setIcon(R.drawable.ic_edit);
				
				  final EditText edMa=(EditText) promptsView.findViewById(R.id.edMadouong);
				  edMa.setText(myList.get(pos).getMaDouong()+"");
			      final EditText edTen=(EditText) promptsView.findViewById(R.id.edTendouong);
			      edTen.setText(myList.get(pos).getTenDouong()+"");
			      final EditText edGianhap=(EditText) promptsView.findViewById(R.id.edGianhap);
				  edGianhap.setText(myList.get(pos).getGiaNhap()+"");
			      final EditText edGiaban=(EditText) promptsView.findViewById(R.id.edGiaban);
			      edGiaban.setText(myList.get(pos).getGiaBan()+"");
			      final EditText edGhichu=(EditText) promptsView.findViewById(R.id.edGhichu);
			      edGhichu.setText(myList.get(pos).getChuThich()+"");
				
				alertDialogBuilder
						
						.setCancelable(false)
						.setNegativeButton("Sửa",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										
										db=new database(context);
										DoUong du=new DoUong();
										du.setMaDouong(edMa.getText()+"");
										du.setTenDouong(edTen.getText()+"");
										du.setGiaBan(Integer.parseInt(edGiaban.getText()+""));
										du.setGiaNhap(Integer.parseInt(edGianhap.getText()+""));
										du.setChuThich(edGhichu.getText()+"");
										try {
											int e=db.updateDouong(myList.get(pos).getMaDouong(),du);
											if(e==-1){
												Toast.makeText(context,
														"Sửa không thành công!",
														Toast.LENGTH_SHORT).show();
											} else{
												myList=db.getDanhmuc();
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
