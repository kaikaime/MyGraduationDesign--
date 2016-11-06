package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.activity.FriendMeagItemActivity;
import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.bean.MeagerMcountBean;
import com.example.lvkaixue.appmeager.protocol.childprotcol.RepostProtocel;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.ThreadUtils;
import com.example.lvkaixue.appmeager.utils.ToastUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import java.util.List;

/**
 * Created by lvkaixue on 2016/10/6.
 */
public class PersProRecyItemAdapter extends RecyclerView.Adapter<PersProRecyItemAdapter.MyViewHolder> {
    private List<IdolBean.Data.Info> list;
    private MyViewHolder myViewHolder;
    private Context context;
    private LayoutInflater inflater;
    private MeagerMcountBean mcountBean;


    public PersProRecyItemAdapter(List<IdolBean.Data.Info> list,Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public void notifyDataSetChanged(List<IdolBean.Data.Info> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHolder = inflater.inflate(R.layout.recy_item_view,null, false);
        myViewHolder = new MyViewHolder(viewHolder);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHodler, int position) {
         final IdolBean.Data.Info  info = list.get(position);
        final boolean[] flag = {false};
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
        viewHodler.tvContent.setText(info.tweet != null && info.tweet.size() > 0 ? info.tweet.get(0).text:"");
        //设置发布时间
        viewHodler.time.setText("发布时间: " + info.tweet != null && info.tweet.size() > 0 ? StringUtils.getDate(info.tweet.get(0).timestamp) : "");
        //设置内容发布地址
        viewHodler.loaction.setText("来自： " + info.tweet != null && info.tweet.size() > 0 ? info.tweet.get(0).from:"");


        //如果有图片设置图片
        viewHodler.zai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHodler.zai.setSelected(flag[0] = !flag[0]);
                if (flag[0]) {
                    ToastUtils.showToast("秒赞");
                } else {
                    ToastUtils.showToast("取消赞");
                }
            }
        });
        //请求每一条条目的转播次数和评论人数
        ThreadUtils.mThreadUI(new Runnable() {
            @Override
            public void run() {
                String rootId = info.tweet.get(0).id;
                String pageTime = "0";
                String pageFlag = "0";
                String tweeId = "0";
                mcountBean = new RepostProtocel(rootId, pageTime, pageFlag, tweeId).GetPost();
                if (mcountBean != null && mcountBean.data != null) {
                    HandlerUtils.mHandlerRefrenshUI(new Runnable() {
                        @Override
                        public void run() {
                            int count = mcountBean.data.info.size();
                            int mcount = mcountBean.data.info.size();
                            viewHodler.zuanfa.setText(count + "" != null ? count + "" : "转发");
                            viewHodler.pinglun.setText(mcount + "" != null ? mcount + "" : "评论");
                        }
                    });
                }
            }
        });

        //整个条目点击,跳转到条目评价页面
        viewHodler.viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FriendMeagItemActivity.class);
                intent.putExtra("info", info);
                intent.putExtra("mcountBean", mcountBean);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder {
        View viewItem;
        CircleTextImageView cilv;
        TextView tvName;
        ImageView birPic;
        TextView birName;
        TextView time;
        TextView birs;
        ImageView tvPic;
        TextView tvContent;
        FrameLayout fl;
        TextView loaction;
        TextView zuanfa;
        TextView pinglun;
        TextView zai;

        public MyViewHolder(View convertView) {
            super(convertView);
            viewItem = convertView;
            cilv = (CircleTextImageView) convertView.findViewById(R.id.user_photo_item);
            tvName = (TextView) convertView.findViewById(R.id.item_tv_name);
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
