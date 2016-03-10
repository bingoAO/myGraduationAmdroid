package com.example.companyuo;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import com.example.companyuo.activity.HealerActivity;

/**
 * Created by sheshihao385 on 15/12/29.
 */
public class RefreshListView extends ListView implements AdapterView.OnItemClickListener {

    private static String TAG = "RefreshListView";

    private Context mContext;

    //State
    private static final int STATE_DRAGGING = 0;
    private static final int STATE_RELEASING = 1;
    private static final int STATE_REFRESHING = 2;
    private static final int STATE_COMPLETE = 3;

    private int state;
    private boolean isRefreshable;

    private LinearLayout headView;
    private LinearLayout footView;
    private LayoutInflater inflater;

    private int headWidth;
    private int headHeight;

    private int firstItemIndex;//flag
    private int startY;
    private boolean isRecorded;//鐢ㄤ簬淇濊瘉startY鐨勫�煎湪涓�涓畬鏁寸殑touch浜嬩欢涓彧琚褰曚竴娆°��


    private TextView tipsTxt;
    private TextView updateTxt;
    private ImageView arrowImg;
    private ProgressBar progressBar;

    private TextView footerTips;
    private ImageView footerArrow;
    private ProgressBar footerProg;

    private RotateAnimation animation;
    private RotateAnimation reverseAnimation;

    private boolean isBack = false;
    private boolean isFooterLoading = false;

    private int count = 0;//鏍规嵁杩欎釜杩借釜鏍囧織鏄惁宸茬粡婊戝埌鏈�搴曢儴
    private int lastItem  = 0;
    private RefreshAdapter refreshAdapter;

    //TODO about Ratio
    // 瀹為檯鐨刾adding鐨勮窛绂讳笌鐣岄潰涓婂亸绉昏窛绂荤殑姣斾緥
    private final static int RATIO = 3;

    public RefreshListView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    private void initView(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        Log.d("RefreshListView","---->1");
        setHeadView();
        setFooterView();
        findView();
        setListener();
        initAnimation();
        initFlag();
    }

    private void findView() {
        arrowImg = (ImageView) headView
                .findViewById(R.id.arrow);
//        arrowImg.setMinimumWidth(70);
//        arrowImg.setMinimumHeight(50);
        progressBar = (ProgressBar) headView
                .findViewById(R.id.progress);
        tipsTxt = (TextView) headView.findViewById(R.id.tips);
        updateTxt = (TextView) headView
                .findViewById(R.id.last_updated);


        footerTips = (TextView)findViewById(R.id.footer_tips);
        footerProg = (ProgressBar) findViewById(R.id.footer_prog);
        footerArrow = (ImageView) findViewById(R.id.footer_arrow);
    }

    private void setListener() {
//        setOnScrollListener(this);
        setOnItemClickListener(this);
    }

    private void setHeadView() {
        //add headView
        headView = (LinearLayout) inflater.inflate(R.layout.head_view, null);
        measureView(headView);
        headHeight = headView.getMeasuredHeight();
        headWidth = headView.getMeasuredWidth();

        headView.setPadding(headView.getPaddingLeft(), -headHeight, headView.getPaddingRight(), headView.getPaddingBottom());
        headView.invalidate();
        addHeaderView(headView, null, false);
        Log.d("RefreshListView", "---->HEADHEIGHT:" + headHeight);

    }

    private void initAnimation() {
        animation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);

