package com.example.lvkaixue.appmeager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.bean.PhotoItemBean;
import com.example.lvkaixue.appmeager.utils.ImgUtils;

import org.xutils.x;

public class LookPhotoItemActivity extends AppCompatActivity {

    private ImageView ivPic;
    private RelativeLayout mRl;
    private boolean isClick = false;
    private PhotoItemBean.PItem item;
    private TextView picSize;
    private TextView picName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_photo_item);
        item = (PhotoItemBean.PItem) getIntent().getSerializableExtra("item");
        ivPic = (ImageView) findViewById(R.id.iv_pic);
        mRl = (RelativeLayout) findViewById(R.id.rl_show);
        picSize = (TextView) findViewById(R.id.tv_size);
        picName = (TextView) findViewById(R.id.tv_pn);
        mRl.setAlpha(0.4f);
        //显示图片
        if(item!=null){
            x.image().bind(ivPic,item.url);
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int ImgWidth = (int) (metrics.widthPixels*0.2+.5f);
            int ImgHeight= (int) (metrics.heightPixels*0.2+.5f);
            ImgUtils.getBitmap(ivPic.getDrawingCache(true), ImgWidth, ImgHeight);
            picSize.setText("尺寸:  "+ImgWidth+"*"+ImgHeight+"");
            picName.setText(item.picName != null ? "原图：" + item.picName : "");
        }
    }
}
