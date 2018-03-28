package com.nepaliapps.nepalinews;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.nepaliapps.nepalinews.Adapter.MyFragmentAdapter;
import com.nepaliapps.nepalinews.FragmentMain.NewsPaper;
import com.nepaliapps.nepalinews.FragmentMain.VideoNews;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager vp;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vp = findViewById(R.id.viewpager);
        this.addViewpager(vp);


        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
        tabLayout.addOnTabSelectedListener(listener(vp));

        AppRate.with(this)
                .setInstallDays(0)
                .setLaunchTimes(3)
                .setRemindInterval(0)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);


    }


    public void addViewpager(ViewPager pager) {

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());

        String newsPaper = getString(R.string.newspaper);
        String videoNews = getString(R.string.videoNews);

        adapter.addPage(new NewsPaper(), newsPaper);
        adapter.addPage(new VideoNews(), videoNews);



        pager.setAdapter(adapter);

    }


    private TabLayout.OnTabSelectedListener listener(final ViewPager pager) {

        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };

    }
}
