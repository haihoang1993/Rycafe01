package melyweb.com.database;

import java.util.ArrayList;

import melyweb.com.java.DoUongban;
import melyweb.com.java.Table_cf;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class database2 extends SQLiteOpenHelper{

	 public database2(Context context) {
			super(context,"RyCafe_DB",null,1);
			// TODO Auto-generated constructor stub
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	//-------------------------------------------------------------
	//-------------------Thêm bàn cf---------------
	public int addBancf(Table_cf ba){
		int e=-1;
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues ct=new ContentValues();
		ct.put("Ma_ban", ba.getMaBan());
		ct.put("Ten_ban", ba.getTenBan());
		ct.put("Full_date",ba.getDaTe());		
		ct.put("Time",ba.getTiMe());
		ct.put("So_tien",ba.getSoTien());
		ct.put("Trang_thai",ba.getIdTrangthai());
		e=(int)db.insert("QUAN_LY_BAN",null,ct);
		 if (e==-1){
	            Log.d("-------", "error in Table");
	            //Toast.makeText(Activity_LopHoc.class,"lỗi",Toast.LENGTH_LONG)

	        } else Log.d("------","Ok table");
		return e;
	}
	
	// ----------------Hàm get các bàn------------------     
    public ArrayList<Table_cf> getTable(){
   	 ArrayList<Table_cf> arrBancf=new ArrayList<Table_cf>();
   	 SQLiteDatabase db=this.getReadableDatabase();
   	 Cursor cursor=db.rawQuery("select * from QUAN_LY_BAN",null);
   	 if(cursor!=null){
   		 cursor.moveToFirst();
   		 while(cursor.isAfterLast()==false){
   			 Table_cf tb=new Table_cf();
   			 tb.setMaBan(cursor.getString(0));
   			 tb.setTenBan(cursor.getString(1));
   			 tb.setDaTe(cursor.getString(2));
   			 tb.setTiMe(cursor.getString(3));
   			 tb.setSoTien(Integer.parseInt(cursor.getInt(4)+""));
   			 tb.setIdTrangthai(Integer.parseInt(cursor.getInt(5)+""));
   			 arrBancf.add(tb);
   			 cursor.moveToNext();
   		 }
   	 }
   	 return arrBancf;
    }
 // ----------------Hàm get các bàn theo trạng thái ------------------     
    public ArrayList<Table_cf> getTable1(int trangThai){
   	 ArrayList<Table_cf> arrBancf=new ArrayList<Table_cf>();
   	 SQLiteDatabase db=this.getReadableDatabase();
   	 Cursor cursor=db.rawQuery("select * from QUAN_LY_BAN where Trang_thai=?",new String[]{trangThai+""});
   	 if(cursor!=null){
   		 cursor.moveToFirst();
   		 while(cursor.isAfterLast()==false){
   			 Table_cf tb=new Table_cf();
   			 tb.setMaBan(cursor.getString(0));
   			 tb.setTenBan(cursor.getString(1));
   			 tb.setDaTe(cursor.getString(2));
   			 tb.setTiMe(cursor.getString(3));
   			 tb.setSoTien(Integer.parseInt(cursor.getInt(4)+""));
   			 tb.setIdTrangthai(Integer.parseInt(cursor.getInt(5)+""));
   			 arrBancf.add(tb);
   			 cursor.moveToNext();
   		 }
   	 }
   	 return arrBancf;
    }
    // ------------Get các ngày theo bàn ----------
    public ArrayList<Table_cf> getTabledate(String ngay){
      	 ArrayList<Table_cf> arrBancf=new ArrayList<Table_cf>();
      	 SQLiteDatabase db=this.getReadableDatabase();
      	 Cursor cursor=db.rawQuery("select * from QUAN_LY_BAN where Full_date=?",new String[]{ngay});
      	 if(cursor!=null){
      		 cursor.moveToFirst();
      		 while(cursor.isAfterLast()==false){
      			 Table_cf tb=new Table_cf();
      			 tb.setMaBan(cursor.getString(0));
      			 tb.setTenBan(cursor.getString(1));
      			 tb.setDaTe(cursor.getString(2));
      			 tb.setTiMe(cursor.getString(3));
      			 tb.setSoTien(Integer.parseInt(cursor.getInt(4)+""));
      			 tb.setIdTrangthai(Integer.parseInt(cursor.getInt(5)+""));
      			 arrBancf.add(tb);
      			 cursor.moveToNext();
      		 }
      	 }
      	 return arrBancf;
       }
    // ----------------Xóa các bàn------------------     
    public int xoaban(String maBan){
        SQLiteDatabase db=this.getWritableDatabase();
        
        //xóa bàn ở bảng Quản ly bàn
        int e =db.delete("QUAN_LY_BAN","Ma_ban=?",new String[]{maBan+""});
        
        //Đồng thời xóa các đồ uống trong bàn
        
        if((e==-1)){
       	 Log.d("---------Xóa bàn-------","Xóa ko được");
       	 
        } else {
       	 Log.d("---------Xóa bàn-------","Xóa Thành công");
       	int a=db.delete("CHI_TIET_BAN", "Ma_ban=?", new String[]{maBan+""});
        }
        return e;
    }
   
    
    
    //-------------chi tiết bàn---------
    public void addDUt(DoUongban du){
       	SQLiteDatabase db=this.getWritableDatabase();
    	ContentValues values=new ContentValues();
//    	Ma_ban TEXT,Ma_do_uong TEXT,Ten_do_uong TEXT,Gia_nhap NUMERIC,Gia_ban NUMERIC,So_luong INTEGER,So_tien NUMERIC,Da_te TEXT
    	values.put("Ma_ban",du.getMaBan());
    	values.put("Ma_do_uong", du.getMaDouong());
    	values.put("Ten_do_uong",du.getTenDouong());
    	values.put("Gia_nhap", du.getGiaNhap());
    	values.put("Gia_ban",du.getGiaBan());
    	values.put("So_luong",du.getSoLuong());
    	values.put("So_tien",du.getSoTien());
    	values.put("Da_te", du.getDaTe());
    	 int error = (int) db.insert("CHI_TIET_BAN",null,values);
	        if (error==-1){
	        	Log.d("-------", "khong thê thêm đồ uống vào bàn");
	        	//Toast.makeText(Activity_LopHoc.class,"lỗi",Toast.LENGTH_LONG)

	        } else Log.d("------","Ok");
    }
    
    
    //--------- Lấy ra danh sách đồ uống trong bàn -----------
    public ArrayList<DoUongban> getDouongban(String maban){
    	int e=0;
    	ArrayList<DoUongban> arrDouongban=new ArrayList<DoUongban>();
    	SQLiteDatabase db=this.getReadableDatabase();
    	Cursor cursor=db.rawQuery("select * from CHI_TIET_BAN where Ma_ban=?",new String[]{maban});
    	 if(cursor!=null){
       		 cursor.moveToFirst();
       		 while(cursor.isAfterLast()==false){
       			 DoUongban du=new DoUongban();
       			 du.setMaBan(cursor.getString(0));
       			 du.setMaDouong(cursor.getString(1));
       			 du.setTenDouong(cursor.getString(2));
       			 du.setGiaNhap(cursor.getInt(3));
       			 du.setGiaBan(cursor.getInt(4));
       			 du.setSoLuong(cursor.getInt(5));
       			 du.setSoTien(cursor.getInt(6));
       			 du.setDaTe(cursor.getString(7));
       			 arrDouongban.add(du);
       			 cursor.moveToNext();
       		 }
       	 }
    	return arrDouongban;
    }
    
    //---------Update số lượng và tổng tiên cho đồ uông bàn---------
    public int upDatedouongBan(String maBan,String maDouong,int soLuong,int soTien){
    	int e=-1;
    	 SQLiteDatabase db=this.getWritableDatabase();
    	 ContentValues ct=new ContentValues();
         ct.put("So_luong", soLuong);
    	 ct.put("So_tien", soTien);
    	 e=db.update("CHI_TIET_BAN",ct,"Ma_ban=? and Ma_do_uong=?",new String[]{maBan,maDouong});
    	 if(e==-1){
    		 Log.d("--sửa đồ uống trong bàn ---","Lỗi");
    	 } else {
    		 Log.d("--sửa đồ uống trong bàn ---","ok");
		}
    	return e;
    }
    //---------Update số tiền hco bàn-----
    public int upDateSotienban(String maBan,int soTien){
    	SQLiteDatabase db=this.getWritableDatabase();
    	ContentValues ct=new ContentValues();
        ct.put("So_tien", soTien);
   	 int e=db.update("QUAN_LY_BAN",ct,"Ma_ban=? ",new String[]{maBan});
	   	if(e==-1){
			 Log.d("--Sửa số tiền bàn ---","Lỗi");
		 } else {
			 Log.d("--Sửa số tiền bàn ---","ok");
		}
	   	return e;
    }
    //---------Update số tiền hco bàn-----
    public int upDatetrangthai(String maBan,int trangThai){
    	SQLiteDatabase db=this.getWritableDatabase();
    	ContentValues ct=new ContentValues();
        ct.put("Trang_thai", trangThai);
   	 int e=db.update("QUAN_LY_BAN",ct,"Ma_ban=? ",new String[]{maBan});
	   	if(e==-1){
			 Log.d("--Sửa số trạng thái ---","Lỗi");
		 } else {
			 Log.d("--Sửa số trạng thái ---","ok");
		}
	   	return e;
    }
    //---------Xóa đồ uống trong chi tiet bàn ---
    public void xoaDuTa(String maBan,String maDouong){
    	SQLiteDatabase db=this.getWritableDatabase();
    	db.delete("CHI_TIET_BAN","Ma_ban=? and Ma_do_uong=?",new String[]{maBan,maDouong});
    }
    
    //----------Tính tổng tiền trong bàn:
    public int tongTienban(String maBan){
    	int tong=0;
    	SQLiteDatabase db=this.getWritableDatabase();
    	tong=(int) DatabaseUtils.longForQuery(db,"select sum(So_tien) from CHI_TIET_BAN where Ma_ban=?",new String[]{maBan});
    	return tong;
    }
    
    //---------- số tiền lãi bàn--------
    public int tienLaiban(String maBan){
    	int tempX=0;
    	int l=0;
    	ArrayList<DoUongban> arrdoUb=new ArrayList<DoUongban>();
    	arrdoUb=getDouongban(maBan);
    	for(int i=0;i<arrdoUb.size();i++){
    		tempX=tempX+(arrdoUb.get(i).getGiaNhap()*arrdoUb.get(i).getSoLuong());
    	}
    	int tempT=tongTienban(maBan);
    	Log.d("---"+tempX,"----"+tempT);
    	l=tempT-tempX;
    	return l;
    }
    //---------- số tiền lãi bàn--------
    //---------tính lãi,doanh thu của nhiều bàn
    public int toDoanhthu(ArrayList<Table_cf> arrBan){
			   	 int l=0;
			   	 for(int i=0;i<arrBan.size();i++){
			   		 l=l+arrBan.get(i).getSoTien();
			   	 }
   	 return l;
    }
    public int toLai(ArrayList<Table_cf> arrBan){
	   	 int l=0;
	   	 for(int i=0;i<arrBan.size();i++){
	   		 l=l+tienLaiban(arrBan.get(i).getMaBan());
	   	 }
return l;
}
}
