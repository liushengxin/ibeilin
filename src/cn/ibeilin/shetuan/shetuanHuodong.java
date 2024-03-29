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
     //获取将要绑定的数据设置到data中
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
	            map.put("title", "跆拳道");
	            map.put("info", "快乐源于生活...");
	            list.add(map);
	        }
	        return list;
	    }
	    
	    //ViewHolder静态类
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
	            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
	            this.mInflater = LayoutInflater.from(context);
	        }

	        @Override
	        public int getCount() {
	            //How many items are in the data set represented by this Adapter.
	            //在此适配器中所代表的数据集中的条目数
	            return data.size();
	        }

	        @Override
	        public Object getItem(int position) {
	            // Get the data item associated with the specified position in the data set.
	            //获取数据集中与指定索引对应的数据项
	            return position;
	        }

	        @Override
	        public long getItemId(int position) {
	            //Get the row id associated with the specified position in the list.
	            //获取在列表中与指定索引对应的行id
	            return position;
	        }
	        
	        //Get a View that displays the data at the specified position in the data set.
	        //获取一个在数据集中指定索引的视图来显示数据
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ViewHolder holder = null;
	            //如果缓存convertView为空，则需要创建View
	            if(convertView == null)
	            {
	                holder = new ViewHolder();
	                //根据自定义的Item布局加载布局
	                convertView = mInflater.inflate(R.layout.list_item, null);
	                holder.img = (ImageView)convertView.findViewById(R.id.huodong_img_shetuanpage);
	                holder.title = (TextView)convertView.findViewById(R.id.tv);
	                holder.info = (TextView)convertView.findViewById(R.id.info);
	                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
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
