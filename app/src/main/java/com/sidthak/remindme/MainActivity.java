package com.sidthak.remindme;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sidthak.remindme.activities.LoginActivity;
import com.sidthak.remindme.adapters.AlarmsAdapter;
import com.sidthak.remindme.fragments.AlarmsFragment;
import com.sidthak.remindme.fragments.Wishlist;
import com.sidthak.remindme.models.AlarmModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        SharedPreferences sharedPref = getSharedPreferences(
                "remindMe", Context.MODE_PRIVATE);

        if(!sharedPref.contains("username")){
            Intent i=new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        TabLayout tablayout = findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(mViewPager);



        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
    }

    // Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            Bundle args = new Bundle();
            switch(i){
                case 0:
                    fragment = new AlarmsFragment();
                    args.putInt(AlarmsFragment.ARG_OBJECT, i + 1);
                    break;
                case 1:
                    fragment = new Wishlist();
                    args.putInt(Wishlist.ARG_OBJECT, i + 1);
                    break;
                default:
                    fragment = new AlarmsFragment();
                    args.putInt(AlarmsFragment.ARG_OBJECT, i + 1);
            }

            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    return "Alarms";
                case 1:
                    return "Wishlist";
                default:
                    return "kya pata";
            }
        }
    }
}


