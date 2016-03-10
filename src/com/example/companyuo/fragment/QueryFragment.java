package com.example.companyuo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import com.example.companyuo.LogUtils;
import com.example.companyuo.R;
import com.example.companyuo.RefreshAdapter;

/**
 * Created by sheshihao385 on 16/2/17.
 */
public class QueryFragment extends Fragment{

    private ListView listView;
    private List<String> hello = new ArrayList<String>();
    private RefreshAdapter refreshAdapter;

    private void makeFakeData(){

        hello.add("hi");
        hello.add("haha");
        hello.add("wawa");
        hello.add("wawa");
        hello.add("wawa");
        hello.add("wawa");
        hello.add("wawa");
        hello.add("wawa");
        hello.add("wawa");
        hello.add("wawa");

        if(getActivity()!=null) {
            refreshAdapter = new RefreshAdapter(getActivity(), hello);
            listView.setAdapter(refreshAdapter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.d("heal");
        View convertView = inflater.inflate(R.layout.frag_query,null);
        listView = (ListView) convertView.findViewById(R.id.list_view);
        makeFakeData();
        return convertView;
    }
}
