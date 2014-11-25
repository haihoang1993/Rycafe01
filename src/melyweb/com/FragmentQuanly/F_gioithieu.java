package melyweb.com.FragmentQuanly;

import melyweb.com.database.database2;
import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class F_gioithieu extends Fragment {
	 View rootView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		rootView = inflater
				.inflate(R.layout.gioithieu, container, false);
	
		
	    return rootView;
	}
}
