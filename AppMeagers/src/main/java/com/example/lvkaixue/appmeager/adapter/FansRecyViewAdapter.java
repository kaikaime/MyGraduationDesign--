package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.bean.FansBean;
import com.example.lvkaixue.appmeager.protocol.childprotcol.AboutPerProtocol;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.example.lvkaixue.appmeager.utils.ToastUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvkaixue on 2016/8/28.
 */
public class  FansRecyViewAdapter extends RecyclerView.Adapter<FansRecyViewAdapter.MyViewHolder>  implements AboutPerProtocol.AboutPerLinstener{
        private List<FansBean.Data.Info> list;
        private MyViewHolder myViewHolder;
        private Context context;
        private List<FansBean.Data.Info> lists = new ArrayList<>();
        private FansBean.Data.Info info;
        private FansBean.Data.Info infos;
        private int index;

        public FansRecyViewAdapter(List<FansBean.Data.Info> list, Context context){
            this.list = list;
            this.context = context;
        }
        public void setNotifyData(List<FansBean.Data.Info> listt){
            this.list = listt;
            notifyDataSetChanged();
        }
        public List<FansBean.Data.Info> getList(){
            return list;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View viewHolder = View.inflate(context, R.layout.fans_recy_item,null);
            myViewHolder = new MyViewHolder(viewHolder);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder( final MyViewHolder viewHodler,  final int position) {
            info = list.get(position);
            if(info !=null){
                //设置图像
               if(info.head!=null && !"".equals(info.head)){
                   LoadDrawableUtils.newInstanceDrawable(info.head + "/100", viewHodler.circleTextImageView);
               }else {
                    viewHodler.circleTextImageView.setImageResource(R.mipmap.v5_bg_avatar);
                }
                viewHodler.textPerNick.setText(info.nick!=null? info.nick:"");//用户昵称

                //设置标签名长度过长
                if(info.tag!=null){
                    viewHodler.textBrife.setText(info.tag.get(0).name.length()>20? info.tag.get(0).name.substring(0,20)+" ·····": info.tag.get(0).name);//标签名
                }

                //如果当前用户是听众就显示关注
                viewHodler.textAbout.setVisibility(info.isidol ?View.VISIBLE:View.GONE);
                viewHodler.textNoAbout.setVisibility(info.isidol?View.GONE:View.VISIBLE);

                //点击改变是否关注
                viewHodler.rlAbout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if(viewHodler.textAbout.getVisibility() == View.VISIBLE){
                                viewHodler.textAbout.setVisibility(View.GONE);
                                viewHodler.textNoAbout.setVisibility(View.VISIBLE);
                            }else if(viewHodler.textNoAbout.getVisibility() == View.VISIBLE){
                                viewHodler.textAbout.setVisibility(View.VISIBLE);
                                viewHodler.textNoAbout.setVisibility(View.GONE);
                            }

                        infos = list.get(position);
                        index = position;
                        if(viewHodler.textAbout.getVisibility()==View.GONE){
                            //关注
                           new AboutPerProtocol(FansRecyViewAdapter.this).requestNetWorkAbout(info.name, info.openid);
                        } else if(viewHodler.textNoAbout.getVisibility() == View.GONE){
                            new AboutPerProtocol(FansRecyViewAdapter.this).requestNetWorkCancel(info.name, info.openid);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        @Override
        public void getNetWorkState(boolean flag) {
                //将以前的对象进行替换掉，防止下一次刷新的时候又被添加到原来的对象
                    ToastUtils.showToast(""+flag);
                    infos.isidol=flag;
                    list.set(index,infos);
        }

   public class MyViewHolder extends RecyclerView.ViewHolder{
        CircleTextImageView circleTextImageView;
        TextView textPerNick;
        TextView textAbout;
        TextView textNoAbout;
        RelativeLayout rlAbout;
        TextView textBrife;

        public MyViewHolder(View convertView ) {
            super(convertView);
            circleTextImageView = (CircleTextImageView) convertView.findViewById(R.id.recy_per_head);
            textPerNick = (TextView) convertView.findViewById(R.id.recy_per_nick);
            textAbout = (TextView) convertView.findViewById(R.id.recy_per_about);
            textBrife = (TextView) convertView.findViewById(R.id.recy_per_biref);
            textNoAbout = (TextView) convertView.findViewById(R.id.recy_per_noabout);
            rlAbout = (RelativeLayout) convertView.findViewById(R.id.recy_rl_about);
        }
    }
}