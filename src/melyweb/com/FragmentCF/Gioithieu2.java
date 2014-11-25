package melyweb.com.FragmentCF;

import melyweb.com.rycafe.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi") 
public class Gioithieu2 extends Fragment{
	 View rootView;
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			
			rootView = inflater
					.inflate(R.layout.gioithieu, container, false);
		
			
		    return rootView;
		}
}
