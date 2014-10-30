package cn.ibeilin.ibeilin;

import android.R.color;
import android.R.drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import java.util.List;
import java.util.Map;

import cn.ibeilin.ibeilin.R.id;
import cn.ibeilin.ibeilin.util.TabHorizontalScrollView;
import cn.ibeilin.shetuan.CommonFragment;
import cn.ibeilin.shetuan.ShetuanJieshao;
import cn.ibeilin.shetuan.ShetuanZhishu;
import cn.ibeilin.shetuan.shetuanHuodong;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FollowCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


public class ShetuanPageActivity2 extends FragmentActivity {
      ImageView shetuanZhezhaoIV;
      ImageView shetuanCoverIV;
      ImageView shetuanLogoIV;
      TextView shetuanNameTV;
      TextView shetuanTypeTV;
	    TextView shetuanKeywordTV;	 
	    ImageButton guanzhuShetuanBt;
	    RelativeLayout coverLayout;
	    protected static final String TAG = "MainActivity";
		private ViewPager vp;
		private ImageView iv_indicator,iv_nav_right,iv_nav_left;
		private RadioGroup rg;
		private static String[] tabTitle = { "活动", "介绍", "关于", "加入"};	//标题
		private int currentNavItemWidth,currentIndicatorLeft = 0;
		private TabHorizontalScrollView tsv;
		private int cardinality; //将屏幕按宽分成的份数
		private RelativeLayout rl;
 
  	private List<Map<String, Object>> mData;
	
      String shetuanId="53ca2978e4b09c3fc487016d";
	String shetuanAdminId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		// shetuanId=bundle.getString(shetuanId);
		// 获取屏幕高度
		AVUser currentUser = AVUser.getCurrentUser();
		Toast.makeText(ShetuanPageActivity2.this,
				currentUser.getUsername() + "已登陆", Toast.LENGTH_LONG).show();

		// 下载载入社团信息
		initView();
		getShetuanAVObject(shetuanId);
		findView();
		initTabView();
		setListener();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
   //获得社团代码；	
	
