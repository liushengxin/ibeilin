package cn.ibeilin.ibeilin;

import android.R.color;
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
import cn.ibeilin.shetuan.ShetuanListAll;
import cn.ibeilin.shetuan.ShetuanListGuanzhu;
import cn.ibeilin.shetuan.ShetuanZhishu;
import cn.ibeilin.shetuan.shetuanHuodong;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
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
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;


public class ShetuanListActivity2 extends FragmentActivity {
  
	 
		protected static final String TAG = null;
		private ViewPager vp;
		private ImageView iv_indicator,iv_nav_right,iv_nav_left;
		private RadioGroup rg;
		private static String[] tabTitle = { "ȫ��", "�ѹ�ע","�Ƽ�", "�Ѽ���"};	//����
		private int currentNavItemWidth,currentIndicatorLeft = 0;
		private TabHorizontalScrollView tsv;
		private int cardinality; //����Ļ����ֳɵķ���
		private RelativeLayout rl;
 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras(); 

        

	 //��������������Ϣ		
		initView();
		  
			findView();
			initTabView();
			setListener();
	}      


	/**
	 * A placeholder fragment containing a simple view.
	 */
	
   //������Ŵ��룻	
	
	private void initView(){
		
		setContentView(R.layout.fragment_shetuan_list2);
	     
		
	}



	
	private void setListener() {
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				String TAG = null;
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
				
				iv_indicator.startAnimation(animation);//ִ��λ�ƶ���
				
				currentIndicatorLeft = rg.getChildAt(checkedId).getLeft();//��¼��ǰ �±�ľ������� ����
				
				//ViewPager
				vp.setCurrentItem(checkedId);//ViewPager ����һ�� �л�
				
				//ScrollView�Ļ���
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
		param.width = currentNavItemWidth;// ��ʼ�������±�Ŀ�
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
		iv_nav_left.setVisibility(View.GONE); //��ʼ��ʱĬ��ѡ�е�һ�������Խ�����ļ�ͷ����
		if(tabTitle.length <= 4){
			iv_nav_right.setVisibility(View.GONE); //���ҵļ�ͷ����
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
				Fragment fg=new ShetuanListAll();
				return fg;}
			case 1:{
				Fragment fg=new ShetuanListGuanzhu();
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
		
		rl = (RelativeLayout) findViewById(R.id.rl_nav1);
		tsv = (TabHorizontalScrollView) findViewById(R.id.sv_nav1);
		rg = (RadioGroup) findViewById(R.id.rg_nav1);
		
		iv_indicator = (ImageView) findViewById(R.id.iv_nav_indicator1);
		iv_nav_left = (ImageView) findViewById(R.id.iv_nav_left1);
		iv_nav_right = (ImageView) findViewById(R.id.iv_nav_right1);
		
		vp = (ViewPager) findViewById(R.id.vp1);
		
	}


    
	}

