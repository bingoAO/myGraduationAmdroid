package com.example.companyuo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HealPagerAdapter extends FragmentPagerAdapter{

	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	
	public void setFragmentList(List<Fragment> fragmentList){
		this.fragmentList = fragmentList;
	}
	
	
	
	public HealPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int type) {
		
		return fragmentList.get(type);
	}

	@Override
	public int getCount() {
		
		return fragmentList.size();
	}

}
