package cn.ibeilin.ibeilin;

import cn.ibeilin.ibeilin.R.id;

import com.etsy.android.grid.StaggeredGridView;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;

public class HuodongListActivity extends Activity {
	private StaggeredGridView mGridView; 
	private static final int[] COLOR = new int[] {
		0xff33b5e5, 0xffaa66cc, 0xff99cc00, 0xffffbb33, 0xffff4444
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_huodong_list);  
          
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);  
        mGridView.setAdapter(new MyAdapter());  
	}
	
	
	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return DATA.length;
		}

		@Override
		public Object getItem(int position) {
			return DATA[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new TextView(HuodongListActivity.this);}
			
		 
		View view=findViewById(R.id.view_iterm_huodong_list);
	    	 
	    	 TextView huodongNameTV= (TextView) findViewById(id.huodong_name_iterm);
	    	 huodongNameTV.setText(DATA[position]);
			
		
		/*	TextView view = (TextView) convertView;
			view.setText(DATA[position]);
			view.setBackgroundColor(COLOR[position % 5]);
			view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
			view.setGravity(Gravity.BOTTOM);
			view.setTextColor(Color.WHITE);
			
			
		*/
			return   view;
		
	}

	private final String[] DATA = new String[] {
		"Abbyyyyaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
	    "Acorn", "Adelost", "Affidelice au Chablis"};


	}

}

