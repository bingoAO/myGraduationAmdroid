package com.example.companyuo.activity;

import com.example.companyuo.LogUtils;
import com.example.companyuo.R;
import com.example.companyuo.interfacepack.OnBlurListener;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;


/**
 * Created by sheshihao385 on 16/1/13.
 */
public class HealerActivity extends BaseActivity implements View.OnClickListener,OnBlurListener{

    private LinearLayout background;
    private final Context context = this;

    @Override
    public void initDatas() {
        setContentView(R.layout.healer_layout);

    }

    private void initBlurBackground() {

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
//            }
//        };
        //views
        //TODO锛氣�滃湪涓荤嚎绋嬶紝浼氫笉浼氭湁闃诲鐨勯闄�  鏁堢巼澶綆锛岀偣鍑诲崱椤匡紝涓嶅�硷紝杩欑鏈夊緟瑙ｅ喅锛岄毦閬撹浣跨敤鍥炶皟锛宐lur瀹屼簡涔嬪悗鐩存帴setBitmap
        LogUtils.d("blur bitmap ");

        background.setBackgroundDrawable(getResources().getDrawable(R.drawable.dolat));
//        Bitmap bitmap = ImageTools.bitmapFromRes(this, R.drawable.dolat);
//        Bitmap newBitmap = ImageTools.blurBitmap(this, bitmap);
//        background.setBackgroundDrawable(ImageTools.bitmapToDrawable(newBitmap));
    }

    @Override
    public void findViews() {
        background= (LinearLayout) findViewById(R.id.person_card);

    }

    @Override
    public void setUpListeners() {
        background.setOnClickListener(this);

    }

    @Override
    public void setUpViews() {
        initBlurBackground();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.person_card:
                break;
        }
    }


    @Override
    public void onBlurFinish() {
        LogUtils.d("onBlurFinish");

    }
}
