package com.example.companyuo.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;


import java.util.ArrayList;
import java.util.List;

import com.example.companyuo.R;
import com.example.companyuo.adapter.HealPagerAdapter;
import com.example.companyuo.fragment.ReadingFragment;
import com.example.companyuo.fragment.TestFragment;

public class HealBySelfActivity extends FragmentActivity implements OnClickListener,OnPageChangeListener{

	private ReadingFragment readingFragment;
	private TestFragment testFragment;
	
	private View readingTab;
	private View testingTab;
		
	private ViewPager viewPager;
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	private HealPagerAdapter fragPagerAdapter;
	
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		initView();
		findView();
		setupView();
	}

	public void initView() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_heal_layout);
		}

	public void findView() {
		readingTab = findViewById(R.id.reading_tab);
		testingTab = findViewById(R.id.testing_tab);
		//TODO
		viewPager = (ViewPager) findViewById(R.id.content);

	}

	public void setupView() {

		readingTab.setOnClickListener(this);
		testingTab.setOnClickListener(this);
		viewPager.setOnPageChangeListener(this);
		
		setTabSelection(0);
		setViewPager();
	}
	
	public void setTabSelection(int type){//�л�fragment�͸ı�tab��״̬
		clearSelection();
		switch(type){
		
		case 0:
			readingTab.setBackgroundColor(getResources().getColor(R.color.light_blue));
			break;
			
		case 1:
			testingTab.setBackgroundColor(getResources().getColor(R.color.light_blue));
			break;
			
		default:
			
			break;
		}
	}
	
	public void clearSelection(){
		readingTab.setBackgroundColor(getResources().getColor(R.color.orange));
		testingTab.setBackgroundColor(getResources().getColor(R.color.orange));
	}
	
	public void hideFragments(FragmentTransaction transaction){
		if(readingFragment!=null){
			transaction.hide(readingFragment);
		}
		if(testFragment!=null){
			transaction.hide(testFragment);
		}
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.reading_tab:
			setTabSelection(0);
			if(viewPager.getCurrentItem()!=0)
			viewPager.setCurrentItem(0);
			break;
			
		case R.id.testing_tab:
			setTabSelection(1);
			if(viewPager.getCurrentItem()!=1)
			viewPager.setCurrentItem(1);
			break;
			
		default:
			break;
		}
		
	}
	
	public void setViewPager(){
		if(readingFragment!=null&&!fragmentList.contains(readingFragment)){
			fragmentList.add(readingFragment);
		}else if(!fragmentList.contains(readingFragment)){
			readingFragment = new ReadingFragment();
			fragmentList.add(readingFragment);
		}
		
		if(testFragment!=null&&!fragmentList.contains(testFragment)){
			fragmentList.add(testFragment);
		}else if(!fragmentList.contains(testFragment)){
			testFragment = new TestFragment();
			fragmentList.add(testFragment);
		}
		fragPagerAdapter = new HealPagerAdapter(getSupportFragmentManager());
		fragPagerAdapter.setFragmentList(fragmentList);
		viewPager.setAdapter(fragPagerAdapter);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		
	}

	@Override
	public void onPageSelected(int type) {
		setTabSelection(type);
	}

	
	public void onBack(View v){
		finish();
	}
}
