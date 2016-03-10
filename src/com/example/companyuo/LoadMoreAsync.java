package com.example.companyuo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by sheshihao385 on 16/1/11.
 */
public class LoadMoreAsync extends AsyncTask {

    private  Context context;

    public LoadMoreAsync(Context context) {
        super();
        this.context = context;
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
//        ((MainActivity)context).onLoadComplete();
    }


    public void getData() {
        Log.d("DataObtainAsynTask", "-------->hello");
    }


}
