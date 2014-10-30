package cn.ibeilin.shetuan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import cn.ibeilin.ibeilin.R;
import cn.ibeilin.ibeilin.ShetuanListActivity;
import cn.ibeilin.ibeilin.ShetuanPageActivity2;
import cn.ibeilin.ibeilin.R.id;
import cn.ibeilin.ibeilin.util.shetuanListAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class ShetuanListAll extends Fragment {
	ImageView IV1;
	ViewPager mViewPager;
	shetuanListAdapter adapter;
    String names[]={"53ca2978e4b09c3fc487016d"};
    ArrayList <String> name;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shetuan_list_all, container,false);
		final GridView gridView = (GridView)view.findViewById(R.id.gridView1);		
		setShetuanList(gridView);
		return view;
}
//获取社团
    public void setShetuanList(final GridView gridView){
    	 AVQuery<AVObject> query = new AVQuery<AVObject>("Shetuan");//获取社团列表    	 
    	;  
   ArrayList <String> name=new ArrayList <String>();
    name.add(0,"53ca2978e4b09c3fc487016d");
 	 query.whereContainedIn("objectId", name);
    		  query.findInBackground(new FindCallback<AVObject>() {
    		    public  void done(List<AVObject> ShetuanList, AVException e) {
    	if (e == null) {
    		 //显示图画           
    		        	Log.d("ccc", "aaa" + ShetuanList.size() + " 条符合条件的数据");
    		        	adapter=new shetuanListAdapter(getActivity(),ShetuanList);          
    	    	        gridView.setAdapter(adapter);      
    	    	             //添加消息处理
    	    	        gridView.setOnItemClickListener(new ItemClickListener());
    		       		}	  
    		         else {
    		            Log.d("bbb", "bbb: " + e.getMessage());
    		        }
    		    }
    		  }); 	
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
      intent.setClass(getActivity(),ShetuanPageActivity2.class);
      getActivity().startActivity(intent);
  }
  }  
}






