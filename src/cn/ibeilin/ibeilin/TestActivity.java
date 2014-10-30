package cn.ibeilin.ibeilin;

import cn.ibeilin.ibeilin.R.id;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;










import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class TestActivity extends ActionBarActivity implements OnClickListener{
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control_panel);

		AVUser.logInInBackground("user1", "1111", new LogInCallback() {
		    public void done(AVUser user, AVException e) {
		        if (user != null) {
		        	  Toast.makeText(TestActivity.this, user.getUsername()+"已登陆",Toast.LENGTH_LONG ).show();
		        } else {
		      	  Toast.makeText(TestActivity.this, "未登录",Toast.LENGTH_LONG ).show();
		        }
		    }
		});	
		
		
	Button bt1= (Button) findViewById(id.button1);	
	Button bt2= (Button) findViewById(id.button2);	
	Button bt3= (Button) findViewById(id.button3);
	Button bt4= (Button) findViewById(id.button4);
	Button bt5= (Button) findViewById(id.button5);

	
	bt1.setText("社团主页");
	bt2.setText("列表2");
	bt3.setText("社团列表");
	bt4.setText("活动列表");
	bt5.setText("启动页面");

	bt1.setOnClickListener(this);
	bt2.setOnClickListener(this);
	bt3.setOnClickListener(this);
	bt4.setOnClickListener(this);
	bt5.setOnClickListener(this);

	
	}
	
	
	
	public void onClick(View aView){
	   Intent intent=new Intent();   
	   
		 switch (aView.getId()) {  
              case R.id.button1:  
                   intent.setClass(TestActivity.this, ShetuanPageActivity2.class);  break;  

              case R.id.button2:  
           	       intent.setClass(TestActivity.this,ShetuanListActivity2.class);        break;  
                                       
              case R.id.button3:  
       	         intent.setClass(TestActivity.this, ShetuanListActivity.class);  break;  
              case R.id.button4:  
        	         intent.setClass(TestActivity.this, HuodongListActivity.class);  break;  
              case R.id.button5:  
     	         intent.setClass(TestActivity.this,SplashActivity.class);  break;  
                   }   

              startActivity (intent) ;//打开相对应的测试程序

	 }





	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
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


//	 A placeholder fragment containing a simple view.
	 
	
	

}

	
