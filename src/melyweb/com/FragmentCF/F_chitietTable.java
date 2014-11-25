package melyweb.com.FragmentCF;

import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi") 
public class F_chitietTable extends Fragment{
	public F_chitietTable(){}
     
	private View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater
				.inflate(R.layout.f_chitiet_table, container, false);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	
}
