package cn.ibeilin.ibeilin;



import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ibeilin.ibeilin.R.id;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;

import android.R.integer;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiEnterpriseConfig.Phase2;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;

public class ShetuanPageActivity extends ActionBarActivity {
      protected static final String TAG = null;
	  ImageView shetuanCoverIV;
      ImageView shetuanLogoIV;
      TextView shetuanNameTV;
      TextView shetuanTypeTV;
	    TextView shetuanKeywordTV;

  	private List<Map<String, Object>> mData;
	
      String shetuanId="53ca2978e4b09c3fc487016d";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras(); 
	//	shetuanId=bundle.getString(shetuanId);
		//获取屏幕高度
         
		 DisplayMetrics dm = new DisplayMetrics();
		 getWindowManager().getDefaultDisplay().getMetrics(dm);
		 int height = dm.heightPixels;//宽度height = dm.heightPixels ;//高度
		 this.requestWindowFeature(Window.
				 FEATURE_NO_TITLE);//去掉标题栏
		 
		setContentView(R.layout.activity_shetuan_page2);
		
	    shetuanCoverIV=(ImageView) findViewById(R.id.shetuan_cover_IV); 
	    shetuanTypeTV=(TextView) findViewById(R.id.shetuan_type);
	    shetuanKeywordTV=(TextView) findViewById(R.id.shetuan_keyword);
	    shetuanLogoIV=(ImageView) findViewById(R.id.shetuan_logo); 
	    shetuanNameTV=(TextView) findViewById(id.shetuan_name);
	    
	    shetuanCoverIV.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int) (height*0.382)));
	//     shetuanCoverIV.setmHeight(height/3);				//封面 设置适应性高度
		

	//加载选项卡
				TabHost.TabSpec tabSpec;
	
					// 获取TabHost对象
					TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
					// 如果没有继承TabActivity时，通过该种方法加载启动tabHost
					tabHost.setup();
					tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("活动")
							.setContent(R.id.view1));

					tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("我们")
							.setContent(R.id.view2));
					
					tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("加入")
							.setContent(R.id.view3));

					//下载载入社团信息		
					
					getShetuanAVObject2(shetuanId);
					
					ListView huodongListView=(ListView) findViewById(id.huodong_list_shetuanpage);
					SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.iterm_sheuan_huodong_shetuanpage,
							new String[]{"title"},
							new int[]{R.id.huodong_name_shetuanpage});
					huodongListView.setAdapter(adapter);

				}
				
		
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "活动12131");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "活动2");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "活动3");
		list.add(map);
		
		return list;
	}
					
			


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shetuan_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
   //获得社团代码；	
	
	public AVObject getShetuanAVObject1(String _shetuanId)
	{   
		AVQuery<AVObject> query = new AVQuery<AVObject>("Shetuan");
		AVObject ShetuanAVObject = null;
		try {
			 ShetuanAVObject = query.get(_shetuanId);
			  Log.i("iblshetuan", "获取社团对象成功");
			  Log.i("iblshetuan",ShetuanAVObject.getString("st_id"));
			  
		} catch (AVException e) {
		  Log.e("iblshetuan", "获取社团对象失败");
		}
             
	return ShetuanAVObject;
	}
	
	public void getShetuanAVObject2(String _shetuanId)
	{
	AVQuery<AVObject> query = new AVQuery<AVObject>("Shetuan");
	query.getInBackground(_shetuanId, new GetCallback<AVObject>() {
	    public void done(AVObject ShetuanAVObject, AVException e) {
	        if (e == null) {
	        	
	        	  Log.i("iblshetuan", "获取社团对象成功");
	            Log.d("获取分数", "比分是：" + ShetuanAVObject.getString("st_id"));
	            
	          layoutShetuan(ShetuanAVObject);
	        } else {
	            Log.e("iblshetuan", "获取社团对象失败" + e.getMessage());
	        }
	    }
	});
	}

	//下载cover 布局图片
	public void layoutShetuan( AVObject _ShetuanAVObject){
		
		shetuanNameTV.setText(_ShetuanAVObject.getString("st_name").toString());
	    shetuanKeywordTV.setText(_ShetuanAVObject.getString("keyword").toString());
		shetuanTypeTV.setText(_ShetuanAVObject.getString("st_type").toString());
		
 //载入logo
		_ShetuanAVObject.getAVFile("st_logo").getDataInBackground(new GetDataCallback() {
			
			@Override
			public void done(byte[] data, AVException arg1) {
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //生成位图  
				shetuanLogoIV.setImageBitmap(bitmap);   //显示图片    
			}
		});
        
	//	 Bitmap coverBitmap=getBitmapFromUri(coverUri);
		 
	//	 shetuanCoverIV.setImageBitmap(coverBitmap);
		
		/*Bitmap shetuanCoverBm = null;
		try {
			shetuanCoverBm = getPicFromBytes(	
					_ShetuanAVObject.getAVFile("st_logo").getData(), null);
		} catch (AVException e) {
			// TODO 自动生成的 catch 块
			 Log.e("iblshetuan", "图像转换失败");
		}

		shetuanCoverIV.setImageBitmap(shetuanCoverBm);
		
		 Log.e("iblshetuan", "图像部署失败");*/
	}
	 
	 
	//bytes 转换成  Bitmap
	public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {

	     if (bytes != null)
	     {
	        if (opts != null) 
	             return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,  opts); 
	        else
	            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length); 
	   
	     }
	     return null;
	}
	}
