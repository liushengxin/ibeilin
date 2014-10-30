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
		//��ȡ��Ļ�߶�
         
		 DisplayMetrics dm = new DisplayMetrics();
		 getWindowManager().getDefaultDisplay().getMetrics(dm);
		 int height = dm.heightPixels;//���height = dm.heightPixels ;//�߶�
		 this.requestWindowFeature(Window.
				 FEATURE_NO_TITLE);//ȥ��������
		 
		setContentView(R.layout.activity_shetuan_page2);
		
	    shetuanCoverIV=(ImageView) findViewById(R.id.shetuan_cover_IV); 
	    shetuanTypeTV=(TextView) findViewById(R.id.shetuan_type);
	    shetuanKeywordTV=(TextView) findViewById(R.id.shetuan_keyword);
	    shetuanLogoIV=(ImageView) findViewById(R.id.shetuan_logo); 
	    shetuanNameTV=(TextView) findViewById(id.shetuan_name);
	    
	    shetuanCoverIV.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int) (height*0.382)));
	//     shetuanCoverIV.setmHeight(height/3);				//���� ������Ӧ�Ը߶�
		

	//����ѡ�
				TabHost.TabSpec tabSpec;
	
					// ��ȡTabHost����
					TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
					// ���û�м̳�TabActivityʱ��ͨ�����ַ�����������tabHost
					tabHost.setup();
					tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("�")
							.setContent(R.id.view1));

					tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("����")
							.setContent(R.id.view2));
					
					tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("����")
							.setContent(R.id.view3));

					//��������������Ϣ		
					
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
		map.put("title", "�12131");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "�2");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "�3");
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
	
   //������Ŵ��룻	
	
	public AVObject getShetuanAVObject1(String _shetuanId)
	{   
		AVQuery<AVObject> query = new AVQuery<AVObject>("Shetuan");
		AVObject ShetuanAVObject = null;
		try {
			 ShetuanAVObject = query.get(_shetuanId);
			  Log.i("iblshetuan", "��ȡ���Ŷ���ɹ�");
			  Log.i("iblshetuan",ShetuanAVObject.getString("st_id"));
			  
		} catch (AVException e) {
		  Log.e("iblshetuan", "��ȡ���Ŷ���ʧ��");
		}
             
	return ShetuanAVObject;
	}
	
	public void getShetuanAVObject2(String _shetuanId)
	{
	AVQuery<AVObject> query = new AVQuery<AVObject>("Shetuan");
	query.getInBackground(_shetuanId, new GetCallback<AVObject>() {
	    public void done(AVObject ShetuanAVObject, AVException e) {
	        if (e == null) {
	        	
	        	  Log.i("iblshetuan", "��ȡ���Ŷ���ɹ�");
	            Log.d("��ȡ����", "�ȷ��ǣ�" + ShetuanAVObject.getString("st_id"));
	            
	          layoutShetuan(ShetuanAVObject);
	        } else {
	            Log.e("iblshetuan", "��ȡ���Ŷ���ʧ��" + e.getMessage());
	        }
	    }
	});
	}

	//����cover ����ͼƬ
	public void layoutShetuan( AVObject _ShetuanAVObject){
		
		shetuanNameTV.setText(_ShetuanAVObject.getString("st_name").toString());
	    shetuanKeywordTV.setText(_ShetuanAVObject.getString("keyword").toString());
		shetuanTypeTV.setText(_ShetuanAVObject.getString("st_type").toString());
		
 //����logo
		_ShetuanAVObject.getAVFile("st_logo").getDataInBackground(new GetDataCallback() {
			
			@Override
			public void done(byte[] data, AVException arg1) {
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //����λͼ  
				shetuanLogoIV.setImageBitmap(bitmap);   //��ʾͼƬ    
			}
		});
        
	//	 Bitmap coverBitmap=getBitmapFromUri(coverUri);
		 
	//	 shetuanCoverIV.setImageBitmap(coverBitmap);
		
		/*Bitmap shetuanCoverBm = null;
		try {
			shetuanCoverBm = getPicFromBytes(	
					_ShetuanAVObject.getAVFile("st_logo").getData(), null);
		} catch (AVException e) {
			// TODO �Զ����ɵ� catch ��
			 Log.e("iblshetuan", "ͼ��ת��ʧ��");
		}

		shetuanCoverIV.setImageBitmap(shetuanCoverBm);
		
		 Log.e("iblshetuan", "ͼ����ʧ��");*/
	}
	 
	 
	//bytes ת����  Bitmap
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
