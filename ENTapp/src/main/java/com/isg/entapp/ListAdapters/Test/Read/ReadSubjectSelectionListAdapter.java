package com.isg.entapp.ListAdapters.Test.Read;

/**
 * Created by yeldar on 13.07.13.
 */

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.isg.entapp.Models.Subject;
import com.isg.entapp.R;

public class ReadSubjectSelectionListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] subjects;



    //view holder class
    static class ViewHolder {
        public TextView subjectName;
    }

    public ReadSubjectSelectionListAdapter(Activity context, String[] subjects) {
        super(context, R.layout.read_subject_list_item, subjects);
        this.context = context;
        this.subjects = subjects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.read_subject_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.subjectName = (TextView) rowView.findViewById(R.id.subject_name);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.subjectName.setText(subjects[position]);
        return rowView;
    }
}
