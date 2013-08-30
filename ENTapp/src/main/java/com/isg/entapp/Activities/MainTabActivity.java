package com.isg.entapp.Activities;

/**
 * Created by yeldar on 13.07.13.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;

import com.isg.entapp.Fragments.More.Settings;
import com.isg.entapp.Fragments.Read.ReadSubjectSelection;
import com.isg.entapp.Fragments.Test.TestSubjectSelection;
import com.isg.entapp.R;
import com.isg.entapp.Utilities.Constants;


import java.util.HashMap;
import java.util.Locale;

/**
 * @author yeldar
 *
 */
public class MainTabActivity extends FragmentActivity implements TabHost.OnTabChangeListener {

    private TabHost mTabHost;
    private HashMap mapTabInfo = new HashMap();
    private TabInfo mLastTab = null;

    public class TabInfo {
        private String tag;
        private Class clss;
        private Bundle args;
        private Fragment fragment;
        TabInfo(String tag, Class clazz, Bundle args) {
            this.tag = tag;
            this.clss = clazz;
            this.args = args;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    class TabFactory implements TabContentFactory {

        private final Context mContext;

        /**
         * @param context
         */
        public TabFactory(Context context) {
            mContext = context;
        }

        /** (non-Javadoc)
         * @see android.widget.TabHost.TabContentFactory#createTabContent(String)
         */
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }

    }
    /** (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Step 0: set Language
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
        String language = settings.getString("language", "kk");
        setLanguage(language);
        this.getActionBar().setTitle(getResources().getString(R.string.test));

        // Step 1: Inflate layout
        setContentView(R.layout.main_tab_activity);
        // Step 2: Setup TabHost
        initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
    }

    /** (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
     */
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }

    /**
     * Step 2: Setup TabHost
     */
    public void initialiseTabHost(Bundle args) {
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        TabInfo tabInfo = null;
        String test = getResources().getString(R.string.test);
        String read = getResources().getString(R.string.read);
        String settings = getResources().getString(R.string.settings);
        this.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("test").setIndicator(test), ( tabInfo = new TabInfo(test, TestSubjectSelection.class, args)));
        this.mapTabInfo.put("test", tabInfo);
        this.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("read").setIndicator(read), ( tabInfo = new TabInfo(read, ReadSubjectSelection.class, args)));
        this.mapTabInfo.put("read", tabInfo);
        this.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("settings").setIndicator(settings), ( tabInfo = new TabInfo(settings, Settings.class, args)));
        this.mapTabInfo.put("settings", tabInfo);
        // Default to first tab
        this.onTabChanged("Tab1");
        //
        mTabHost.setOnTabChangedListener(this);
    }

    /**
     * @param activity
     * @param tabHost
     * @param tabSpec
     * @param tabInfo
     */
    private void addTab(MainTabActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
        // Attach a Tab view factory to the spec
        tabSpec.setContent(activity.new TabFactory(activity));
        String tag = tabSpec.getTag();

        // Check to see if we already have a fragment for this tab, probably
        // from a previously saved state.  If so, deactivate it, because our
        // initial state is that a tab isn't shown.
        tabInfo.fragment = activity.getSupportFragmentManager().findFragmentByTag(tag);
        if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()) {
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.detach(tabInfo.fragment);
            fragmentTransaction.commit();
            activity.getSupportFragmentManager().executePendingTransactions();
        }

        tabHost.addTab(tabSpec);
    }

    /** (non-Javadoc)
     * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(String)
     */
    public void onTabChanged(String tag) {
        TabInfo newTab = (TabInfo)this.mapTabInfo.get(tag);
        if (mLastTab != newTab) {
            FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    fragmentTransaction.detach(mLastTab.fragment);
                }
            }
            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(this, newTab.clss.getName(), newTab.args);
                    fragmentTransaction.add(android.R.id.tabcontent, newTab.fragment, newTab.tag);
                } else {
                    fragmentTransaction.attach(newTab.fragment);
                }
            }


            mLastTab = newTab;
            fragmentTransaction.commit();
            this.getActionBar().setTitle(mLastTab.tag);
            System.out.println(mLastTab.tag);
            this.getSupportFragmentManager().executePendingTransactions();
        }
    }

    public void onResume(){
        super.onResume();
    }

    public void setLanguage(String lg){
        Locale locale = new Locale(lg);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;

        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public TabHost getmTabHost() {
        return mTabHost;
    }

    public void setmTabHost(TabHost mTabHost) {
        this.mTabHost = mTabHost;
    }

    public HashMap getMapTabInfo() {
        return mapTabInfo;
    }

    public void setMapTabInfo(HashMap mapTabInfo) {
        this.mapTabInfo = mapTabInfo;
    }
}