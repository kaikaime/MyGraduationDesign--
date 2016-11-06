package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.bean.ZhuanFaBean;
import com.thinkcool.circletextimageview.CircleTextImageView;

import java.util.ArrayList;

/**
 * Created by kaikaime on 2016/10/30.
 */
public class ZhuanFaAdapter extends ListBaseAdapter<ZhuanFaBean.ZhuanFaBeanData> {
    public ZhuanFaAdapter(ArrayList<ZhuanFaBean.ZhuanFaBeanData> mArray, Context context) {
        super(mArray, context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new MyViewHolder();
            convertView = View.inflate(context, R.layout.weibo_details_item,null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.ci = (CircleTextImageView) convertView.findViewById(R.id.user_photo_item);
            viewHolder.imgZan = (TextView) convertView.findViewById(R.id.zai_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        final ZhuanFaBean.ZhuanFaBeanData zhuanFaBeanData = mArray.get(position);
        if(zhuanFaBeanData!= null){
            viewHolder.title.setText(zhuanFaBeanData.mName);
            viewHolder.time.setText(zhuanFaBeanData.mTime);
            viewHolder.tv_content.setText(zhuanFaBeanData.mContent);
            if(zhuanFaBeanData.mFlag){
                viewHolder.imgZan.setSelected(zhuanFaBeanData.mFlag?true:false);
            }
        }
        final MyViewHolder finalViewHolder = viewHolder;
        viewHolder.imgZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuanFaBeanData.mFlag = !zhuanFaBeanData.mFlag;
                finalViewHolder.imgZan.setSelected(zhuanFaBeanData.mFlag?true:false);
                mArray.set(position,zhuanFaBeanData);
            }
        });
        return convertView;
    }
    class MyViewHolder{
        TextView title;
        TextView time;
        TextView tv_content;
        TextView imgZan;
        CircleTextImageView ci;
    }
}