	private void initView(){
		

		 DisplayMetrics dm = new DisplayMetrics();
		 getWindowManager().getDefaultDisplay().getMetrics(dm);
		 int height = dm.heightPixels;//宽度height = dm.heightPixels ;//高度
		 this.requestWindowFeature(Window.
				 FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_shetuan_page2);
		shetuanZhezhaoIV=(ImageView)findViewById(R.id.zhezhao);
	    shetuanCoverIV=(ImageView) findViewById(R.id.shetuan_cover_IV); 
	    shetuanTypeTV=(TextView) findViewById(R.id.shetuan_type);
	    shetuanKeywordTV=(TextView) findViewById(R.id.shetuan_keyword);
	    shetuanLogoIV=(ImageView) findViewById(R.id.shetuan_logo); 
	    shetuanNameTV=(TextView) findViewById(id.shetuan_name);
	    guanzhuShetuanBt=(ImageButton)findViewById(id.guanzhu_shetuan_button);
	    coverLayout=(RelativeLayout) findViewById(R.id.cover_layout);
	// shetuanCoverIV.setLayoutParams(new android.widget.FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,(int) (height*0.382)));
	// shetuanZhezhaoIV.setLayoutParams(new android.widget.FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,(int) (height*0.382)));
	// coverLayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,(int) (height*0.382-60)));
		
	   guanzhuShetuanBt.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {  guanzhuShetuan(shetuanId);}		
		});
	   
		
	}
	

	
	public void getShetuanAVObject(String _shetuanId)
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
		shetuanAdminId=_ShetuanAVObject.getAVObject("st_admin").getObjectId().toString();
		shetuanNameTV.setText(_ShetuanAVObject.getString("signature").toString());
		//shetuanTypeTV.setText(_ShetuanAVObject.getString("st_type").toString());
		
 //载入logo
		_ShetuanAVObject.getAVFile("st_logo").getDataInBackground(new GetDataCallback() {
			
			@Override
			public void done(byte[] data, AVException arg1) {
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //生成位图  
				shetuanLogoIV.setImageBitmap(bitmap);   //显示图片    
			}
		});
	}   
	
	//加入社团函数
	private void guanzhuShetuan(String shetuanId) {
		// TODO 自动生成的方法存根
		AVUser currentUser = AVUser.getCurrentUser();
		if (currentUser != null) {		
			AVUser.getCurrentUser().followInBackground(shetuanAdminId, new FollowCallback() {
		        @Override
		        public void done(AVObject object, AVException e) {
		            if (e == null) {
		                Log.i(TAG, "follow succeed.");
		                guanzhuShetuanBt.setImageResource(R.drawable.heart_green);;
		                guanzhuShetuanBt.setOnClickListener(null);
		            } else if (e.getCode() == AVException.DUPLICATE_VALUE) {
		                Log.w(TAG, "Already followed.");
		            }
		        }
		    });
		} else {
			Toast.makeText(ShetuanPageActivity2.this, "未登录", Toast.LENGTH_LONG).show();
		    
		}
	
		
		
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

	private void setListener() {
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				Log.i(TAG, "checkedId:"+checkedId);
				
				//RadioGroup
				rg.check(checkedId);
				
				//indicator
				TranslateAnimation animation = new TranslateAnimation(
						currentIndicatorLeft ,
						((RadioButton) rg.getChildAt(checkedId)).getLeft(), 0f, 0f);
				animation.setInterpolator(new LinearInterpolator());
				animation.setDuration(300);
				animation.setFillAfter(true);
				
				iv_indicator.startAnimation(animation);//执行位移动画
				
				currentIndicatorLeft = rg.getChildAt(checkedId).getLeft();//记录当前 下标的距最左侧的 距离
				
				//ViewPager
				vp.setCurrentItem(checkedId);//ViewPager 跟随一起 切换
				
				//ScrollView的滑动
				int x = (checkedId > 0 ? ((RadioButton) rg.getChildAt(checkedId)).getLeft() : 0) - ((RadioButton) rg.getChildAt(1)).getLeft();
				tsv.smoothScrollTo(x, 0);
			}
		});
		
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				Log.i(TAG, "position: " + position);
				
				if(rg != null){
					rg.getChildAt(position).performClick();
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void initTabView() {

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		cardinality = 4;
		if(tabTitle.length < 4){
			cardinality = tabTitle.length;
		}
		
		currentNavItemWidth = dm.widthPixels/cardinality;
		
		//indicator
		LayoutParams param = (LayoutParams) iv_indicator.getLayoutParams();
		param.width = currentNavItemWidth;// 初始化滑动下标的宽
		iv_indicator.setLayoutParams(param);
		

	
		//RadioGroup
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		rg.removeAllViews();
	     for(int i = 0; i < tabTitle.length; i++){
			
			RadioButton rb = (RadioButton) inflater.inflate(R.layout.nav_rg_item, null);
			rb.setBackgroundColor(color.transparent);
			rb.setLayoutParams(new LinearLayout.LayoutParams(currentNavItemWidth, LayoutParams.MATCH_PARENT));
			rb.setId(i);
			rb.setText(tabTitle[i]);
			rg.addView(rb); 
		}
	
		//ViewPaper
		TabFragmentPagerAdapter pagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
		vp.setAdapter(pagerAdapter);
		
		//TabHorizontalScrollView
		tsv.setParams(rl, iv_nav_left, iv_nav_right, this);
		iv_nav_left.setVisibility(View.GONE); //初始化时默认选中第一个，所以将向左的箭头隐藏
		if(tabTitle.length <= 4){
			iv_nav_right.setVisibility(View.GONE); //向右的箭头隐藏
		}  
	}
	
	class TabFragmentPagerAdapter extends FragmentPagerAdapter {

		public TabFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			switch ( arg0)
			
			{case 0:{
				Fragment fg=new shetuanHuodong();
				return fg;}
			
			case 1:{
				Fragment fg=new ShetuanJieshao();
				return fg;}
			
			case 2:{
				Fragment fg=new ShetuanZhishu();
				return fg;}
			  default:{
				Fragment fg = new CommonFragment();
				Bundle args = new Bundle();
				args.putString(CommonFragment.TV, tabTitle[arg0]);
				fg.setArguments(args);
				
				return fg;}
			}


		}

		@Override
		public int getCount() {

			if(tabTitle != null){
				return tabTitle.length;
			}
			
			return 0;
		}
		
	}

	private void findView() {
		
		rl = (RelativeLayout) findViewById(R.id.rl_nav);
		tsv = (TabHorizontalScrollView) findViewById(R.id.sv_nav);
		rg = (RadioGroup) findViewById(R.id.rg_nav);
		
		iv_indicator = (ImageView) findViewById(R.id.iv_nav_indicator);
		iv_nav_left = (ImageView) findViewById(R.id.iv_nav_left);
		iv_nav_right = (ImageView) findViewById(R.id.iv_nav_right);
		
		
		vp = (ViewPager) findViewById(R.id.vp);
		
	}


    
	}

