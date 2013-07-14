package com.isg.entapp.Fragments.Test;

/**
 * Created by yeldar on 13.07.13.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

//import com.isg.entapp.ListAdapters.Test.TestSubjectSelectionListAdapter;
import com.isg.entapp.R;

import java.util.ArrayList;

public class TestSubjectSelection extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ListView listview = (ListView) getActivity().findViewById(R.id.listview);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }


        //final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.test_subject_list_item, list);
       // final TestSubjectSelectionListAdapter adapter = new TestSubjectSelectionListAdapter(getActivity(), values);
        //listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.test_subject_selection_fragment, container, false);
    }
}