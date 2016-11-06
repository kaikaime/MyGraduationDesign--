package com.example.lvkaixue.appmeager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.customview.SweepCustomView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestActivity extends Activity {
    private ListView listView;
    private List<String> list = new ArrayList<>();
    private Map<Integer,View> mMap = new HashMap<>();
    private static final int DEFUALT = -1;
    private static final int DELETE = 0;
    private static final int MODIFY = 1;
    private int action = DEFUALT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.list);
        for(int i = 0;i<10;i++){
            list.add("项目"+i);
        }
        listView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter{
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
            final View view =  View.inflate(TestActivity.this,R.layout.sweep_item,null);
            TextView title = (TextView) view.findViewById(R.id.test_content_id);
            title.setText(list.get(position));

            final SweepCustomView customView = (SweepCustomView) view.findViewById(R.id.sweep_custom_view);

            //打开监听
            customView.addOpenListener(new SweepCustomView.OnOpenListener() {
                @Override
                public void isOpen(boolean flag, SweepCustomView sweepCustomView) {
                        if(flag && mMap.get(position) == null){
                            mMap.put(position,view);
                        }
                }
            });

            //删除监听
            customView.addDeleteListener(new SweepCustomView.OnDeleteListener() {
                @Override
                public void isDelete(boolean flag,boolean isOpen, SweepCustomView sweepCustomView) {
                    action = DEFUALT;
                    sweepAction(flag,isOpen,sweepCustomView);
                }
            });

            //修改
            customView.addModifyListener(new SweepCustomView.OnModifyListener() {
                @Override
                public void isModify(boolean flag, boolean isOpen, SweepCustomView sweepCustomView) {
                    action = MODIFY;
                    sweepAction(flag,isOpen,sweepCustomView);
                }
            });
            return view;
        }
    }

    private void sweepAction(boolean flag,boolean isOpen,SweepCustomView sweepCustomView){
        if(flag&&isOpen){
            Iterator<Integer> iterator =  mMap.keySet().iterator();
            while (iterator.hasNext()){
                SweepCustomView sweep = (((SweepCustomView) mMap.get(iterator.next()).findViewById(R.id.sweep_custom_view)));
                if(sweep != sweepCustomView){
                    sweep.close();
                }
            }
            sweepCustomView.close();
            sweepCustomView.addOpenListener(new SweepCustomView.OnOpenListener() {
                @Override
                public void isOpen(boolean flag, SweepCustomView sweepCustomView) {
                    if(!flag){
                        if(action == MODIFY){
                            action = DEFUALT;
                            System.out.println("修改成功");
                        }else if(action == DEFUALT){
                            action = DEFUALT;
                            System.out.println("删除成功");
                        }
                    }
                }
            });
        }
    }
}
