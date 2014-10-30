package cn.ibeilin.shetuan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

import cn.ibeilin.ibeilin.R;
import cn.ibeilin.ibeilin.ShetuanListActivity;
import cn.ibeilin.ibeilin.ShetuanPageActivity2;
import cn.ibeilin.ibeilin.R.id;
import cn.ibeilin.ibeilin.util.shetuanListAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ShetuanListGuanzhu extends Fragment {
	ImageView IV1;
	ViewPager mViewPager;
	shetuanListAdapter adapter;
	ArrayList<String> names =null;
   

public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_shetuan_list_all, container,false);
    final GridView gridView = (GridView)view.findViewById(R.id.gridView1);		
    ArrayList <String> names=new ArrayList <String>();
    names.add("53ca2978e4b09c3fc487016d");
    getGuanzhuShetuanList();
	setShetuanList(gridView);
	return view;
}

//获取社团关注社团列表
public void getGuanzhuShetuanList(){
AVUser currentUser=AVUser.getCurrentUser();
AVQuery<AVUser> followeeQuery = AVUser.followeeQuery(currentUser.getObjectId(), AVUser.class);
//AVQuery<AVUser> followeeQuery = userB.followeeQuery(AVUser.class);
followeeQuery.findInBackground(new FindCallback<AVUser>() {
	@Override
	public void done(List<AVUser> parseObjects, AVException parseException){
		for(int i = 0;i<parseObjects.size();i++)
		{         
			names.add(parseObjects.get(i).get("shetuan").toString());
		}
	}
});

}
//获取社团
    public void setShetuanList(final GridView gridView){
         

    	
    	 AVQuery<AVObject> query = new AVQuery<AVObject>("Shetuan");//获取社团列
	 
    	 query.whereContainedIn("objectId", names);
    		  query.findInBackground(new FindCallback<AVObject>() {
    		    public  void done(List<AVObject> ShetuanList, AVException e) {
    	if (e == null) {
    		 //显示图画           
    		        	Log.d("ibeilin", "获取社团对象" + ShetuanList.size() + " 条符合条件的数据");
    		        	adapter=new shetuanListAdapter(getActivity(),ShetuanList);          
    	    	        gridView.setAdapter(adapter);      
    	    	             //添加消息处理
    	    	      //  gridView.setOnItemClickListener(new ItemClickListener());
    		       		}	  
    		         else {
    		            Log.d("bbb", "未获取关注对象: " + e.getMessage());
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






