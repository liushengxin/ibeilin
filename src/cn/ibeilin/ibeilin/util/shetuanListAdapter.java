package cn.ibeilin.ibeilin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.GetDataCallback;

import cn.ibeilin.ibeilin.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class shetuanListAdapter extends BaseAdapter {
	    private List<AVObject> data;
	    private static LayoutInflater inflater=null;
	    
  
       public  shetuanListAdapter(Context context ,List<AVObject> shetuanList) { 
    	   data=shetuanList;
    	   this.inflater = LayoutInflater.from(context);
;
	      //  imageLoader=new ImageLoader(activity.getApplicationContext());
	    }

	    public int getCount() {
	        return data.size();
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }
	    
	    public View getView(int position, View convertView, ViewGroup parent) {
	
	        View vi=convertView;
	    //    if(convertView==null)
	        vi = inflater.inflate(R.layout.iterm_shetuan_list, null);
	     

	        TextView shetuanNameTV = (TextView)vi.findViewById(R.id.shetuan_name_iterm); // 标题

	        final ImageView shetuanLogoIV=(ImageView)vi.findViewById(R.id.shetuan_logo_iterm); // 缩略图
	        
	        shetuanNameTV.setText(data.get(position).getString("st_name"));
	        
	        data.get(position).getAVFile("st_logo").getDataInBackground(new GetDataCallback() {
				
				@Override
				public void done(byte[]data , AVException arg1) {
					// TODO 自动生成的方法存根
				     Bitmap bitmap= BitmapFactory.decodeByteArray(data, 0, data.length);
				     shetuanLogoIV.setImageBitmap(bitmap);
				}
			});
	        
	   /*     
	        // 设置ListView的相关值
	        shetuanNameTV.setText((String)data.get(position).get("shetuan_name"));
	     //   shetuanLogoIV.setImageBitmap((Bitmap)data.get(position).get("shetuan_logo"));
	   
	   */
	        
	        return vi;
	    }
	} 