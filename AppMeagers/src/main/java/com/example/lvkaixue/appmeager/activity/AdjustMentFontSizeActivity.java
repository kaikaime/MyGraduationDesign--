package com.example.lvkaixue.appmeager.activity;

import android.view.View;
import android.widget.ImageView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.StringUtils;

public class AdjustMentFontSizeActivity extends BaseActivity {
    private ImageView maxImg;
    private ImageView manImg;
    private ImageView defImg;
    private ImageView goBackImg;

     @Override
    public void initView() {
        setContentView(R.layout.activity_adjust_ment_font_size);
        goBackImg = (ImageView) findViewById(R.id.goback_iv);
        maxImg = (ImageView) findViewById(R.id.iv_font_max);
        manImg = (ImageView) findViewById(R.id.iv_font_man);
        defImg = (ImageView) findViewById(R.id.iv_font_default);
        defImg.setVisibility(View.VISIBLE);
        setInitFontSize(StringUtils.getFontSize());//设置字体大小
        goBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setInitFontSize(int fontSizeIndex) {
        if(fontSizeIndex == Constant.defualtFontSize){
            maxImg.setVisibility(View.INVISIBLE);
            manImg.setVisibility(View.INVISIBLE);
            defImg.setVisibility(View.VISIBLE);
        }else if(fontSizeIndex == Constant.manFontSize){
            maxImg.setVisibility(View.INVISIBLE);
            manImg.setVisibility(View.VISIBLE);
            defImg.setVisibility(View.INVISIBLE);
        }else if(fontSizeIndex == Constant.maxFontSize){
            maxImg.setVisibility(View.VISIBLE);
            manImg.setVisibility(View.INVISIBLE);
            defImg.setVisibility(View.INVISIBLE);
        }
    }

    //设置默认的图片
    public void onImgIsShow(View view){
        switch (view.getId()){
            case R.id.rl_ftsi_max:
                StringUtils.setFontSize(Constant.maxFontSize);
                setInitFontSize(Constant.maxFontSize);
            break;
            case R.id.rl_ftsi_man:
                StringUtils.setFontSize(Constant.manFontSize);
                setInitFontSize(Constant.manFontSize);
                break;
            case R.id.rl_ftsi_default:
                StringUtils.setFontSize(Constant.defualtFontSize);
                setInitFontSize(Constant.defualtFontSize);
                break;
            default:
                break;
        }
    }


}
