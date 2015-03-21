package com.theappsignal.lauresoft.theappsignal;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.theappsignal.lauresoft.theappsignal.adapters.TabSliderAdapter;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager pager;
    private TabSliderAdapter adapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new TabSliderAdapter(getSupportFragmentManager());
        actionBar = getActionBar();
        actionBar.setTitle("The App Signal");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        pager.setAdapter(adapter);

        //Kan performanter met string array
        actionBar.addTab(actionBar.newTab().setText("Home")
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Contacts")
                .setTabListener(this));

        actionBar.addTab(actionBar.newTab().setText("Message")
                .setTabListener(this));

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                switch(position)
                {
                    case 0 : actionBar.setTitle("The App Signal");
                        break;
                    case 1 : actionBar.setTitle("Contacts");
                        break;
                    case 2 : actionBar.setTitle("Message");
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


        String intentaction = getIntent().getAction();

        if (intentaction != null) {
            if (intentaction.equals(".NewContact")) {
                actionBar.setSelectedNavigationItem(1);
            }
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
