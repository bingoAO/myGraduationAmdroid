package com.example.companyuo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by sheshihao385 on 15/12/23.
 * 鎺у埗鐢荤殑绫诲瀷锛屽渾褰㈡垨鑰呭渾瑙掔煩褰�
 * 鎺у埗鍦嗚鐨勫ぇ灏�
 * 鎺у埗浣滀负鑳屾櫙鐨刡itmap
 * 鐭╁舰鏈夊緟鎷撳睍
 */
public class CircleView extends ImageView {

    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;

    private int style;//鍦嗚杩樻槸鍦嗗舰
    private Bitmap mBitmap;//鎺у埗鑳屾櫙

    private Paint mPaint;
    private RectF roundRect;
    private BitmapShader mBitmapShader;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("CircleView", "-->" + 1);
        intitView(context, attrs);

    }

    public CircleView(Context context) {
        this(context, null);
    }

    public void intitView(Context context,AttributeSet attrs){

        super.setScaleType(SCALE_TYPE);

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
//        radius = a.getDimensionPixelSize(R.styleable.CircleView_radius,30);
//        style = a.getInt(R.styleable.CircleView_type, Constant.Circle);
//        cornerRaidus = a.getDimensionPixelSize(R.styleable.CircleView_corner, 10);
//        a.recycle();

    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        Log.d("CircleView", "--->haha");
        super.setImageMatrix(matrix);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = ImageTools.drawableToBitmap(drawable);
        setUpViews();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = ImageTools.drawableToBitmap(getDrawable());
        setUpViews();
    }

    private void setUpViews() {
        if(mPaint==null){
            mPaint = new Paint();
        }

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setShader(mBitmapShader);
        invalidate();
    }

    public void setStyle(int style){
        this.style = style;
        invalidate();
    }

    public int getStyle() {
        return style;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (null != mBitmap) {
            //澶勭悊Bitmap 杞垚姝ｆ柟褰�
            Bitmap newBitmap = dealRawBitmap(mBitmap);
            //灏唍ewBitmap 杞崲鎴愬渾褰�
            Bitmap circleBitmap = toRoundCorner(newBitmap, 14);

            final Rect rect = new Rect(0, 0, circleBitmap.getWidth(), circleBitmap.getHeight());
            mPaint.reset();
            //缁樺埗鍒扮敾甯冧笂
            canvas.drawBitmap(circleBitmap, rect, rect, mPaint);
        } else {
            super.onDraw(canvas);
        }
    }

    //灏嗗師濮嬪浘鍍忚鍓垚姝ｆ柟褰�
    private Bitmap dealRawBitmap(Bitmap bitmap){

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if(width==height) {
            return bitmap;
        }
        //鑾峰彇瀹藉害
        int minWidth = width > height ?  height:width ;
        //璁＄畻姝ｆ柟褰㈢殑鑼冨洿
        int leftTopX = (width - minWidth)/2;
        int leftTopY = (height - minWidth)/2;
        //瑁佸壀鎴愭鏂瑰舰
        Bitmap newBitmap = Bitmap.createBitmap(bitmap,leftTopX,leftTopY,minWidth,minWidth,null,false);
        return  scaleBitmap(newBitmap);
    }

    //灏嗗ご鍍忔寜姣斾緥缂╂斁
    private Bitmap scaleBitmap(Bitmap bitmap){
        int width = getWidth();
        //涓�瀹氳寮鸿浆鎴恌loat 涓嶇劧鏈夊彲鑳藉洜涓虹簿搴︿笉澶� 鍑虹幇 scale涓�0 鐨勯敊璇�
        float scale = (float)width/(float)bitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

    }

    private Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        //鎸囧畾涓� ARGB_4444 鍙互鍑忓皬鍥剧墖澶у皬
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0,bitmap.getWidth(), bitmap.getHeight());
        mPaint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        mPaint.setColor(color);
        int x = bitmap.getWidth();
        canvas.drawCircle(x / 2, x / 2, x / 2, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, mPaint);
        return output;
    }

    public void setmBitmap(Bitmap bitmap){
        this.mBitmap = bitmap;
        invalidate();
    }
}


