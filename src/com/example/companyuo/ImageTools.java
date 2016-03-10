package com.example.companyuo;

import com.example.companyuo.interfacepack.OnBlurListener;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by sheshihao385 on 15/12/28.
 */
public class ImageTools {

    private OnBlurListener onBlurListener;

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            } else {

                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    //鍥犱负BitmapDrawable鏄疍rawable鐨勫瓙绫伙紝鎵�浠ュ彲浠ョ洿鎺�
    public static BitmapDrawable bitmapToDrawable(Bitmap bitmap){
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        return bitmapDrawable;
    }

    public static Bitmap bitmapFromRes(Activity activity,int resId ){

        Resources res = activity.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res,resId);
        return bitmap;

    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurBitmap(Context context,Bitmap bitmap){

        //鍒涘缓涓�涓笌鎴戜滑鎯宠妯＄硦鐨刡itmap涓�鏍峰ぇ灏忕殑绌篵itmap
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //鍒濆鍖栦竴涓柊鐨凴enderscript
        final RenderScript rs = RenderScript.create(context);

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
//            }
//        }.start();
        //浣跨敤RenderScript鍒涘缓涓�涓浐鏈夌殑Script blur
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(25.f);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;
    }


}
