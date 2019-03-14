package com.example.application_jxf.view;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.application_jxf.R;
import com.example.application_jxf.adapters.ViewPagerAdapter;
import com.example.application_jxf.dao.ResturantDAO;
import com.example.application_jxf.pojo.BookingItem;
import com.example.application_jxf.pojo.RestourantItem;
import com.example.application_jxf.view.fragments.MapFragment;
import com.example.application_jxf.view.fragments.UserFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TabActivty extends AppCompatActivity {

    public static final String GOOGLE_ACCOUNT = "google_account";

    public static final String PHOTO_URL = "https://www.worlds50bestbars.com/files/Listing/Images/1//American_Bar-Museum_Bar_2.jpg";

    BottomNavigationView navigation;
    MenuItem prevMenuItem;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_home:
                    viewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };
    private ViewPager viewPager;
    private MapFragment mapFragment;
    private UserFragment userFragment;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activty);


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        setupViewPager(viewPager);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mapFragment = MapFragment.newInstance(ResturantDAO.getInstance().getRestourantItems());
        userFragment = UserFragment.newInstance(ResturantDAO.getInstance().getBookingItems());
        viewPagerAdapter.addFragment(mapFragment);
        viewPagerAdapter.addFragment(userFragment);
        viewPager.setAdapter(viewPagerAdapter);
    }

}
