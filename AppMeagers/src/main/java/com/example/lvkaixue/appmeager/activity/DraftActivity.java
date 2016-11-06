package com.example.lvkaixue.appmeager.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.WrListAdapter;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.SaveDataBean;
import com.example.lvkaixue.appmeager.database.DBDao;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_test)
public class DraftActivity extends BaseActivity {
    @ViewInject(R.id.list_view)
    private ListView mListView;

    @ViewInject(R.id.list_count)
    private TextView mTitle;
    private WrListAdapter adapter;


   /*@Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
       initView();
   }*/

    @Override
    public void initView() {
        x.view().inject(this);

        getDataResource();

        //退出当前页面
        ((ImageView) findViewById(R.id.goback_iv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getDataResource(){
        //获取数据库中的数据
        DBDao dbDao = new DBDao(this);
        List<SaveDataBean> bean = dbDao.query();
        if(bean != null){
            if(adapter == null){
                adapter = new WrListAdapter(this,bean);
                adapter.setTextView(mTitle);
                mListView.setAdapter(adapter);
            }else {
                adapter.setList(bean);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == 0){
            getDataResource();
        }
    }
}
