package com.isg.entapp.Fragments.More;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.isg.entapp.Activities.MainTabActivity;
import com.isg.entapp.Activities.MainTabActivity.*;
import com.isg.entapp.ListAdapters.More.SettingsListAdapter;
import com.isg.entapp.R;
import com.isg.entapp.Utilities.Constants;

import java.util.Locale;

import static java.security.AccessController.getContext;


/**
 * Created by 1 on 24.08.13.
 */


public class Settings extends SherlockFragment {

    private String[] settingsListItem;
    private ListView listview;
    private SettingsListAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listview = (ListView) getActivity().findViewById(R.id.listview4);

        //load the settings List items
        this.loadListItem();

        //final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.test_subject_list_item, list);
        adapter = new SettingsListAdapter(getActivity(), settingsListItem);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                if(position==1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(R.string.til);
                        builder.setItems(R.array.language, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==1){
                                    setLanguage("ru");
                                }else
                                    setLanguage("kk");

                                // The 'which' argument contains the index position
                                // of the selected item
                            }
                        });
                    AlertDialog alertDialog1 = builder.create();

                    // show it
                    alertDialog1.show();

                }
                if(position==settingsListItem.length-4){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.dialog_erase_results)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    // Create the AlertDialog object and return it
                    AlertDialog alertDialog2 = builder.create();

                    // show it
                    alertDialog2.show();

                }
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.settings_list_fragment, container, false);
    }

    public void loadListItem(){
        settingsListItem = getResources().getStringArray(R.array.settings_array);
    }

    public void setLanguage(String lg){
        Locale locale = new Locale(lg);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;

        getActivity().getResources().updateConfiguration(config,
                getActivity().getResources().getDisplayMetrics());

        loadListItem();

        TabHost tabHost = ((MainTabActivity) getActivity()).getmTabHost();
        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv1.setText(getResources().getString(R.string.test));

        TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv2.setText(getResources().getString(R.string.read));

        TextView tv3 = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tv3.setText(getResources().getString(R.string.settings));

        ((TabInfo) (((MainTabActivity) getActivity()).getMapTabInfo().get("test") )).setTag(getResources().getString(R.string.test));
        ((TabInfo) (((MainTabActivity) getActivity()).getMapTabInfo().get("read") )).setTag(getResources().getString(R.string.read));
        ((TabInfo) (((MainTabActivity) getActivity()).getMapTabInfo().get("settings") )).setTag(getResources().getString(R.string.settings));

        ((MainTabActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.settings));

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setSettingsItem(settingsListItem);
                adapter.notifyDataSetChanged();
            }
        });


        //SharedPreferences
        SharedPreferences settings = getActivity().getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language", lg);
        // Commit the edits!
        editor.commit();
    }
}