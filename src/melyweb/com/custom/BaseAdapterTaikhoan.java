package melyweb.com.custom;

import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;

import melyweb.com.ActivityQuanly.Quanly_Activity;
import melyweb.com.database.database;
import melyweb.com.java.TaiKhoan;
import melyweb.com.rycafe.R;
import android.app.Activity;
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

public class BaseAdapterTaikhoan extends BaseAdapter {
	Context cont;
	database db;
	ArrayList<TaiKhoan> myList = new ArrayList<TaiKhoan>();
	LayoutInflater inflater;
	Context context;
	int pos;
	public BaseAdapterTaikhoan(Context context, ArrayList<TaiKhoan> myList) {
		db=new database(context);
		this.myList = myList;
		this.context = context;
		inflater = LayoutInflater.from(this.context); // only context can also
														// be used
	}
    
	@Override
	public int getCount() {
		return myList.size();
	}

	@Override
	public TaiKhoan getItem(int position) {
		return myList.get(position);
	}
	public int getPosition(int p){
		return p;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
    
	private class MyViewHolder {
		TextView tvTen, tvMK;
		TextView tvST;
		ImageView ivDele,ivSua;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyViewHolder mViewHolder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_adapter_taikhoan, null);
			mViewHolder = new MyViewHolder();
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (MyViewHolder) convertView.getTag();
		}
		
		mViewHolder.tvST = detail(convertView, R.id.tvID,
				position+1+"");
		mViewHolder.tvTen= detail(convertView, R.id.tvTen,
				"Username: "+myList.get(position).getTenTK());
		
		//mViewHolder.tvST = detail(convertView, R.id.tvMK, myList.get(position).getMaTaikhan());
		
		mViewHolder.tvMK = detail(convertView, R.id.tvMK,
				"Password: "+myList.get(position).getMatKhau());
		mViewHolder.ivDele= (ImageView) convertView.findViewById(R.id.ivDele);
		mViewHolder.ivSua=(ImageView) convertView.findViewById(R.id.ivEdit);
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
											 Log.d("-------thu----------",myList.get(pos).getMaTaikhan()+"");
											if(myList.get(pos).getMaTaikhan()==1){
												Toast.makeText(context,
														"Đây là tài khoản của bạn dùng để quản lý, không thể xóa được",
														Toast.LENGTH_LONG).show();
											} else {
												int e=db.xoaLop(myList.get(pos).getMaTaikhan());
												if(e==-1){
													Toast.makeText(context,
															"Xóa không thành công!",
															Toast.LENGTH_SHORT).show();
											
												} else{
													
													myList.remove(pos);
													notifyDataSetChanged();
												}
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
				View promptsView = li.inflate(R.layout.popup_taikhoan, null);
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setView(promptsView);
				alertDialogBuilder.setTitle("Sửa Tài Khoản:");
				alertDialogBuilder.setIcon(R.drawable.ic_edit);
				
				final EditText edTen=(EditText) promptsView.findViewById(R.id.edTenDN);
				edTen.setText(myList.get(pos).getTenTK());
				final EditText edPass=(EditText) promptsView.findViewById(R.id.edMatkhau);
				edPass.setText(myList.get(pos).getMatKhau());
				
				alertDialogBuilder
						
						.setCancelable(false)
						.setNegativeButton("Sửa",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										
										db=new database(context);
										try {
											int e=db.updateTaikhoan(myList.get(pos).getMaTaikhan()
																			,edTen.getText().toString(),edPass.getText().toString());
											if(e==-1){
												Toast.makeText(context,
														"Sửa không thành công!",
														Toast.LENGTH_SHORT).show();
											} else{
												myList=db.getTaikhoan();
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

	private ImageView detail1(View v, int resId, int icon) {
		ImageView iv = (ImageView) v.findViewById(resId);
		iv.setImageResource(icon); //
		return iv;
	}

  
  

}
