package cn.ibeilin.shetuan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ibeilin.ibeilin.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class shetuanHuodong extends Fragment {
    private ListView lv;
    private List<Map<String, Object>> data;
    @Override
   

public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_shetuan_huodong, container,false);
	 lv = (ListView)view.findViewById(R.id.lv);
     //��ȡ��Ҫ�󶨵��������õ�data��
     data = getData();
     MyAdapter adapter = new MyAdapter(getActivity());
     lv.setAdapter(adapter);

	
	return view;
}

    
	  private List<Map<String, Object>> getData()
	    {
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map;
	        for(int i=0;i<5;i++)
	        {
	            map = new HashMap<String, Object>();
	            map.put("img", R.drawable.pic2);
	            map.put("title", "��ȭ��");
	            map.put("info", "����Դ������...");
	            list.add(map);
	        }
	        return list;
	    }
	    
	    //ViewHolder��̬��
	    static class ViewHolder
	    {
	        public ImageView img;
	        public TextView title;
	        public TextView info;
	    }
	    
	    public class MyAdapter extends BaseAdapter
	    {    
	        private LayoutInflater mInflater = null;
	        private MyAdapter(Context context)
	        {
	            //����context�����ļ��ز��֣��������Demo17Activity������this
	            this.mInflater = LayoutInflater.from(context);
	        }

	        @Override
	        public int getCount() {
	            //How many items are in the data set represented by this Adapter.
	            //�ڴ�������������������ݼ��е���Ŀ��
	            return data.size();
	        }

	        @Override
	        public Object getItem(int position) {
	            // Get the data item associated with the specified position in the data set.
	            //��ȡ���ݼ�����ָ��������Ӧ��������
	            return position;
	        }

	        @Override
	        public long getItemId(int position) {
	            //Get the row id associated with the specified position in the list.
	            //��ȡ���б�����ָ��������Ӧ����id
	            return position;
	        }
	        
	        //Get a View that displays the data at the specified position in the data set.
	        //��ȡһ�������ݼ���ָ����������ͼ����ʾ����
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ViewHolder holder = null;
	            //�������convertViewΪ�գ�����Ҫ����View
	            if(convertView == null)
	            {
	                holder = new ViewHolder();
	                //�����Զ����Item���ּ��ز���
	                convertView = mInflater.inflate(R.layout.list_item, null);
	                holder.img = (ImageView)convertView.findViewById(R.id.huodong_img_shetuanpage);
	                holder.title = (TextView)convertView.findViewById(R.id.tv);
	                holder.info = (TextView)convertView.findViewById(R.id.info);
	                //�����úõĲ��ֱ��浽�����У�������������Tag��Ա���淽��ȡ��Tag
	                convertView.setTag(holder);
	            }else
	            {
	                holder = (ViewHolder)convertView.getTag();
	            }
	            holder.img.setBackgroundResource((Integer)data.get(position).get("img"));
	            holder.title.setText((String)data.get(position).get("title"));
	            holder.info.setText((String)data.get(position).get("info"));
	            
	            return convertView;
	        }
	        
	    }

}
