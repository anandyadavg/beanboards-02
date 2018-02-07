package com.example.edexworldpc.beanboards;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class LandingPage2 extends AppCompatActivity{

    private Toolbar mtoolBar;
    private TabLayout tabLayout;
    private ViewPager mviewPager;
    private CustomPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page2);
        mAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        mtoolBar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(mtoolBar);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        mviewPager = (ViewPager)findViewById(R.id.pager);
        mviewPager.setAdapter(mAdapter);
        tabLayout.setTabsFromPagerAdapter(mAdapter);
        tabLayout.setupWithViewPager(mviewPager);
    }

}

class CustomPagerAdapter extends FragmentStatePagerAdapter{

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new MyBoardsFragment();
            case 1:
                return new BoardsView();
            case 2:
                return new WorkFlowFragments();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "My Boards";
            case 1:
                return "Board's View";
            case 2:
                return "Work Flow";
        }
        return "";
    }
}
