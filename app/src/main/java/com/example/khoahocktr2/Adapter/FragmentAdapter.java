package com.example.khoahocktr2.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.khoahocktr2.Fragment.Infomation;
import com.example.khoahocktr2.Fragment.List;
import com.example.khoahocktr2.Fragment.Search;



public class FragmentAdapter extends FragmentPagerAdapter {
    private int pageNumber;
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new List();
            case 1:
                return new Infomation();
            case 2:
                return new Search();
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageNumber;
    }
}
