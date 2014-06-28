package sdu.edu.entapp.Fragments.More;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.Locale;
import sdu.edu.entapp.Activities.MainTabActivity;
import sdu.edu.entapp.ListAdapters.More.SettingsListAdapter;
import sdu.edu.entapp.Models.Test;
import sdu.edu.entapp.R;
import sdu.edu.entapp.Utilities.Constants;

import static java.security.AccessController.getContext;


/**
 * Created by 1 on 24.08.13.
 */


public class Settings extends android.app.Fragment {

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
                                    clearResults();
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

        ((MainTabActivity.TabInfo) (((MainTabActivity) getActivity()).getMapTabInfo().get("test") )).setTag(getResources().getString(R.string.test));
        ((MainTabActivity.TabInfo) (((MainTabActivity) getActivity()).getMapTabInfo().get("read") )).setTag(getResources().getString(R.string.read));
        ((MainTabActivity.TabInfo) (((MainTabActivity) getActivity()).getMapTabInfo().get("settings") )).setTag(getResources().getString(R.string.settings));

        ((MainTabActivity) getActivity()).getActionBar().setTitle(getResources().getString(R.string.settings));

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

    public void clearResults(){
        String tempSubjects[] = getResources().getStringArray(R.array.test_subjects_array);
        String tempTopics[] = getResources().getStringArray(R.array.test_topics_array);

        String subjects[] = new String[tempSubjects.length];
        String topics[] = new String[tempTopics.length];

        int counter = 0;
        for(String temp: tempSubjects){
            if(temp.isEmpty()){
                break;
            }
            System.out.println(temp);
            String names[] = temp.split(",");
            subjects[counter++] = names[0];
        }

        counter = 0;
        for(String temp: tempTopics){
            if(temp.isEmpty()){
                break;
            }
            System.out.println(temp);
            String names[] = temp.split(",");
            topics[counter++] = names[0];
        }

        //clear shared preferences (only for the selected language)
        //SharedPreferences
        SharedPreferences settings = getActivity().getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        counter = 0;
        for(String subject: subjects){
            editor.remove(subject);

            for(String topic:topics){
                if(topic != null && !topic.isEmpty()) {
                    String variant = subject + topic;
                    editor.remove(variant);
                }
            }
        }

        // Commit the edits!
        editor.commit();
    }
}