package cn.ibeilin.shetuan;



import cn.ibeilin.ibeilin.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CommonFragment extends Fragment {
	public static final String TV = "TV";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.common_frag, container,false);
		
		TextView tv = (TextView) view.findViewById(R.id.tv);
		Bundle arg = getArguments();
		String text = arg.getString(TV);
		tv.setText(text);

		return view;
	}
}
