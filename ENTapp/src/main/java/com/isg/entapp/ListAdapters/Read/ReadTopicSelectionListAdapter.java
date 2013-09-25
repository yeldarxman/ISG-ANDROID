package com.isg.entapp.ListAdapters.Read;

/**
 * Created by yeldar on 13.07.13.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.isg.entapp.R;

public class ReadTopicSelectionListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] topics;

    //view holder class
    static class ViewHolder {
        public TextView topicName;
    }

    public ReadTopicSelectionListAdapter(Activity context, String[] topics) {
        super(context, R.layout.read_topic_list_item, topics);
        this.context = context;
        this.topics = topics;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.read_topic_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.topicName = (TextView) rowView.findViewById(R.id.read_topics_name);
            rowView.setTag(viewHolder);

            if(position==topics.length-1){
                rowView.setLayoutParams(new AbsListView.LayoutParams(0,0));
            }
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.topicName.setText(topics[position]);
        return rowView;
    }
}
