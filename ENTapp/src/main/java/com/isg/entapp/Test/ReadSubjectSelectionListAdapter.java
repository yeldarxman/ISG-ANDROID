package com.isg.entapp.Test;

/**
 * Created by yeldar on 13.07.13.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isg.entapp.Models.Subject;
import com.isg.entapp.R;

public class ReadSubjectSelectionListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] subjects;



    //view holder class
    static class ViewHolder {
        public TextView subjectName;

        public ImageView subjectImage;

    }

    public ReadSubjectSelectionListAdapter(Activity context, String[] subjects) {
        super(context, R.layout.test_subject_list_item, subjects);
        this.context = context;
        this.subjects = subjects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.test_subject_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.subjectName = (TextView) rowView.findViewById(R.id.subject_name);

            viewHolder.subjectImage = (ImageView) rowView.findViewById(R.id.image);

            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        String subject = subjects[position];


        holder.subjectImage.setImageResource(0);

        switch (position){
            case 0:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 1:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 2:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 3:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 4:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 5:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 6:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 7:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 8:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 9:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
            case 10:
                holder.subjectImage.setImageResource(R.drawable.navigation_next_item);
                break;
        }

        return rowView;
    }
}
