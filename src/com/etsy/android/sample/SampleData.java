package com.etsy.android.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;



public class SampleData {

    public static  int item_count =6;
    
        public static ArrayList<String> generateSampleData() {
            final ArrayList<String> data = new ArrayList<String>(item_count);
            for (int i = 0; i < item_count; i++) {
                data.add("活动#");
            }
            return data;
        }


        
 public static  ArrayList<String> getHuodongList() {
    	
      final ArrayList<String> list = null;
      list.add("dsadad");
   	 AVQuery<AVObject> query = new AVQuery<AVObject>("Huodong");
   	 query.findInBackground(new FindCallback<AVObject>() {
   	 public  void done(List<AVObject> HuodongList, AVException e) {
   		  if (e == null) {
   		         //显示图画
   			  Log.d("ccc", "aaa" + HuodongList.size() + " 条符合条件的数据");	  

   			  //生成动态数组，并且转入数据
   			  ArrayList<HashMap<String, Object>> huodonglist = new ArrayList<HashMap<String, Object>>();
   			  for(int i=0;i<HuodongList.size();i++)
   			  {
   				
   				  //map.put("huodong_name", HuodongList.get(i).get("name").toString());// 载入 社团名称	
   				  list.add(HuodongList.get(i).get("name").toString());
   			  }    	         
   		        }        

   		  else {
   		            Log.d("bbb", "bbb: " + e.getMessage());
   		        }
   		    }
   		        
   		  });
   		return list;
     	       	
       } 
    
    public static  ArrayList<String> generateSampleData2() {
   	
         ArrayList<String> list=getHuodongList();
    	 int  item_count=list.size();
   	   final ArrayList<String> data = new ArrayList<String>(item_count);
      
    	for (int i = 0; i < item_count; i++) {
            data.add(list.get(i).toString());
        }
   	
        return data;
    }

}
