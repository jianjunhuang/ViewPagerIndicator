package com.jianjunhuang.demo.viewpagerindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ViewPager viewPager = findViewById(R.id.view_pager);
    final StripIndicatorView indicatorView = findViewById(R.id.indicator_view);
    final Fragment[] fragments = {MainFragment.newInstance(0), MainFragment.newInstance(1),
        MainFragment.newInstance(2), MainFragment.newInstance(3), MainFragment.newInstance(4)};
    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

      @Override
      public Fragment getItem(int i) {
        return fragments[i];
      }

      @Override
      public int getCount() {
        return fragments.length;
      }
    });
    indicatorView.bindViewPager(viewPager);
  }
}
