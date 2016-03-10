package com.example.companyuo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.companyuo.Constants;
import com.example.companyuo.DataObtainAsynTask;
import com.example.companyuo.LoadMoreAsync;
import com.example.companyuo.R;
import com.example.companyuo.RefreshAdapter;
import com.example.companyuo.RefreshListView;

/**
 * Created by sheshihao385 on 16/2/17.
 */
public class HealBySelfFragment extends Fragment implements View.OnClickListener,RefreshListView.OnRefreshListener,RefreshListView.OnLoadMoreDataListener{

    private List<String> hello = new ArrayList<String>();
    private RefreshListView refreshListView;
    private RefreshAdapter refreshAdapter;

    private void makeFakeData(){

        refreshListView.setOnLoadMoreListener(this);
        refreshListView.setOnRefreshListener(this);

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
            refreshListView.setAdapter(refreshAdapter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.frag_heal,null);
        refreshListView = (RefreshListView) convertView.findViewById(R.id.heal_refresh);
        makeFakeData();
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 鎵嬪娍涓嬫媺鍒锋柊
     */
    @Override
    public void onRefresh() {
        Log.d("MainActivity", "-->kao");
        DataObtainAsynTask asynTask = new DataObtainAsynTask(refreshListView);
        asynTask.execute();
    }


    /**
     * 涓婃媺鍔犺浇鏇村
     */
    @Override
    public void onLoadMore() {
        if(getActivity()!=null) {
            LoadMoreAsync loadMoreAsync = new LoadMoreAsync(getActivity());
            loadMoreAsync.execute();
        }
    }

    /**
     * 寮傛浠诲姟瀹屾垚涔嬪悗鍥炶皟锛屾敼鍙樺埛鏂板垪琛ㄧ殑footer鐘舵��
     */
    @Override
    public void onLoadComplete() {
        /**
         * 璋冪敤ListView鐨勪笢瑗�
         */
        if(getActivity()!=null)
        Toast.makeText(getActivity(), "鍔犺浇瀹屾垚", Toast.LENGTH_SHORT).show();
        refreshListView.changeFooterView(Constants.COMPLETED);
    }
}
