package com.example.companyuo.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import com.example.companyuo.CircleView;
import com.example.companyuo.R;
import com.example.companyuo.bean.Person;
import com.example.companyuo.customView.CustomPopUpWindow;

public class MainActivity2 extends Activity implements View.OnClickListener{

    private CircleView circleView;
    private RelativeLayout childDrawer;

//    private List<String> hello = new ArrayList<>();
//    private RefreshListView refreshListView;
//    private RefreshAdapter refreshAdapter;


    //drawer layout
    private CircleView changeHeadImage;
    private TextView nikeName;
    private EditText changeNikeName;
    private TextView correctNikeNameTag;

    private Person person;

    public final static int CAMERA = 0;
    public final static int GALLERY = 1;
    private static final int CROP = 2;
    private File file;

    private CustomPopUpWindow popupWindow;
    private DrawerLayout parentDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        setUpView();
        setUpListener();

    }

    private void initData(){
        file = new File(Environment.getExternalStorageDirectory(),"temp.jpg");
        if(file.exists()){
            Log.d("MainActivity",file.exists()+"-->");
            file.delete();
        }
    }

    public void setUpView() {
        circleView = (CircleView) findViewById(R.id.circle_view);
        circleView.setImageResource(R.drawable.dolat);
        childDrawer = (RelativeLayout) findViewById(R.id.drawer);
        parentDrawer = (DrawerLayout) findViewById(R.id.parent_drawer);

        changeHeadImage = (CircleView) findViewById(R.id.take_photo);
        correctNikeNameTag = (TextView) findViewById(R.id.correct_tag);
        nikeName = (TextView) findViewById(R.id.nike_name);
        changeNikeName = (EditText) findViewById(R.id.change_nike_name);

//        hello.add("hi");
//        hello.add("haha");
//        hello.add("wawa");
//        hello.add("wawa");
//        hello.add("wawa");
//        hello.add("wawa");
//        hello.add("wawa");
//        hello.add("wawa");
//        hello.add("wawa");
//        hello.add("wawa");
//
//
//
//        refreshAdapter = new RefreshAdapter(this,hello);
//        refreshListView.setAdapter(refreshAdapter);
    }

    public void setUpListener(){
        circleView.setOnClickListener(this);
//        refreshListView.setOnRefreshListener(this);
//        refreshListView.setOnLoadMoreListener(this);
        changeHeadImage.setOnClickListener(this);
        correctNikeNameTag.setOnClickListener(this);
        parentDrawer.setOnClickListener(this);
        childDrawer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.circle_view:
                parentDrawer.openDrawer(childDrawer);
                break;
            case R.id.take_photo:
                chooseCameraOrAlbum();
                break;
            case R.id.correct_tag:
                changeNickName();
                break;

            case R.id.open_album:
                openAlbum();
                popupWindow.dismiss();
                break;

            case R.id.open_camera:
                openCamera();
                popupWindow.dismiss();
                break;
            case R.id.drawer:
                Log.d("MainActivity","--->"+popupWindow.isShowing());
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                break;

        }
    }

    private void chooseCameraOrAlbum(){

        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);

        Button openCamera = (Button) view.findViewById(R.id.open_camera);
        Button openAlbum = (Button) view.findViewById(R.id.open_album);

        openAlbum.setOnClickListener(this);
        openCamera.setOnClickListener(this);

        popupWindow = new CustomPopUpWindow(view);
        //杩欎釜涓嶈缃紝澶栭儴鐐瑰嚮鏁堟灉鏄病鏈夌殑銆傘�傘�備负浜嗙偣鍑绘晥鏋滅殑濂界湅锛屼篃鍙互鐩存帴浣跨敤閫忔槑鑳屾櫙
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_background));
        //set location
        popupWindow.showBelow(changeHeadImage);
    }

    /**
     * 璋冪敤绯荤粺鐩告満锛屽垏鎹㈠ご鍍�
     */
    private void openCamera(){
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));//鎷嶇殑鐓х墖瀛樺湪鍝噷
        startActivityForResult(intent,CAMERA);
    }


    /**
     * 璋冪敤鐩稿唽
     */
    private void openAlbum(){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,GALLERY);

    }

    /**
     * 淇敼鏄电О
     * 鏂囨湰鍩熼殣钘忥紝缂栬緫妗嗘樉绀猴紝淇敼鍙樻垚淇濆瓨
     * 鐐瑰嚮淇濆瓨涔嬪悗锛屼慨鏀瑰唴瀹瑰緱鍒颁繚瀛橈紝鏄电О涓枃鏈鏄剧ず锛岀紪杈戞闅愯棌銆�
     * 杩樿淇敼骞朵笖淇濆瓨鍒版暟鎹簱
     */
    private  void changeNickName(){
        //鐐瑰嚮淇敼
        if(correctNikeNameTag.getText().equals("淇敼")) {
            nikeName.setVisibility(View.GONE);
            changeNikeName.setVisibility(View.VISIBLE);
            correctNikeNameTag.setText("淇濆瓨");
        }else if(correctNikeNameTag.getText().equals("淇濆瓨")&&changeNikeName.getText().length()>0){
            changeNikeName.setVisibility(View.GONE);
            nikeName.setVisibility(View.VISIBLE);
            nikeName.setText(changeNikeName.getText());
            correctNikeNameTag.setText("淇敼");
        }else if(correctNikeNameTag.getText().equals("淇濆瓨")&&changeNikeName.getText().length()==0){
            Toast.makeText(this,"淇敼闀垮害涓嶈兘涓虹┖",Toast.LENGTH_SHORT).show();
            nikeName.setVisibility(View.VISIBLE);
            changeNikeName.setVisibility(View.GONE);
            correctNikeNameTag.setText("淇敼");
        }
    }



    /**
     * 鎷嶅畬鐓т箣鍚庡湪杩欓噷澶勭悊
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case CAMERA://浠庢枃浠朵腑鑾峰彇
                    if(file.exists())
                    cropPicture(Uri.fromFile(file));
                    break;

                case GALLERY:
                    if(data!=null){
                        cropPicture(data.getData());//杩斿洖uri
                    }else{
                        Log.d("MainActivity","intent 涓殑 data 涓虹┖");
                    }
                    break;

                case CROP:
                    setPictureToView(data);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setPictureToView(Intent data){

        Bundle extras  = data.getExtras();
        if(extras!=null){
            Bitmap photo = extras.getParcelable("data");
            changeHeadImage.setmBitmap(photo);
        }
    }

    private void cropPicture(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 鏄楂樼殑姣斾緥锛岃繖閲岃缃殑鏄鏂瑰舰锛堥暱瀹芥瘮涓�1:1锛�
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 鏄鍓浘鐗囧楂�
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP);
    }

//    /**
//     * 鎵嬪娍涓嬫媺鍒锋柊
//     */
//    @Override
//    public void onRefresh() {
//        Log.d("MainActivity","-->kao");
//        DataObtainAsynTask asynTask = new DataObtainAsynTask(refreshListView);
//        asynTask.execute();
//    }
//
//
//    /**
//     * 涓婃媺鍔犺浇鏇村
//     */
//    @Override
//    public void onLoadMore() {
//        LoadMoreAsync loadMoreAsync = new LoadMoreAsync(this);
//        loadMoreAsync.execute();
//    }
//
//    /**
//     * 寮傛浠诲姟瀹屾垚涔嬪悗鍥炶皟锛屾敼鍙樺埛鏂板垪琛ㄧ殑footer鐘舵��
//     */
//    @Override
//    public void onLoadComplete() {
//        /**
//         * 璋冪敤ListView鐨勪笢瑗�
//         */
//        Toast.makeText(this,"鍔犺浇瀹屾垚",Toast.LENGTH_SHORT).show();
//        refreshListView.changeFooterView(Constants.COMPLETED);
//    }
}
