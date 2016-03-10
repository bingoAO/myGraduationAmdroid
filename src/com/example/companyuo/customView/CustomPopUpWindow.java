package com.example.companyuo.customView;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by sheshihao385 on 16/1/18.
 */
public class CustomPopUpWindow extends PopupWindow {

    private PopupWindow popupWindow;

    public CustomPopUpWindow(View v){
        initView(v);
    }

    private void initView(View view){
        popupWindow = new PopupWindow(view);
        popupWindow.setFocusable(true);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupWindow.setWidth(view.getMeasuredWidth() + 40);
        popupWindow.setHeight(view.getMeasuredHeight() + 80);

        //聽璁剧疆澶栭儴鍙偣鍑�
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);

    }

    /**
     * v涓哄弬鑰冧綅缃�
     */
    public void showBelow(View v){
        //set location
        popupWindow.showAsDropDown(v);
    }

    /**
     * 瀹瑰櫒姝ｄ腑鏄剧ず
     * 鐗堟湰鎺у埗
     * @param view
     */
    @TargetApi(16)
    public boolean showMiddle(View view){
        if(Build.VERSION.SDK_INT <= 16){
            // 浣跨敤api11 鏂板姞 api
            return false;
        }
        popupWindow.showAtLocation(view, Gravity.CENTER, view.getMinimumWidth() / 2, view.getMinimumHeight() / 2);
        return true;
    }




}