        reverseAnimation = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(200);
        reverseAnimation.setFillAfter(true);
    }

    private void initFlag() {
        state = STATE_COMPLETE;
        isRefreshable = false;
    }

    private void measureView(LinearLayout headView) {
        ViewGroup.LayoutParams p = headView.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        headView.measure(childWidthSpec, childHeightSpec);
    }

    private void changeHeadView(int state) {
        switch (state) {
            case STATE_DRAGGING:
                Log.d("RefreshListView","---->6");
                progressBar.setVisibility(View.GONE);
                arrowImg.clearAnimation();
                arrowImg.setVisibility(View.VISIBLE);

                if (isBack) {
                    isBack = false;
                    arrowImg.clearAnimation();
                    arrowImg.startAnimation(reverseAnimation);
                    tipsTxt.setText("涓嬫媺鍒锋柊");
                } else {
                    tipsTxt.setText("涓嬫媺鍒锋柊");
                }
                break;

            case STATE_RELEASING:
                arrowImg.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                arrowImg.clearAnimation();
                arrowImg.startAnimation(animation);

                tipsTxt.setText("鏉惧紑鍒锋柊");

                break;

            case STATE_REFRESHING:
                headView.setPadding(0, 0, 0, 0);
                progressBar.setVisibility(View.VISIBLE);
                arrowImg.clearAnimation();
                arrowImg.setVisibility(View.GONE);
                tipsTxt.setText("姝ｅ湪鍒锋柊...");
                break;

            case STATE_COMPLETE:
                progressBar.setVisibility(View.GONE);
                arrowImg.clearAnimation();
                arrowImg.setImageResource(R.drawable.arrow_down);
                tipsTxt.setText("涓嬫媺鍒锋柊");
                headView.setPadding(headView.getPaddingLeft(), -1 * headHeight, headView.getPaddingRight(), headView.getPaddingBottom());
                Log.d("RefreshListView","----->hahah");
                break;
        }

    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        Log.d(TAG,"---->i'm onScrollStateChanged");
//
//        if(refreshAdapter==null){
//            count = 0;
//        }else {
//            count = refreshAdapter.getCount();
//        }
//        if((lastItem == count) && (scrollState == this.SCROLL_STATE_IDLE)&&(!isFooterLoading)){
//            Log.d(TAG,"---->i'm onScrollStateChanged--if");
//            changeFooterView(Constants.LOADING);
//            loadMoreData();
//        }
//        }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        Log.d(TAG,"---->i'm onScroll");
//        firstItemIndex = firstVisibleItem;
//        lastItem  = firstVisibleItem + visibleItemCount - 2;//杩欓噷鍑�2鏄噺鍘诲ご閮ㄥ拰灏鹃儴
//
//    }


    public void changeFooterView(int state){
        Log.d(TAG,"---->i'm changeFooterView");
        switch (state){
            case Constants.LOADING:
                isFooterLoading = true;
                Log.d(TAG,"---->i'm Loading");
                footerArrow.setVisibility(View.GONE);
                footerProg.setVisibility(View.VISIBLE);
                footerTips.setText("Loading...");
                break;
            case Constants.COMPLETED:
                isFooterLoading = false;
                footerArrow.setVisibility(View.VISIBLE);
                footerProg.setVisibility(View.GONE);
                footerTips.setText("鍚戜笂鍔犺浇鏇村");
                break;
        }

    }

    private void loadMoreData(){
        Log.d(TAG,"---->i'm onLoadMoreData");
        /**
         * 杩欓噷搴旇寮�绾跨▼鏉ュ姞杞芥柊鏁版嵁
         * 骞朵笖鐩戝惉鏁版嵁鏄惁鍔犺浇瀹屾垚锛屾潵鏀瑰彉footer鐨勫竷灞�
         * loadmorefinish鐨勬椂鍊欙紝搴旇淇敼isFooterLoading涓篺alse
         */
        mOnLoadMoreListener.onLoadMore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isRefreshable) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (firstItemIndex == 0 && !isRecorded) {
                        isRecorded = true;
                        startY = (int) event.getY();
                    }
                    break;

                case MotionEvent.ACTION_MOVE://鐘舵�佽浆鎹�
                    int tempY = (int) event.getY();

//                    if (!isRecorded && firstItemIndex == 0) {
                        isRecorded = true;
                        startY = tempY;
//                    }
                    if(state!=STATE_REFRESHING){
                        if(state == STATE_RELEASING){
                            setSelection(0);

                            if (((tempY - startY) / RATIO < headHeight)
                                    && (tempY - startY) > 0) {
                                state = STATE_DRAGGING;
                                changeHeadView(state);
                            }

                            else if (tempY - startY <= 0) {
                                state = STATE_COMPLETE;
                                changeHeadView(state);
                            }
                            else {
                            }
                        }
                        if(state == STATE_DRAGGING){
                            setSelection(0);
                            if ((tempY - startY) / RATIO >= headHeight) {
                                state = STATE_RELEASING;
                                isBack = true;
                                changeHeadView(state);
                            }else if(tempY - startY <= 0){
                                state = STATE_COMPLETE;
                                changeHeadView(state);
                            }

                        }
                        if(state == STATE_COMPLETE){
                            if (tempY - startY > 0) {
                                state = STATE_DRAGGING;
                                changeHeadView(state);
                            }
                        }

                        // 鏇存柊headView鐨剆ize
                        if (state == STATE_DRAGGING) {
                            Log.d("RefreshListView","---->5");
                            headView.setPadding(0, -1 * headHeight
                                    + (tempY - startY) / RATIO, 0, 0);

                        }

                        // 鏇存柊headView鐨刾addingTop
                        if (state == STATE_RELEASING) {
                            headView.setPadding(0, (tempY - startY) / RATIO
                                    - headHeight, 0, 0);
                        }

                    }

                    break;

                case MotionEvent.ACTION_UP:
                    if (state != STATE_REFRESHING) {
                        if (state == STATE_COMPLETE) {
                            // 浠�涔堥兘涓嶅仛
                        }
                        if (state == STATE_DRAGGING) {
                            state = STATE_COMPLETE;
                            changeHeadView(state);
                        }
                        if (state == STATE_RELEASING) {
                            state = STATE_REFRESHING;
                            changeHeadView(state);
                            Log.d("RefreshListView","--->hello world");
                            refreshListener.onRefresh();
                        }
                    }

                    isRecorded = false;
                    isBack = false;

                    break;
            }
        }

        return super.onTouchEvent(event);

    }
    public void setAdapter(BaseAdapter adapter) {
        updateTxt.setText("涓婃鏇存柊:" + new Date().toLocaleString());
        refreshAdapter = (RefreshAdapter) adapter;//杩欓噷鍙兘浼氭湁鐐归棶棰橈紝鍏堢暀鐫�锛屽洖鏉ヨВ鍐�
        super.setAdapter(adapter);
    }

    private OnRefreshListener refreshListener;

    public void setOnRefreshListener(OnRefreshListener refreshListener){
        this.refreshListener = refreshListener;
        isRefreshable = true;
    }

    public void setFooterView() {
        footView = (LinearLayout) inflater.inflate(R.layout.foot_view,null);
        addFooterView(footView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(mContext,HealerActivity.class);
        mContext.startActivity(intent);
    }

    public interface OnRefreshListener{
        void onRefresh();
    }
    public void onRefreshComplete() {
        Log.d("RefreshListView","----->refresh over");
        state = STATE_COMPLETE ;
        updateTxt.setText("涓婃鏇存柊:" + new Date().toLocaleString());
        changeHeadView(state);
    }

    /**
     * interface for load more data
     */
    public interface  OnLoadMoreDataListener{
        void onLoadMore();
        void onLoadComplete();
    }

    public void setOnLoadMoreListener(OnLoadMoreDataListener onLoadMoreListener){
        mOnLoadMoreListener = onLoadMoreListener;
    }

    private OnLoadMoreDataListener mOnLoadMoreListener;

}