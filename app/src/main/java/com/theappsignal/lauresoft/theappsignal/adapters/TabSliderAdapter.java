package com.theappsignal.lauresoft.theappsignal.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.theappsignal.lauresoft.theappsignal.contacts.ContactsFragment;
import com.theappsignal.lauresoft.theappsignal.message.HomeFragment;
import com.theappsignal.lauresoft.theappsignal.message.MessageFragment;

/**
 * Created by Laure on 9/11/2014.
 */
public class TabSliderAdapter extends FragmentPagerAdapter
{

    public TabSliderAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new HomeFragment();
            case 1:
                // Games fragment activity
                return new ContactsFragment();
            case 2:
                // Movies fragment activity
                return new MessageFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
