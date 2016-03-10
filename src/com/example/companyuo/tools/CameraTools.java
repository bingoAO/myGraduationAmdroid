package com.example.companyuo.tools;//package com.example.sheshihao385.myapplication.com.example.sheshihao385.myapplication.tools;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.PopupWindow;
//import com.example.sheshihao385.myapplication.R;
//import java.io.File;
//
///**
// * Created by sheshihao385 on 16/1/15.
// */
//public class CameraTools{
//
//    private final static int CAMERA = 0;
//    private final static int GALLERY = 1;
//    private static final int CROP = 2;
//
//    private PopupWindow popupWindow;
//    private File file;
//
//    private Context mContext;
//
//
//    public CameraTools(Context context){
//        mContext = context;
//    }
//
//
//
//    public void chooseCameraOrAlbum(View v){
//
//        View view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow, null);
//
//        Button openCamera = (Button) view.findViewById(R.id.open_camera);
//        Button openAlbum = (Button) view.findViewById(R.id.open_album);
//
//        openAlbum.setOnClickListener(mContext);
//        openCamera.setOnClickListener(mContext);
//
//        popupWindow = new PopupWindow(view);
//        popupWindow.setFocusable(true);
//
//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        popupWindow.setWidth(view.getMeasuredWidth() + 40);
//        popupWindow.setHeight(view.getMeasuredHeight() + 80);
//
//        //聽璁剧疆澶栭儴鍙偣鍑�
//        popupWindow.setTouchable(true);
//        popupWindow.setOutsideTouchable(true);
//        //杩欎釜涓嶈缃紝澶栭儴鐐瑰嚮鏁堟灉鏄病鏈夌殑銆傘�傘�備负浜嗙偣鍑绘晥鏋滅殑濂界湅锛屼篃鍙互鐩存帴浣跨敤閫忔槑鑳屾櫙
//        popupWindow.setBackgroundDrawable();
//
//        //set location聽涓嬮潰杩欐牱瀛愮殑鏁堟灉鏄涓棿
////        View parent = this.getWindow().getDecorView();
////        popupWindow.showAtLocation(parent, Gravity.CENTER,parent.getMinimumWidth()/2,parent.getMinimumHeight()/2);
//
//        //set location
//        popupWindow.showAsDropDown(v);
//
//
//    }
//
//    /**
//     * 璋冪敤绯荤粺鐩告満锛屽垏鎹㈠ご鍍�
//     */
//    public void getOpenCameraIntent(){
//        Intent intent = new Intent();
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        file = FileTools.newFile();
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));//鎷嶇殑鐓х墖瀛樺湪鍝噷
//    }
//
//
//    /**
//     * 璋冪敤鐩稿唽
//     */
//    public void getOpenAlbumIntent(){
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//    }
//
//    /**
//     * 鎷嶅畬鐓т箣鍚庡湪杩欓噷澶勭悊
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(resultCode == RESULT_OK){
//            switch(requestCode){
//                case CAMERA://浠庢枃浠朵腑鑾峰彇
//                    if(file.exists())
//                        cropPicture(Uri.fromFile(file));
//                    break;
//
//                case GALLERY:
//                    if(data!=null){
//                        cropPicture(data.getData());//杩斿洖uri
//                    }else{
//                        Log.d("MainActivity", "intent 涓殑 data 涓虹┖");
//                    }
//                    break;
//
//                case CROP:
//                    setPictureToView(data);
//                    break;
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void setPictureToView(Intent data){
//
//        Bundle extras  = data.getExtras();
//        if(extras!=null){
//            Bitmap photo = extras.getParcelable("data");
//            changeHeadImage.setmBitmap(photo);
//        }
//    }
//
//    private void cropPicture(Uri uri){
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        // aspectX aspectY 鏄楂樼殑姣斾緥锛岃繖閲岃缃殑鏄鏂瑰舰锛堥暱瀹芥瘮涓�1:1锛�
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 鏄鍓浘鐗囧楂�
//        intent.putExtra("outputX", 200);
//        intent.putExtra("outputY", 200);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, CROP);
//    }
//
//}
