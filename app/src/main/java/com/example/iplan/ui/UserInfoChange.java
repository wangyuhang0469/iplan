package com.example.iplan.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.example.iplan.Config;
import com.example.iplan.R;
import com.example.iplan.bean.User;
import com.example.iplan.util.CameraPhotoUtil;
import com.example.iplan.util.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.iplan.util.BaseApplication.dismissProgressDialog;

public class UserInfoChange extends AppCompatActivity {

    private static final String TAG = "UserInfoChange";

    private final int RESULT_REQUEST_PHOTO = 1005;
    private final int RESULT_REQUEST_PHOTO_CROP = 1006;
    private final int RESULT_REQUEST_AUTH_HBUT = 1007;
    private final int RESULT_REQUEST_DOR_PART = 1008;
    private final int RESULT_REQUEST_DOR_NUM = 1009;
    private final int RESULT_REQUEST_PHONE = 1010;
    private final int RESULT_REQUEST_QQ = 1011;

    private Uri fileUri;
    private Uri fileCropUri;

    @Bind(R.id.iv_avator)
    ImageView iv_avator;

    private User curUser;

    private AQuery aq;

    @SuppressWarnings("unused")
    private Bundle bundle;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageType.MINE_INFO_FINISH_FIND_USER:
                    initView();
                    break;
                default:
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_change);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();
    }

    private void initView() {

//        aq = ShopApplication.getAQuery();
        aq = new AQuery(this);

        curUser = BmobUser.getCurrentUser(this, User.class);
        if (curUser == null)
            return;

        if (null != curUser.PicUser())
            aq.id(R.id.iv_avatar).image(curUser.PicUser().getFileUrl(this));
        else
            aq.id(R.id.iv_avatar).image(R.mipmap.head);

    }

    /**
     * 设置头像
     */
    private void setIcon() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("更换头像")
                .setItems(R.array.camera_gallery, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            camera();
                        } else {
                            photo();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialogTitleLineColor(dialog);
    }

    private void dialogTitleLineColor(AlertDialog dialog) {

    }


    private void refreshUserIcon() {
        aq.id(R.id.iv_avatar).image(curUser.PicUser().getUrl());
    }


    /**
     * 打开相机
     */
    private void camera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = CameraPhotoUtil.getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, RESULT_REQUEST_PHOTO);
    }

    /**
     * 打开相册
     */
    private void photo() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_REQUEST_PHOTO);
    }


    /**
     * 打开图片裁剪
     *
     * @param uri
     * @param outputUri
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    private void cropImageUri(Uri uri, Uri outputUri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }

    /**
     * 保存用户个人信息
     */
    private void saveUserInfo() {
        if (curUser == null) {
            ToastUtils.showToast("请先登录");
            Intent toLogin = new Intent(UserInfoChange.this, LoginActivity.class);
            startActivity(toLogin);
            finish();
        } else {
            Log.i("当前用户的ID: ", curUser.getObjectId());
            curUser.update(this, curUser.getObjectId(), new UpdateListener() {

                @Override
                public void onSuccess() {
                    Intent back = new Intent(UserInfoChange.this, UserInfoActivity.class);
                    setResult(RESULT_OK, back);  //返回成功码
                    finish();
                    ToastUtils.showToast("资料修改成功");
                }

                @Override
                public void onFailure(int arg0, String arg1) {
                    ToastUtils.showToast("资料修改失败: " + arg0 + "  " + arg1);
                }
            });
        }

    }


    /**
     * 上传用户头像
     *
     * @param filePath
     */
    private void uploadFile(String filePath) {

        BTPFileResponse response = BmobProFile.getInstance(this).upload(filePath, new UploadListener() {

            @Override
            public void onSuccess(String fileName, String url, BmobFile bmobFile) {
                dismissProgressDialog();
                String URL = BmobProFile.getInstance(UserInfoChange.this).signURL(fileName, url, Config.DEFAULT_APPKEY, 0, null);
                bmobFile.setUrl(URL);
                curUser.PicUser(BmobFile);
                refreshUserIcon();
                ToastUtils.showToast("文件已上传成功：" + fileName);
                Log.d(TAG, "filename: " + fileName + " url: " + url);
            }

            @Override
            public void onProgress(int ratio) {
                // TODO Auto-generated method stub
                Log.d(TAG, "MainActivity -onProgress :" + ratio);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                // TODO Auto-generated method stub
                dismissProgressDialog();
                ToastUtils.showToast("上传出错：" + errormsg);
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_REQUEST_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    fileUri = data.getData();
                }

                fileCropUri = CameraPhotoUtil.getOutputMediaFileUri();
                cropImageUri(fileUri, fileCropUri, 640, 640, RESULT_REQUEST_PHOTO_CROP);
            }

        } else if (requestCode == RESULT_REQUEST_PHOTO_CROP) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    String filePath = CameraPhotoUtil.getPath(this, fileCropUri);
                    uploadFile(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mine_info_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveUserInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("UserInfoChange"); //统计页面
        MobclickAgent.onResume(this);          //统计时长
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("UserInfoChange"); // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息
        MobclickAgent.onPause(this);
    }

}


