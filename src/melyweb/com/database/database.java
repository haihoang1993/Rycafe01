package melyweb.com.database;

import java.util.ArrayList;

import melyweb.com.java.DoUong;
import melyweb.com.java.Table_cf;
import melyweb.com.java.TaiKhoan;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class database extends SQLiteOpenHelper {
	//Biến để tạo bảng tài khoản
	 private static final String CREATE_TABLE_TAIKHOAN=
				"CREATE TABLE TAI_KHOAN(ID INTEGER PRIMARY KEY  AUTOINCREMENT,Name TEXT,Pass TEXT)";
    private static final String CREATE_TABLE_DOUONG="CREATE TABLE DO_UONG(" +
    		"Ma_do_uong TEXT PRIMARY KEY, Ten_do_uong TEXT, Gia_nhap NUMERIC, Gia_ban NUMERIC, Chu_thich Text)";
	
    private static final String CREATE_TABLE_BAN="CREATE TABLE QUAN_LY_BAN(Ma_ban TEXT PRIMARY KEY,Ten_ban TEXT,Full_date TEXT,Time TEXT,So_tien NUMERIC,Trang_thai TEXT)";
    
    private static final String CREATE_TABLE_CHI_TIET="CREATE TABLE CHI_TIET_BAN(Ma_ban TEXT,Ma_do_uong TEXT,Ten_do_uong TEXT,Gia_nhap NUMERIC,Gia_ban NUMERIC,So_luong INTEGER,So_tien NUMERIC,Da_te TEXT)";
    
    public database(Context context) {
		super(context,"RyCafe_DB",null,1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_TAIKHOAN);
		db.execSQL(CREATE_TABLE_DOUONG);
	    db.execSQL(CREATE_TABLE_BAN);
	    db.execSQL(CREATE_TABLE_CHI_TIET);
		 
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS TAI_KHOAN");
		db.execSQL("DROP TABLE IF EXISTS DO_UONG");
     	db.execSQL("DROP TABLE IF EXISTS QUAN_LY_BAN");
     	db.execSQL("DROP TABLE IF EXISTS CHI_TIET_BAN");
		onCreate(db);
	}
	
	
   //----------------------------------------------------------
   //----------------- Thêm tài khoản vào databse---------------
		//trả về -1 và 0
		public int addCACKHOAN(TaiKhoan tk){
	        SQLiteDatabase db=this.getWritableDatabase();
	        ContentValues values=new ContentValues();
	       // values.put("idLoaiGD",khoan.getIdLoaiGD());
	        values.put("Name",tk.getTenTK());
	        values.put("Pass",tk.getMatKhau());
	        int error = (int) db.insert("TAI_KHOAN",null,values);
	        if (error==-1){
	        	Log.d("-------", "error in");
	        	//Toast.makeText(Activity_LopHoc.class,"lỗi",Toast.LENGTH_LONG)

	        } else Log.d("------","Ok");

	        return error;
	    }
		
		//-------------------------------------------------------------
		//-------------------Ham thêm đồ uống vào danh mực--------------
		public int addDouong(DoUong d){
			int e=1;
			SQLiteDatabase db=this.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put("Ma_do_uong", d.getMaDouong());
			values.put("Ten_do_uong",d.getTenDouong());
			values.put("Gia_nhap", d.getGiaNhap());
			values.put("Gia_ban", d.getGiaBan());
			values.put("Chu_thich", d.getChuThich());
			e=(int)db.insert("DO_UONG", null, values);
			 if (e==-1){
		            Log.d("-------", "error in");
		            //Toast.makeText(Activity_LopHoc.class,"lỗi",Toast.LENGTH_LONG)

		        } else Log.d("------","Ok");
			return e;
		}	
		
	
	//-------------------------------------------------------------
	//-------------------Lấy ra tất cả các tài khoản---------------
	     public ArrayList<TaiKhoan> getTaikhoan(){
	    	
	    	        ArrayList<TaiKhoan> dsCackhoan=new ArrayList<TaiKhoan>();

	    	        SQLiteDatabase db = this.getWritableDatabase();

	    	        Cursor cursor= db.rawQuery("Select * from TAI_KHOAN",null);
	    	        if (cursor!=null) {
	    	            cursor.moveToFirst();
	    	            Log.d("----Log-----"," query ok");
	    	            while (cursor.isAfterLast() == false) {
	    	                 TaiKhoan rowNew = new TaiKhoan();
	    	                // rowNew.setStt(stt);
	    	                rowNew.setMaTaikhan(cursor.getInt(0));
	    	                rowNew.setTenTK(cursor.getString(1));
	    	                rowNew.setMatKhau(cursor.getString(2));
	    	                dsCackhoan.add(rowNew) ;
	    	                cursor.moveToNext();

	    	        }
	    	        }else{
	    	            Log.d("---LOG---","error query");
	    	            
	    	        }

	    	        cursor.close();
	    	        return dsCackhoan;
	     }

    //------------------Hàm get danh mục --------------
	     public ArrayList<DoUong> getDanhmuc(){
	    	 ArrayList<DoUong> arrDouong=new ArrayList<DoUong>();
	    	 SQLiteDatabase db=this.getReadableDatabase();
	    	 Cursor cursor=db.rawQuery("select * from DO_UONG",null);
	    	 if(cursor!=null){
	    		 cursor.moveToFirst();
	    		 Log.d("----Log-----"," query ok");
	    		  while (cursor.isAfterLast() == false) {
 	                 DoUong rowNew = new DoUong();
 	                // rowNew.setStt(stt);
 	                rowNew.setMaDouong(cursor.getString(0));
 	                rowNew.setTenDouong(cursor.getString(1));
 	                rowNew.setGiaNhap(Integer.parseInt(cursor.getString(2)+""));
 	                rowNew.setGiaBan(Integer.parseInt(cursor.getString(3)+""));
 	                rowNew.setChuThich(cursor.getString(4));
 	                arrDouong.add(rowNew) ;
 	                cursor.moveToNext();

	    		  }
	    	 }
	    	 return arrDouong;
	     }
	     
//	  // ----------------Hàm ghet các bàn------------------     
//	     public ArrayList<Table_cf> getTable(){
//	    	 ArrayList<Table_cf> arrBancf=new ArrayList<Table_cf>();
//	    	 SQLiteDatabase db=this.getReadableDatabase();
//	    	 Cursor cursor=db.rawQuery("select * from QUAN_LY_BAN",null);
//	    	 if(cursor!=null){
//	    		 cursor.moveToFirst();
//	    		 while(cursor.isAfterLast()==false){
//	    			 Table_cf tb=new Table_cf();
//	    			 tb.setMaBan(cursor.getString(0));
//	    			 tb.setTenBan(cursor.getString(1));
//	    			 tb.setDaTe(cursor.getString(2));
//	    			 tb.setTiMe(cursor.getString(3));
//	    			 tb.setSoTien(Integer.parseInt(cursor.getInt(4)+""));
//	    			 tb.setIdTrangthai(Integer.parseInt(cursor.getInt(5)+""));
//	    			 arrBancf.add(tb);
//	    			 cursor.moveToNext();
//	    		 }
//	    	 }
//	    	 return arrBancf;
//	     }
	     
	// ----------------Xóa tài khoản------------------
	     public int xoaLop(int maTaikhoan){
	         SQLiteDatabase db=this.getWritableDatabase();
	         int e =db.delete("TAI_KHOAN","ID=?",new String[]{maTaikhoan+""});
	         if(e==-1){
	        	 Log.d("---------Xóa-------","Xóa ko được");
	        	 
	         } else {
	        	 Log.d("---------Xóa-------","Xóa Thành công");
	         }
	         return e;
	     }
   
	//----------Hàm xóa đồ uống trong danh mục theo mã đồ uống--------     
	     public int xoaDouong(String maDouong){
	         SQLiteDatabase db=this.getWritableDatabase();
	         int e =db.delete("DO_UONG","Ma_do_uong=?",new String[]{maDouong+""});
	         if(e==-1){
	        	 Log.d("---------Xóa-------","Xóa ko được");
	        	 
	         } else {
	        	 Log.d("---------Xóa-------","Xóa Thành công");
	         }
	         return e;
	     }
	     
    //----------Hàm sửa danh mục------------------------
	     public int updateDouong(String maDouong,DoUong du){
	    	 int e=0;
	    	 SQLiteDatabase db=this.getWritableDatabase();
	    	 ContentValues values=new ContentValues();
	    	 	values.put("Ma_do_uong", du.getMaDouong());
				values.put("Ten_do_uong",du.getTenDouong());
				values.put("Gia_nhap",du.getGiaNhap());
				values.put("Gia_ban",du.getGiaBan());
				values.put("Chu_thich",du.getChuThich());
			e=db.update("DO_UONG", values,"Ma_do_uong=?", new String[]{maDouong});
			if(e==-1){
				Log.d("------------","Sửa đồ uống ko thành công");
			} else{
				Log.d("------------","Sửa đồ uống thành công");
			}
	    	 return e;
	     }   
	 
	 //-----------------Hàm sửa tài khoản --------------
	     public int updateTaikhoan(int maTK,String tenTatkhoan, String matKhau){
	    	 int e=-1;
	    	 SQLiteDatabase db=this.getWritableDatabase();
	    	 ContentValues ct=new ContentValues();
	         ct.put("Name", tenTatkhoan);
	    	 ct.put("Pass", matKhau);
	    	 
	    	 e=db.update("TAI_KHOAN",ct,"ID=?",new String[]{maTK+""});
	    	 if(e==-1){
	        	 Log.d("---------Sua ko thanh công -------","Xóa ko được");
	        	 
	         } else {
	        	 Log.d("---------Sua thanh cong-------","Xóa Thành công");
	         }
	    	 return e;
	    	 
	     }
	     
	     
	  
}
