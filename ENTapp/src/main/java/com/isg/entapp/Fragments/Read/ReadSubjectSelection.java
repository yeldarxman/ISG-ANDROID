package com.isg.entapp.Fragments.Read;

/**
 * Created by yeldar on 13.07.13.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.isg.entapp.Activities.MainTabActivity;

import com.isg.entapp.Activities.ReadTopicSelectionActivity;
import com.isg.entapp.ListAdapters.Read.ReadSubjectSelectionListAdapter;
import com.isg.entapp.R;

public class ReadSubjectSelection extends Fragment {

    private String[] subject;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ListView listview = (ListView) getActivity().findViewById(R.id.listview2);

        //load the subject objects
        this.loadSubjects();

        //final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.test_subject_list_item, list);
        final ReadSubjectSelectionListAdapter adapter = new ReadSubjectSelectionListAdapter(getActivity(), subject);
        listview.setAdapter(adapter);

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
        return inflater.inflate(R.layout.read_subject_selection_fragment, container, false);
    }

    public void loadSubjects(){
        subject = getResources().getStringArray((R.array.read_subjects_array));
    }
}
