package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.bean.MeaterBean;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lvkaixue on 2016/8/9.
 */
public class MyDataAdapter extends BaseAdapter{
    private Context context = HandlerUtils.getContext();
    private List<MeaterBean> meaterBeanList;

    public MyDataAdapter(List<MeaterBean> meaterBeanList) {
        this.meaterBeanList = meaterBeanList;
    }

    public void setRefrenshUI(List<MeaterBean> meaterBeanList){
        this.meaterBeanList = meaterBeanList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return meaterBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return meaterBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = View.inflate(context, R.layout.recy_item_view,null);
            viewHodler.cilv = (CircleTextImageView) convertView.findViewById(R.id.user_photo_item);
            viewHodler.tvName  = (TextView) convertView.findViewById(R.id.item_tv_name);
            viewHodler.birPic = (ImageView) convertView.findViewById(R.id.item_bir_iv);
            viewHodler.birName = (TextView) convertView.findViewById(R.id.item_tv_bir);
            viewHodler.time = (TextView) convertView.findViewById(R.id.item_tv_time);
            viewHodler.birs = (TextView) convertView.findViewById(R.id.tv_bir);
            viewHodler.tvPic = (ImageView) convertView.findViewById(R.id.tv_pic);
            viewHodler.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            viewHodler.fl = (FrameLayout) convertView.findViewById(R.id.add_fra_resouce);
            viewHodler.loaction = (TextView) convertView.findViewById(R.id.loaction);
            viewHodler.zuanfa = (TextView) convertView.findViewById(R.id.zhuanfa_tv);
            viewHodler.pinglun = (TextView) convertView.findViewById(R.id.pinglun_tv);
            viewHodler.zai = (TextView) convertView.findViewById(R.id.zai_tv);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) convertView.getTag();
        }
        MeaterBean  meaterBean = meaterBeanList.get(position);
        //存放数据
        LoadDrawableUtils.newInstanceDrawable(meaterBean.data.head + "/30", viewHodler.cilv);
        viewHodler.tvName.setText(meaterBean.data.nick);
        viewHodler.birName.setText(meaterBean.data.introduction);
        viewHodler.tvContent.setText(meaterBean.data.tweetinfo.get(0).text);
        viewHodler.loaction.setText("来自: " + meaterBean.data.tweetinfo.get(0).location);
        viewHodler.time.setText(new SimpleDateFormat("dd 月-mm日-hh时").format(new Date()));
        //设置等级数
        viewHodler.birPic.setImageResource((meaterBean.data.level =="10")?R.mipmap.icon_wb_star:
        meaterBean.data.level=="6"?R.mipmap.icon_vip:R.mipmap.icon_gov_vip);
        viewHodler.zai.setText("");
        return convertView;
    }
    class  ViewHodler{
        CircleTextImageView cilv;
        TextView tvName;
        ImageView birPic;
        TextView birName;
        TextView time;
        TextView  birs;
        ImageView tvPic;
        TextView tvContent;
        FrameLayout fl;
        TextView loaction;
        TextView zuanfa;
        TextView pinglun;
        TextView zai;
    }
}
