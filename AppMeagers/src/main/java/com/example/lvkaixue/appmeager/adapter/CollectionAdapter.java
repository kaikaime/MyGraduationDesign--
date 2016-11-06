package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import java.util.ArrayList;

/**
 * Created by lvkaixue on 2016/10/13.
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<IdolBean.Data.Info> infoArrayList;
    private ArrayList<IdolBean.Data.Info> notifyAdapterArrayData;

    public CollectionAdapter(Context context, ArrayList<IdolBean.Data.Info> mArrayList) {
        this.context = context;
        this.infoArrayList = mArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.recy_item_view,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHodler, int position) {
        final IdolBean.Data.Info  info = infoArrayList.get(position);
        //设置用户图像
        if(info.head!=null&&!"".equals(info.head)){
            LoadDrawableUtils.newInstanceDrawable(info.head + "/100", viewHodler.cilv);
        }else{
            viewHodler.cilv.setImageResource(R.mipmap.v5_bg_avatar);
        }
        //设置用户昵称
        viewHodler.tvName.setText(info.nick);
        //个人简介
        viewHodler.birName.setText(info.tag!=null&&info.tag.size()>0? info.tag.get(0).name:"");
        //微博内容
        viewHodler.tvContent.setTextSize(StringUtils.getFontSize());
        viewHodler.tvContent.setText(info.tweet != null && info.tweet.size() > 0 ? info.tweet.get(0).text : "");
        //设置发布时间
        viewHodler.time.setText("发布时间: " + info.tweet != null && info.tweet.size() > 0 ? StringUtils.getDate(info.tweet.get(0).timestamp) : "");
        //设置内容发布地址
        viewHodler.loaction.setText("来自： "+info.tweet!=null&&info.tweet.size()>0?info.tweet.get(0).from:"");
    }

    @Override
    public int getItemCount() {
        return infoArrayList.size();
    }

    public void setNotifyAdapterArrayData(ArrayList<IdolBean.Data.Info> notifyAdapterArrayData) {
        this.notifyAdapterArrayData = notifyAdapterArrayData;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        View viewItem;      CircleTextImageView cilv;   TextView tvName;
        ImageView birPic;   TextView birName;           TextView time;
        TextView  birs;     ImageView tvPic;            TextView tvContent;
        FrameLayout fl;     TextView loaction;          TextView zuanfa;
        TextView pinglun;   TextView zai;
        public MyViewHolder(View convertView ) {
            super(convertView);
            viewItem = convertView;
            cilv = (CircleTextImageView) convertView.findViewById(R.id.user_photo_item);
            tvName  = (TextView) convertView.findViewById(R.id.item_tv_name);
            birPic = (ImageView) convertView.findViewById(R.id.item_bir_iv);
            birName = (TextView) convertView.findViewById(R.id.item_tv_bir);
            time = (TextView) convertView.findViewById(R.id.item_tv_time);
            birs = (TextView) convertView.findViewById(R.id.tv_bir);
            tvPic = (ImageView) convertView.findViewById(R.id.tv_pic);
            tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            fl = (FrameLayout) convertView.findViewById(R.id.add_fra_resouce);
            loaction = (TextView) convertView.findViewById(R.id.loaction);
            zuanfa = (TextView) convertView.findViewById(R.id.zhuanfa_tv);
            pinglun = (TextView) convertView.findViewById(R.id.pinglun_tv);
            zai = (TextView) convertView.findViewById(R.id.zai_tv);
         }
    }
}

