package com.example.lvkaixue.appmeager.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;

/**
 * Created by lvkaixue on 2016/10/18.
 */
public class ImgUtils {

    /**
     * 图片裁剪
     **/
    public static Bitmap getBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap == null) {
            return null;
        }
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //  Bitmap bitmap = BitmapFactory.decodeByteArray(pBtye, 0, pBtye.length, opts);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    /**
     * 图片转换为字节
     **/
    public static byte[] getImgByte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /*public static Bitmap getImageThumbnail(byte[] uri,int width,int height){//缩略图的路径，宽度，高度
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();//可以直接获取缩略力
        options.inJustDecodeBounds = false;
        int beWidth = options.outWidth/width;
        int beHeight = options.outHeight/height;
        int be = 1 ;
        if (beWidth<beHeight){
            be = beWidth;
        }else {
            be = beHeight;
        }
        if (be <=0){
            be=1;
        }
        options.inSampleSize = be;
        bitmap = BitmapFactory.decodeByteArray(uri, 0, uri.length, options);
       Bitmap b = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return b;
    }*/
}
