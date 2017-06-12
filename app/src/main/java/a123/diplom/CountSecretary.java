package a123.diplom;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import a123.diplom.Fragments.FragmentCommission;
import a123.diplom.Fragments.FragmentCount;


public class CountSecretary extends BaseOther {
    public static String id_std,id;
    public static int coins;
    public static FragmentCount f1;
    public static FragmentCommission f2;
    ViewPager mViewPager;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_secr);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        f1 = new FragmentCount();
        f2 = new FragmentCommission();
        coins = getIntent().getExtras().getInt("std_coins");
        setupViewPager(mViewPager);
        String theme = getIntent().getExtras().getString("std_theme");
        getSupportActionBar().setTitle(theme);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);
        id_std = getIntent().getExtras().getString("std_id");
        id = getIntent().getExtras().getString("std_id");



        Log.d("LoginActivity", "coins:" + coins);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int pos) {
                if (pos == 1 && f2!=null){
                    f2.loadData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
    private void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if(coins!=0){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(f1);
            ft.commit();
            adapter.addFragment(f2, "Баллы членов комиссии");
            adapter.addFragment(f1, "Выставление баллов");
            mViewPager.setAdapter(adapter);
        }else {
            adapter.addFragment(f1, "Выставление баллов");
            adapter.addFragment(f2, "Баллы членов комиссии");
            mViewPager.setAdapter(adapter);
        }

    }
}
