package com.example.companyuo;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by sheshihao385 on 15/12/30.
 */
public class DataObtainAsynTask extends AsyncTask {
    private RefreshListView refreshListView;

    public DataObtainAsynTask(RefreshListView refreshListView) {
        super();
        this.refreshListView = refreshListView;
    }

    /**
     * 鍋氱綉缁滄搷浣�
     * @param params
     * @return
     */
    @Override
    protected Object doInBackground(Object[] params) {
        getData();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        refreshListView.onRefreshComplete();
        refreshListView = null;//娉ㄦ剰闈炲紓姝ラ棶棰�,聽鍥炴敹寮曠敤瀵硅薄

    }


    public void getData() {
        Log.d("DataObtainAsynTask","-------->hello");
    }
}
