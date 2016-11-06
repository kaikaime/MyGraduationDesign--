package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.activity.MainActivity;
import com.example.lvkaixue.appmeager.activity.PhotoItemActivity;
import com.example.lvkaixue.appmeager.bean.PhotoBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by lvkaixue on 2016/9/3.
 */
public class PhotoViewAdapter {
    private static PhotoReclyViewAdapter photoReclyViewAdapter;
    public static PhotoReclyViewAdapter newInstanceRecyViewApdater(Context context,PhotoBean photoBean){
        if(photoReclyViewAdapter == null){
            photoReclyViewAdapter = new PhotoReclyViewAdapter(context,photoBean);
        }else{
            photoReclyViewAdapter.setNoti(photoBean);
        }
        return photoReclyViewAdapter;
    }

    public static class PhotoReclyViewAdapter extends RecyclerView.Adapter<MyViewHodler>{
        private Context context;
        private PhotoBean photoBean;
        public PhotoReclyViewAdapter(Context context,PhotoBean photoBean) {
            this.context = context;
            this.photoBean = photoBean;
        }

        public void setNoti(PhotoBean photoBean){
            this.photoBean = photoBean;
            notifyDataSetChanged();
        }
        @Override
        public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context,R.layout.photo_item,null);
            MyViewHodler myViewHodler = new MyViewHodler(view);
            return myViewHodler;
        }

        @Override
        public void onBindViewHolder(MyViewHodler holder, final int position) {
            x.image().bind(holder.imageView, photoBean.album.get(position).coverurl, ImageOptions.DEFAULT);
            holder.textView.setText(photoBean.album.get(position).name);

            //设置条目点击事件
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PhotoItemActivity.class);
                    intent.putExtra("album","pic_"+position);
                    ((MainActivity) context).startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return photoBean.album.size();
        }
    }
    static class MyViewHodler extends RecyclerView.ViewHolder{
        private  ImageView imageView;
        private TextView textView;
        private View itemView;
        public MyViewHodler(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.photo);
            textView = (TextView) itemView.findViewById(R.id.photo_name);
        }
        public View getItemView(){
            return itemView;
        }
    }
}
