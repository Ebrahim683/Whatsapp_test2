package com.example.whatsapp_test2.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsapp_test2.Fragment.CallsFragment;
import com.example.whatsapp_test2.Fragment.ChatFragment;
import com.example.whatsapp_test2.Fragment.StatusFragment;

import org.jetbrains.annotations.NotNull;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0: return fragment = new ChatFragment();

            case 1: return fragment =  new StatusFragment();

            case 2: return fragment =  new CallsFragment();

            default: fragment =  new ChatFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "CHATS";
        }
        if (position == 1){
            title = "STATUS";
        }
        if (position == 2){
            title = "CALLS";
        }
        return title;
    }
}
