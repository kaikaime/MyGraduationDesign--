package com.example.lvkaixue.appmeager.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.thinkcool.circletextimageview.CircleTextImageView;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 异步请求网络图片
 */
public class LoadDrawableUtils{
    private static final ImageOptions imageOptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(120),DensityUtil.dip2px(120))//图片大小
            .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
            .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .setUseMemCache(true)//设置缓存
            .build();
    public static ImageView newInstanceDrawable(String url, final CircleTextImageView ci){
        final ImageView[] imageView = {new ImageView(AppBaseApplication.getmContext())};
        if(url!=null||!"".equals(url)){
            x.image().bind(imageView[0], url, imageOptions, new Callback.CommonCallback<Drawable>() {
                @Override
                public void onSuccess(Drawable drawable) {
                    imageView[0] = ci;
                    imageView[0].setImageDrawable(drawable);
                    ci.setImageDrawable(drawable);
                }
                @Override
                public void onError(Throwable throwable, boolean b) {}
                @Override
                public void onCancelled(CancelledException e) {}
                @Override
                public void onFinished() {}
            });
        }
        return imageView[0];
    }
}
