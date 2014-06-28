package sdu.edu.entapp.ListAdapters.Test;

/**
 * Created by yerbolat on 13.07.13.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.security.auth.Subject;

import sdu.edu.entapp.R;

public class TestSubjectSelectionListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] subjects;
    public double[] subjectProgresses;

    //view holder class
    static class ViewHolder {
        public TextView subjectName;
        public TextView percentage;
        public ProgressBar progressBar;
        public ImageView subjectImage;
    }

    public TestSubjectSelectionListAdapter(Activity context, String[] subjects) {
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
            viewHolder.percentage = (TextView) rowView.findViewById(R.id.percentage);
            viewHolder.subjectImage = (ImageView) rowView.findViewById(R.id.image);
            viewHolder.progressBar = (ProgressBar) rowView.findViewById(R.id.progress_bar);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.subjectName.setText(subjects[position]);
        holder.percentage.setText(subjectProgresses[position] + "");
        holder.progressBar.setProgress((int)subjectProgresses[position]);

        switch (position){
            case 0:
                holder.subjectImage.setImageResource(R.drawable.kaztil_icon);
                break;
            case 1:
                holder.subjectImage.setImageResource(R.drawable.kaz_history_icon);
                break;
            case 2:
                holder.subjectImage.setImageResource(R.drawable.russian_icon);
                break;
            case 3:
                holder.subjectImage.setImageResource(R.drawable.english_icon);
                break;
            case 4:
                holder.subjectImage.setImageResource(R.drawable.kaz_history_icon);
                break;

        }

        return rowView;
    }
}