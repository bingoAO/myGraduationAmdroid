package com.example.companyuo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheshihao385 on 15/12/29.
 */
public class RefreshAdapter extends BaseAdapter {

    private List<String> data = new ArrayList<String>();
    private LayoutInflater inflater;

    public RefreshAdapter(Context context,List<String> data){
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.healer_item, null);

            viewHolder.name = (TextView) convertView.findViewById(R.id.healer_name);
            viewHolder.workPlace = (TextView) convertView.findViewById(R.id.work_place);
            viewHolder.otherTab = (TextView) convertView.findViewById(R.id.other_tab);
            viewHolder.circleView = (CircleView) convertView.findViewById(R.id.healer_avater);

            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.name.setText("xfermode");
        viewHolder.workPlace.setText("gangliezhongxue");
        viewHolder.otherTab.setText("i'm the best, come and tell me what has troubled you");

        return convertView;
    }

    class ViewHolder{

        TextView name;
        TextView workPlace;
        TextView otherTab;

        CircleView circleView;
    }

}
