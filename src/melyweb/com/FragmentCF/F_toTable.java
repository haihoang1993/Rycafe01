package melyweb.com.FragmentCF;

import java.util.ArrayList;

import melyweb.com.custom.CustumAdapterDouongban;
import melyweb.com.custom.CustumAdapterDouongban.MyViewHolder;
import melyweb.com.database.database;
import melyweb.com.database.database2;
import melyweb.com.java.DoUong;
import melyweb.com.java.DoUongban;
import melyweb.com.java.Table_cf;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") 
public class F_toTable extends Fragment{
	ImageButton btThanhtoan;
	 MyViewHolder tam;
	public ArrayList<DoUongban> arrDouongban=new ArrayList<DoUongban>();
	
	TextView tvTenban;
	Table_cf table;
	BaseAdapter adapDouongban;
	ArrayList<DoUong> arrDouong=new ArrayList<DoUong>();
	ListView lvDouongban;
	
	
	View rootView;
	private database db1;
	private database2 db2;
	private ListView lvList1,lvList2;
	private TextView tvTongtien;
	public F_toTable(Table_cf tb){
			table=tb;
		}
	public F_toTable(){
		
	}
	
	
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		db1=new database(getActivity().getApplicationContext());
		db2=new database2(getActivity().getApplicationContext());
		rootView= inflater
				.inflate(R.layout.f_totable, container, false);	
		khoiTao();
		toLvdouongban();
		Log.d("----đồ uống bàn-----",arrDouongban.size()+"");
		
				return rootView;
		
	}
	private static Fragment fragment = null;
	public void khoiTao(){
		//--------- textvew tên bàn ------------------
		tvTenban=(TextView) rootView.findViewById(R.id.tvTenban0);
		String tenban=table.getTenBan().toString();
		tvTenban.setText(tenban);
		lvDouongban=(ListView) rootView.findViewById(R.id.lvDouongban);
		tvTongtien=(TextView) rootView.findViewById(R.id.tvTongtien);
		btThanhtoan=(ImageButton) rootView.findViewById(R.id.btThanhtoan);
		//------------------------------------------
		
		//---------Danh sách đồ uống-----------
		lvList1=(ListView) rootView.findViewById(R.id.lvList1);
		lvList2=(ListView) rootView.findViewById(R.id.lvList2);
		arrDouong=db1.getDanhmuc();
		Log.d("Heloo size---",arrDouong.size()+"");
		int a=(int) arrDouong.size()/2;
		Log.d("a:",a+"");
		showMenu(arrDouong,a);
		
		btThanhtoan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int e=db2.upDatetrangthai(table.getMaBan(),1);
				if(e==-1){
					Toast.makeText(getActivity().getApplicationContext(),"Thanh toán bị lỗi",Toast.LENGTH_SHORT).show();
					
				}else {
					Toast.makeText(getActivity().getApplicationContext(),"Thanh toán thành công",Toast.LENGTH_SHORT).show();
					fragment = new F_BanCf();
					FragmentManager fragmentManager = getFragmentManager();						
					fragmentManager.beginTransaction().addToBackStack(null)
							.replace(R.id.frame_container1, fragment).commit();
				}
			}
		});
	}
	
	//--------- Thêm đồ uống vào bàn---------
	public void addTotable(DoUong doUong){
		int e=toFind(doUong.getMaDouong().toString());
		if(e==0){
			db2.addDUt(new DoUongban(					
					table.getMaBan(),doUong.getMaDouong(), doUong.getTenDouong(),doUong.getGiaNhap(),doUong.getGiaBan(),1, doUong.getGiaBan(),table.getDaTe()));
			   toLvdouongban();
		} else{
				e++;
				int soTien=e*doUong.getGiaBan();
				db2.upDatedouongBan(table.getMaBan().toString(),doUong.getMaDouong().toString(),e, soTien);
				   toLvdouongban();
		}
	}
	
	
	
	//------------Kiểm tra trong bàn đã có đồ uống này chưa-------
	int toFind(String maDouong){
		int e=0;
		for(int i=0;i<arrDouongban.size();i++){
			if(maDouong.equals(arrDouongban.get(i).getMaDouong().toString())){
				e=arrDouongban.get(i).getSoLuong();
				break;
			} 
		}
		return e;
	}
	
		
	public void xoaDouonglv(int p){
		Log.d("----d---",p+"");
		
		int soLuong=arrDouongban.get(p).getSoLuong();
		if(soLuong==1){
        	db2.xoaDuTa(table.getMaBan(),arrDouongban.get(p).getMaDouong());
        } else {
			soLuong--;
			int soTien=(int) arrDouong.get(p).getGiaBan()*soLuong;
			db2.upDatedouongBan(table.getMaBan().toString(),arrDouongban.get(p).getMaDouong(),soLuong,soTien);
		}
		
        toLvdouongban();
	}
	
	//Hàm set dữ liệu cho l
	public void toLvdouongban(){
		arrDouongban=db2.getDouongban(table.getMaBan().toString());
		adapDouongban=new CustumAdapterDouongban(getActivity(),arrDouongban);
		
		lvDouongban.setAdapter(adapDouongban);
		int tongTien=db2.tongTienban(table.getMaBan());
		db2.upDateSotienban(table.getMaBan(), tongTien);
		Log.d("---Tổng tiền----", tongTien+"");
		tvTongtien.setText(tongTien+"");
		lvDouongban.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				xoaDouonglv(position);
			}			
		});
	}
	
	//Danh sách các loại đồ uống
	ArrayList<DoUong> arrDouong1,arrDouong2;
	ArrayAdapter<DoUong>adapL1,adapL2=null;
	void showMenu(ArrayList<DoUong> arrDo,int a){
		arrDouong1=new ArrayList<DoUong>();	
		arrDouong2=new ArrayList<DoUong>();	
	
		if(a>0){
			for(int i=0;i<a;i++){
				arrDouong1.add(arrDo.get(i));
				
			}
			for(int i=a;i<arrDo.size();i++){
				arrDouong2.add(arrDo.get(i));
				
			}
		}
		adapL1=new ArrayAdapter<DoUong>(getActivity(),android.R.layout.simple_list_item_1, arrDouong1);
		lvList1.setAdapter(adapL1);
		adapL2=new ArrayAdapter<DoUong>(getActivity(),android.R.layout.simple_list_item_1, arrDouong2);
		lvList2.setAdapter(adapL2);
		
		lvList1.setOnItemClickListener(new OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> adapter, View view, int i, long arg) {
				   addTotable(arrDouong1.get(i));
				   
			   } 
			});
		lvList2.setOnItemClickListener(new OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> adapter, View view, int i, long arg) {
				   addTotable(arrDouong2.get(i));
				  
			   } 
			});
		
	}
}
