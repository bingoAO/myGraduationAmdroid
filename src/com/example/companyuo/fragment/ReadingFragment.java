package com.example.companyuo.fragment;

import com.example.companyuo.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadingFragment extends Fragment{

	private TextView textView;
	public ReadingFragment(){
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View readingLayout = inflater.inflate(R.layout.fragment_reading_layout,container,false);
		textView = (TextView) readingLayout.findViewById(R.id.asyoulike);
		textView.setText("reading!!");
		return readingLayout;
	}

}
