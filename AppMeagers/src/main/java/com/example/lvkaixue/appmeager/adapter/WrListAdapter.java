package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.activity.DraftActivity;
import com.example.lvkaixue.appmeager.activity.WriterInforActivity;
import com.example.lvkaixue.appmeager.bean.SaveDataBean;
import com.example.lvkaixue.appmeager.customview.SweepCustomView;
import com.example.lvkaixue.appmeager.database.DBDao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lvkaixue on 2016/9/17.
 */
public class WrListAdapter extends BaseAdapter {
    private Context context;
    private List<SaveDataBean> list;
    private Map<Integer,View> mMap = new HashMap<>();
    private static final int DEFUALT = -1;
    private static final int DELETE = 0;
    private static final int MODIFY = 1;
    private int action = DEFUALT;
    private TextView textView;
    public WrListAdapter(Context context, List<SaveDataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setTextView(TextView textView){
        this.textView = textView;
        //设置草稿数量
        textView.setText(list.size() > 0 ? "草稿箱(" + list.size() + ")" : "草稿箱");
    }

    public void setList(List<SaveDataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.wrlist_item_view,null);
            holder.con = (TextView) convertView.findViewById(R.id.list_content);
            holder.date = (TextView) convertView.findViewById(R.id.list_time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final SaveDataBean bean = list.get(position);
        holder.con.setText(bean.getmConData().length()>30?bean.getmConData().substring(0, 30)+"....":bean.getmConData());
        holder.date.setText("时间: "+bean.getmConDate());

        final SweepCustomView customView = (SweepCustomView) convertView.findViewById(R.id.sweep_custom_view);
        final View finalConvertView = convertView;

        //打开监听
        customView.addOpenListener(new SweepCustomView.OnOpenListener() {
            @Override
            public void isOpen(boolean flag, SweepCustomView sweepCustomView) {
                if(flag && mMap.get(position) == null){
                    mMap.put(position,finalConvertView);
                }
            }
        });

        //删除监听
        customView.addDeleteListener(new SweepCustomView.OnDeleteListener() {
            @Override
            public void isDelete(boolean flag,boolean isOpen, SweepCustomView sweepCustomView) {
                if(flag && isOpen){
                    Iterator<Integer> iterator =  mMap.keySet().iterator();
                    while (iterator.hasNext()){
                        SweepCustomView sweep = (((SweepCustomView) mMap.get(iterator.next()).findViewById(R.id.sweep_custom_view)));
                        if(sweep != sweepCustomView){
                            sweep.closeAll();
                        }
                    }
                        customView.closeAll();
                        Map<Integer,View> mMaps = new HashMap<>(mMap);
                        Iterator<Integer> it =  mMaps.keySet().iterator();
                        while (it.hasNext()){
                            mMap.remove(it.next());
                        }
                        if(new DBDao(context).delete(list.get(position))){
                            list.remove(position);
                            notifyDataSetChanged();
                        }
                }
            }
        });

        //修改
        customView.addModifyListener(new SweepCustomView.OnModifyListener() {
            @Override
            public void isModify(boolean flag, boolean isOpen, SweepCustomView sweepCustomView) {
                if(flag && isOpen){
                    Iterator<Integer> iterator =  mMap.keySet().iterator();
                    while (iterator.hasNext()){
                        SweepCustomView sweep = (((SweepCustomView) mMap.get(iterator.next()).findViewById(R.id.sweep_custom_view)));
                        if(sweep != sweepCustomView){
                            sweep.closeAll();
                        }
                    }
                    customView.closeAll();
                    Map<Integer,View> mMaps = new HashMap<>(mMap);
                    Iterator<Integer> it =  mMaps.keySet().iterator();
                    while (it.hasNext()){
                        mMap.remove(it.next());
                    }
                    Intent intent = new Intent(context,WriterInforActivity.class);
                    intent.putExtra("savebean", list.get(position));
                    ((DraftActivity) context).startActivityForResult(intent, 0);
                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView con;
        TextView date;
    }
}
