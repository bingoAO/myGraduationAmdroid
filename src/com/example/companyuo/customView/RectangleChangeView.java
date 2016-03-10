package com.example.companyuo.customView;

import com.example.companyuo.Constants;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class RectangleChangeView extends ImageView{
	
	private Paint mPaint = new Paint();
	
	private Bitmap mBitmap;
    private float r = 24;
    
    private int drawStyle = 0;
	
	public RectangleChangeView(Context context) {
		super(context);
		Log.d("RectangleChangeView","hello,this is ondraw1");

	}
	
	public RectangleChangeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.d("RectangleChangeView","hello,this is ondraw2");

	}

	public RectangleChangeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Log.d("RectangleChangeView","hello,this is ondraw3");

	}
	
	public void setHeadImage(Bitmap mBitmap){
		this.mBitmap = mBitmap;
	}
	
	public void setR(float r){//锟斤拷锟矫半径锟斤拷
		this.r = r;
	}
	
	public void setDrawStyle(int drawStyle){
		this.drawStyle = drawStyle;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.d("RectangleChangeView","hello,this is ondraw");
		mPaint.setAntiAlias(true);//锟斤拷锟斤拷锟睫撅拷锟�
		mPaint.setColor(Color.BLACK);//锟斤拷锟矫伙拷锟斤拷锟斤拷色
		mPaint.setStrokeWidth((float)3.0);//锟斤拷锟斤拷锟竭的匡拷锟�
		mPaint.setStyle(Style.STROKE);//锟斤拷锟矫匡拷锟斤拷效锟斤拷
		
		switch(drawStyle){
		case Constants.RoundRect:
			drawRoundRect(canvas);
			break;
			
		case Constants.Circle:
			drawCircle(canvas);
			break;
		
		}
				
	}
	
	private void drawRoundRect(Canvas canvas){
		RectF rect = new RectF();
		rect.left = 0;
		rect.top = 0;
		rect.right = 48;
		rect.bottom = 48;
		
		if(mBitmap!=null){
		canvas.setBitmap(mBitmap);
		}else{
		canvas.drawColor(Color.BLUE);//锟斤拷色锟斤拷锟斤拷
		}
		drawRoundRect(canvas);
	}

	private void drawCircle(Canvas canvas){
		canvas.drawCircle(r, 24, 24, mPaint);
	}


}
