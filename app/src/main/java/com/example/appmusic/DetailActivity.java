package com.example.appmusic;

import android.os.Bundle;

import com.example.appmusic.ui.main.FragmentDetail;
import com.example.appmusic.ui.main.FragmentNote;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmusic.ui.main.SectionsPagerAdapter;

public class DetailActivity extends AppCompatActivity {
FragmentDetail fragmentDetail;
FragmentNote fragmentNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        fragmentDetail =new FragmentDetail();
        fragmentNote =new FragmentNote();
        sectionsPagerAdapter.addFragment(fragmentDetail);
        sectionsPagerAdapter.addFragment(fragmentNote);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

;
//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}