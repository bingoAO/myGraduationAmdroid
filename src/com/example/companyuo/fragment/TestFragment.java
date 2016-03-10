package com.example.companyuo.fragment;

import com.example.companyuo.Constants;
import com.example.companyuo.R;
import com.example.companyuo.customView.RectangleChangeView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFragment extends Fragment{
	private TextView textView;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		RectangleChangeView rect = new RectangleChangeView(getActivity());
		rect.setDrawStyle(Constants.Circle);
		
		
		View testingLayout = inflater.inflate(R.layout.fragment_testing_layout, container,false);
		textView = (TextView) testingLayout.findViewById(R.id.asyoulike);
		textView.setText("testing!!");
		return testingLayout;
	}
}
