package com.condingblocks.multicopy.views.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.condingblocks.multicopy.Adapters.ViewPagerAdapter;
import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.Utils.Constants;
import com.condingblocks.multicopy.Utils.PrefsManager;

public class WelcomeActivity extends AppCompatActivity {

    PrefsManager prefsManager;
    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button btnSkip;
    Button btnNext;
    int[] layouts;
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        prefsManager = new PrefsManager(this);
        Intent i = getIntent();
        int fromBase = i.getIntExtra(Constants.BASE_TO_WELCOME,0);
        if (!prefsManager.isFirstTimeLaunch()) {
            if(fromBase != 777) {
            launchHomeScreen();
            finish();
            }
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        viewPager = (ViewPager) findViewById(R.id.introViewpager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);
        layouts = new int[]{
                R.layout.welcome_1,
                R.layout.welcome_2,
                R.layout.welcome_3,
                R.layout.welcome_4,
                R.layout.welcome_5
        };

        addBottomDots(0);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });

        viewPagerAdapter = new ViewPagerAdapter(this,layouts);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                addBottomDots(position);
                if (position == layouts.length - 1) {
                    btnNext.setText(getString(R.string.start));
                    btnSkip.setVisibility(View.GONE);
                } else {
                    btnNext.setText(getString(R.string.next));
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);

    }

    public int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    public void launchHomeScreen() {
        prefsManager.setIsFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this,BaseActivity.class));
        finish();
    }

}


