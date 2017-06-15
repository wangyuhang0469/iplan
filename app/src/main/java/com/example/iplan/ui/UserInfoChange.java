package com.example.iplan.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iplan.R;
import com.example.iplan.model.UserModel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserInfoChange extends AppCompatActivity {


    @Bind(R.id.iv_avator)
    ImageView iv_avator;
    @Bind(R.id.tv_name1)
    TextView tv_name1;


//    @Bind(R.id.btn_chat)
//    Button btn_chat;



    private static ProgressDialog pd;
    private android.support.v7.app.AlertDialog photoDialog;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private File file;
    private Uri uri;

    /**
     * 去上传文件
     */
    protected static final int TO_UPLOAD_FILE = 4;

    /**
     * 上传文件响应
     */
    protected static final int UPLOAD_FILE_DONE = 5;  //

    private Uri origUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_change);
        ButterKnife.bind(this);


        String username = UserModel.getInstance().getCurrentUser().getUsername();
        tv_name1.setText(TextUtils.isEmpty(username) ? "" : username);

        iv_avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }


    private void showDialog() {
        photoDialog = new android.support.v7.app.AlertDialog.Builder(this).create();
        photoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        photoDialog.show();
        Window window = photoDialog.getWindow();
        window.setContentView(R.layout.dialog_photo); // 修改整个dialog窗口的显示
        window.setGravity(Gravity.BOTTOM);

        WindowManager.LayoutParams lp = photoDialog.getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = dm.widthPixels;
        photoDialog.getWindow().setAttributes(lp); // 设置宽度

        photoDialog.findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCamera();
            }
        });
        photoDialog.findViewById(R.id.btn_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPicture();
            }
        });
        photoDialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoDialog.dismiss();
            }
        });
    }

    private void toPicture() {

        Intent openAlbumIntent = new Intent(Intent.ACTION_PICK, null);
        openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);

    }

    private void toCamera() {

        Intent openAlbumIntent = new Intent();
        openAlbumIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        file = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        openAlbumIntent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(openAlbumIntent, TAKE_PICTURE);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    Bitmap bp = getCameraBitmap();
                    try {
                        saveFile(bp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    iv_avator.setImageBitmap(bp);
                    break;
                case CHOOSE_PICTURE:
                    Bitmap bit = getPhotoBitmap(data);
                    try {
                        saveFile(bit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    iv_avator.setImageBitmap(bit);

                    break;
            }
        }
    }

    private Bitmap getPhotoBitmap(Intent data) {
        Bitmap bitmap=null;
        Bitmap bit=null;
        try {
            uri=data.getData();
            bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            bitmap= ThumbnailUtils.extractThumbnail(bitmap, 300, 300);
            bit=toRoundBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //return bit;
        return bitmap;
    }


    private Bitmap getCameraBitmap() {
        Bitmap bitmap=null;
        try {
            bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.fromFile(file)));
            bitmap= ThumbnailUtils.extractThumbnail(bitmap, 300, 300);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 将一个bitmap转化为圆形输出
     * @param bitmap
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap){
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        int r=0;
        if (width<height){
            r=width;
        }else {
            r=height;
        }
        Bitmap backgroundBitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(backgroundBitmap);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        RectF rectF=new RectF(0,0,r,r);
        canvas.drawRoundRect(rectF,r/2,r/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,null,rectF,paint);
        return backgroundBitmap;
    }
    /**
     * 保存文件
     * @param bm
     * @throws IOException
     */
    public File saveFile(Bitmap bm) throws IOException {
        String path = Environment.getExternalStorageDirectory().toString()+"/qinxinxin/";
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        File myIconFile= new File(path + "myicon.jpg");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myIconFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myIconFile;
    }



    /**
     * 从本地获取图片
     * @param pathString 文件路径
     * @return 图片
     */
    public Bitmap getDiskBitmap(String pathString)
    {
        Bitmap bitmap = null;
        try
        {
            File file = new File(pathString);
            if(file.exists())
            {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e)
        {
            // TODO: handle exception
        }
        return bitmap;
    }
}


