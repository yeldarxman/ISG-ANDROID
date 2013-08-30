package com.isg.entapp.ListAdapters.More;

/**
 * Created by 1 on 8/25/13.
 */


import android.app.ActionBar;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isg.entapp.R;

import static android.view.ViewGroup.LayoutParams;

public class SettingsListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] settingsItem;


    //view holder class
    static class ViewHolder {
        public TextView settingsItemName;
    }

    public SettingsListAdapter(Activity context, String[] subjects) {
        super(context, R.layout.settings_list_item, subjects);
        this.context = context;
        this.settingsItem = subjects;
    }

    public String[] getSettingsItem() {
        return settingsItem;
    }

    public void setSettingsItem(String[] settingsItem) {
        this.settingsItem = settingsItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.settings_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.settingsItemName = (TextView) rowView.findViewById(R.id.settings_item_name);
            rowView.setTag(viewHolder);

            if(position==settingsItem.length-1){
                rowView.setLayoutParams(new AbsListView.LayoutParams(0,0));
            }
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.settingsItemName.setText(settingsItem[position]);

        return rowView;

    }
}
