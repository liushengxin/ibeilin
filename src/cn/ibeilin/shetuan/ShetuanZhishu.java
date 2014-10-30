package cn.ibeilin.shetuan;



import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;

import cn.ibeilin.ibeilin.R;
import cn.ibeilin.ibeilin.util.WordWrapView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ShetuanZhishu extends Fragment {
	private WordWrapView wordWrapView;
	private String[] strs = new String[] { "哲学系", "新疆维吾尔族自治区", "新闻学", "心理学",
	    "犯罪心理学", "明明白白" };
	TextView shetuanName;
	ImageView shetuanLogo;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_shetuan_zhishu, container,false);
		  wordWrapView = (WordWrapView) view.findViewById(R.id.view_wordwrap);
		  
		  for (int i = 0; i <strs.length; i++) {
		    TextView textview = new TextView(getActivity());
		    textview.setText(strs[i]);
		  }   

		return view;
		
	}

}
