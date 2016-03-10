package com.example.companyuo.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

import com.example.companyuo.CircleView;
import com.example.companyuo.LogUtils;
import com.example.companyuo.R;
import com.example.companyuo.adapter.MainPagerAdapter;
import com.example.companyuo.fragment.HealBySelfFragment;
import com.example.companyuo.fragment.QueryFragment;
import com.example.companyuo.fragment.SourceFragment;

public class MainActivity extends FragmentActivity implements OnClickListener,OnPageChangeListener{

    private HealBySelfFragment healBySelfFragment;
	private QueryFragment queryFragment;
	private SourceFragment sourceFragment;
	
	private View healTab;
	private View queryTab;
	private View sourceTab;
		
	private ViewPager viewPager;
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	private MainPagerAdapter fragPagerAdapter;

    private CircleView circleView;
    private DrawerLayout parentDrawer;
    private RelativeLayout childDrawer;

    @Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		initView();
		findView();
		setupView();
	}

	public void initView() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		}

	public void findView() {
		healTab = findViewById(R.id.heal_tab);
		queryTab = findViewById(R.id.query_tab);
		sourceTab = findViewById(R.id.source_tab);
		//TODO
		viewPager = (ViewPager) findViewById(R.id.content);

        //left menu
        circleView = (CircleView) findViewById(R.id.circle_view);
        childDrawer = (RelativeLayout) findViewById(R.id.drawer);
        parentDrawer = (DrawerLayout) findViewById(R.id.parent_drawer);

    }

	public void setupView() {

		healTab.setOnClickListener(this);
		queryTab.setOnClickListener(this);
		sourceTab.setOnClickListener(this);
		viewPager.setOnPageChangeListener(this);

//        left menu
        circleView.setOnClickListener(this);

		setTabSelection(0);
		setViewPager();
        setLeftMenu();
	}

    private void setLeftMenu() {

    }

    public void setTabSelection(int type){
		clearSelection();
		switch(type){
		
		case 0:
			healTab.setBackgroundColor(getResources().getColor(R.color.light_blue));
			break;
			
		case 1:
			queryTab.setBackgroundColor(getResources().getColor(R.color.light_blue));
			break;

		case 2:
			sourceTab.setBackgroundColor(getResources().getColor(R.color.light_blue));
			break;
			
		default:
			
			break;
		}
	}
	
	public void clearSelection(){
		healTab.setBackgroundColor(getResources().getColor(R.color.orange));
		queryTab.setBackgroundColor(getResources().getColor(R.color.orange));
		sourceTab.setBackgroundColor(getResources().getColor(R.color.orange));
	}
	
	public void hideFragments(FragmentTransaction transaction){
		if(healBySelfFragment!=null){
			transaction.hide(healBySelfFragment);
		}
		if(queryFragment!=null){
			transaction.hide(queryFragment);
		}
		if(sourceFragment!=null){
			transaction.hide(sourceFragment);
		}
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.heal_tab:
			setTabSelection(0);
			if(viewPager.getCurrentItem()!=0)
			viewPager.setCurrentItem(0);
			break;
			
		case R.id.query_tab:
			setTabSelection(1);
			if(viewPager.getCurrentItem()!=1)
			viewPager.setCurrentItem(1);
			break;

        case R.id.source_tab:
            setTabSelection(2);
            if(viewPager.getCurrentItem()!=2)
                viewPager.setCurrentItem(2);
            break;

        //leftMenu
        case R.id.circle_view:
            parentDrawer.openDrawer(childDrawer);
            break;

		default:
			break;
		}
		
	}
	
	public void setViewPager(){
		if(healBySelfFragment!=null&&!fragmentList.contains(healBySelfFragment)){
			fragmentList.add(healBySelfFragment);
		}else if(!fragmentList.contains(healBySelfFragment)){
            LogUtils.d("heal");
			healBySelfFragment = new HealBySelfFragment();
			fragmentList.add(healBySelfFragment);
		}
		
		if(queryFragment!=null&&!fragmentList.contains(queryFragment)){
			fragmentList.add(queryFragment);
		}else if(!fragmentList.contains(queryFragment)){
			queryFragment = new QueryFragment();
			fragmentList.add(queryFragment);
		}

		if(sourceFragment!=null&&!fragmentList.contains(sourceFragment)){
			fragmentList.add(sourceFragment);
		}else if(!fragmentList.contains(sourceFragment)){
			sourceFragment = new SourceFragment();
			fragmentList.add(sourceFragment);
		}

		fragPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
		fragPagerAdapter.setFragmentList(fragmentList);
        setScollable();
		viewPager.setAdapter(fragPagerAdapter);
	}

    private void setScollable() {
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
	public void onPageScrollStateChanged(int arg0) {
        LogUtils.d("hello"+arg0);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		
	}

	@Override
	public void onPageSelected(int type) {
        LogUtils.d(type);
        setTabSelection(type);
	}

	
	public void onBack(View v){
		finish();
	}
}
