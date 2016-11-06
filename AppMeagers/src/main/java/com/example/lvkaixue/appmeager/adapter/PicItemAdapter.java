package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.activity.LookPhotoItemActivity;
import com.example.lvkaixue.appmeager.bean.PhotoItemBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by lvkaixue on 2016/8/28.
 */
public class PicItemAdapter extends RecyclerView.Adapter<PicItemAdapter.MyViewHolder>{
        private List<PhotoItemBean.PItem> list;
        private Context context;
        public PicItemAdapter(List<PhotoItemBean.PItem> list, Context context){
            this.list = list;
            this.context = context;
        }
        public void setNotifyData(List<PhotoItemBean.PItem> list){
            this.list = list;
            notifyDataSetChanged();
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View viewItem = View.inflate(context,R.layout.photo_item_card,null);
            MyViewHolder myViewHolder = new MyViewHolder(viewItem);
           return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHodler, int position) {
            PhotoItemBean.PItem item = list.get(position);
            if(item.url!=null){
                System.out.println("url: "+item.url);
                x.image().bind(viewHodler.imageView,item.url,ImageOptions.DEFAULT);
                viewHodler.picNick.setText(item.picName);
                //条目点击事件
                setViewListener(viewHodler.mRl,item);
            }
        }

        /**条目点击事件
         * @param mRl
         * @param item**/
        private void setViewListener(RelativeLayout mRl, final PhotoItemBean.PItem item) {
            mRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LookPhotoItemActivity.class);
                    intent.putExtra("item",item);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView picNick;
        RelativeLayout mRl;
        public MyViewHolder(View convertView ) {
            super(convertView);
            imageView = (ImageView) convertView.findViewById(R.id.per_pic);
            picNick = (TextView) convertView.findViewById(R.id.pic_nick);
            mRl = (RelativeLayout) convertView.findViewById(R.id.rl_item);
        }
    }

}