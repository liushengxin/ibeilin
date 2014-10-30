package cn.ibeilin.shetuan;



import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;

import cn.ibeilin.ibeilin.R;
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

   
public class ShetuanJiaru extends Fragment {
    String shetuanId="53ca2978e4b09c3fc487016d";
	TextView shetuanName;
	ImageView shetuanLogo;
	TextView shetuanJieshao;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	     
		
		View view = inflater.inflate(R.layout.fragment_shetuan_jieshao, container,false);
	    shetuanName=  (TextView) view.findViewById(R.id.shetuan_name_shetuanjieshao);
	     shetuanLogo= (ImageView) view.findViewById(R.id.shetuan_logo_shetuanjieshao);
	 shetuanJieshao=(TextView) view.findViewById(R.id.shetuan_jieshao_tv );
	     getShetuanAVObject(shetuanId);
		return view;
		
	}

private void getShetuanAVObject(String _shetuanId)
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
private void layoutShetuan( AVObject _ShetuanAVObject){
	
	shetuanName.setText(_ShetuanAVObject.getString("st_name").toString());
	shetuanJieshao.setText(_ShetuanAVObject.getString("shetuan_intro").toString());
//载入logo
	_ShetuanAVObject.getAVFile("st_logo").getDataInBackground(new GetDataCallback() {
		
		@Override
		public void done(byte[] data, AVException arg1) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //生成位图  
			shetuanLogo.setImageBitmap(bitmap);   //显示图片    
		}
	});
}  

}
