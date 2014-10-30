package cn.ibeilin.ibeilin;



import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class ControlPanelActivity extends Activity /*implements OnClickListener*/ {
    
	private Button bt1; 
	private  Button bt2; 
	private Button bt3; 
	private  Button bt4=null; 
	private  Button bt5=null;
	
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control_panel);
        
		bt1.findViewById(R.id.button1);
		bt2.findViewById(R.id.button2);
		bt3.findViewById(R.id.button3);
       
		

	}

/*	  
public void onClick(View aView){
		 
		 switch (aView.getId()) {  
               case R.id.button1:  
                       intent.setClass(this, ShetuanPageActivity.class);  break;  

               case R.id.button2:  
            	       intent.setClass(this, TestActivity.class);  break;  
                                        
               case R.id.button3:  
        	         intent.setClass(this, ShetuanListActivity.class);  break;  
                    }  
 
         this.startActivity (intent) ;//打开相对应的测试程序

	 }


@Override
public void onClick(DialogInterface arg0, int arg1) {
	// TODO 自动生成的方法存根
	
}  
     

*/
	
	
	

}



