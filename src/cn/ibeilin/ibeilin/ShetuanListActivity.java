package cn.ibeilin.ibeilin;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.ibeilin.ibeilin.R.id;
import cn.ibeilin.ibeilin.util.shetuanListAdapter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CountCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.SaveCallback;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;



public class ShetuanListActivity extends Activity 
		{
	ImageView IV1;
     GridView  gridView1;
 	ViewPager mViewPager;
 	shetuanListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_shetuan_list_all);
		final GridView gridView = (GridView)findViewById(R.id.gridView1);	 
		IV1=(ImageView) findViewById(id.imageView1);	
	//获取社团
 
	 AVQuery<AVObject> query = new AVQuery<AVObject>("Shetuan");//获取社团列表
	 
	 String[] names = {"1", "3", "7","4"};
	 query.whereContainedIn("st_id", Arrays.asList(names));
		  query.findInBackground(new FindCallback<AVObject>() {
		    public  void done(List<AVObject> ShetuanList, AVException e) {
  if (e == null) {
		 //显示图画           
		        	Log.d("ccc", "aaa" + ShetuanList.size() + " 条符合条件的数据");
		        	adapter=new shetuanListAdapter(ShetuanListActivity.this,ShetuanList);          
	    	        gridView.setAdapter(adapter);      
	    	             //添加消息处理
  	       		}	  
		         else {
		            Log.d("bbb", "bbb: " + e.getMessage());
		        }
		    }
		  });
		   //当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件  
		  gridView.setOnItemClickListener(new ItemClickListener());
	}
  
	  class  ItemClickListener implements OnItemClickListener  
	  {  
	public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened   
	                                  View arg1,//The view within the AdapterView that was clicked  
	                                  int arg2,//The position of the view in the adapter  
	                                  long arg3//The row id of the item that was clicked  
	                                  ) {  
	    //在本例中arg2=arg3  
	  /*  HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
	    //显示所选Item的ItemText  */
		
		//AVObject shetuanAVObject=(AVObject)arg0.getItemAtPosition(arg2);
		
		String a[]={"1","3","7"};
	
	    Bundle buddle = null;
	   //buddle.putString("shetuanId", "0");
	    Intent intent=new Intent();
	 //   intent.putExtras(buddle);
	    intent.setClass(ShetuanListActivity.this,ShetuanPageActivity2.class);
	    ShetuanListActivity.this.startActivity(intent);

	}  

		}
	  
	  
}
