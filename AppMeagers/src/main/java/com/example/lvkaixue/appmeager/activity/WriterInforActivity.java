package com.example.lvkaixue.appmeager.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.AddContentBean;
import com.example.lvkaixue.appmeager.bean.SaveDataBean;
import com.example.lvkaixue.appmeager.database.DBDao;
import com.example.lvkaixue.appmeager.listeners.ListenerClass;
import com.example.lvkaixue.appmeager.protocol.childprotcol.AddContentProtocol;
import com.example.lvkaixue.appmeager.utils.ChildHandler;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.JsonResponseParser;
import com.example.lvkaixue.appmeager.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lvkaixue on 2016/8/10.
 */
@ContentView(R.layout.activity_real_main)
public class WriterInforActivity extends BaseActivity implements ListenerClass.RefrenshUIHandler,TextWatcher,ActionSheet.ActionSheetListener {
    public final static String activityName =WriterInforActivity.class.getSimpleName();

    @ViewInject(R.id.real_et_content)
    private EditText mEditContect;//编辑框中的内容

    @ViewInject(R.id.tv_tv_count)
    private TextView mEditTvCount;//编辑字数
    private ChildHandler.StaticChildHandler handler;
    private boolean isCancel = false;
    private SaveDataBean bean;

    @Override
    public void initView() {
       x.view().inject(this);
        handler = HandlerUtils.getUIHandler(this);
        //设置监听
        mEditContect.addTextChangedListener(this);

        //如果是从编辑界面跳转过来就先将编辑界面的数据显示到编辑框中
        if((bean =(SaveDataBean) getIntent().getSerializableExtra("savebean"))!=null){
            mEditContect.setText(bean.getmConData());
            System.out.println("bean"+bean.getmConData());
        }
    }


    //点击事件
    @Event(value = {R.id.real_tv_send,
            R.id.real_tv_cancel,
            R.id.real_pic,
            R.id.get_location_pic,
            R.id.get_camrea_pic},type = View.OnClickListener.class)
    private void onclick(View v){
        switch (v.getId()){
            case R.id.get_camrea_pic:
                //获取相机照片
            break;
            case R.id.get_location_pic:
                //获取本地照片
            break;
            case R.id.real_pic:
                //获取照片
                popuDialog();
            break;
            case R.id.real_tv_cancel:
                setResult(1);
                finish();
                break;
        //发送微博内容
            case R.id.real_tv_send:
                String mContent = mEditContect.getText().toString();
                if ("".equals(mContent)||null == mContent) {
                    ToastUtils.showToast("请输入微博内容!");
                }else {
                    if(HandlerUtils.checkNetWork()){
                        new AddContentProtocol(new String[]{mContent,"0"}).GetPost(handler);
                    }else {
                        ToastUtils.showToast("请检查网络!");
                    }
                }
                break;
        }
    }

    //获取发送微博内容之后服务返回的网络数据
    @Override
    public void mUIHandler(Message msg) {
        System.out.println("我的数据"+msg);
        if(msg.obj != null ||!"".equals(msg.obj)){
            AddContentBean addContentBean =
                    JsonResponseParser.returnGson(((String) msg.obj), AddContentBean.class);
            if(addContentBean != null){
                if ("0".equals(addContentBean.errcode)) {
                    //发送内容成功,结束当前页面
                    setResult(0);
                    finish();
                }else if("5".equals(addContentBean.errcode)){
                    ToastUtils.showToast(addContentBean.msg);
                }
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int fontCounts = Constant.fontCount - s.toString().length();
        mEditTvCount.setText(fontCounts+"");
    }
    @Override
    public void afterTextChanged(Editable s) {}

    //弹出获取图片中类型的对话框
    private void popuDialog(){
        View dialogView = View.inflate(this,R.layout.dialog_chooces_main,null);
        x.view().inject(this,dialogView);
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("请选择照片").setIcon(R.mipmap.icon)
                .setView(dialogView).create();
        alertDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            String mContent = mEditContect.getText().toString();
            if(!"".equals(mContent)&& mContent != null){
                if(!isCancel){
                    //如果用户直接按home键，当没有书写微博内容的时候就直接退出，如果有就告诉用户是否保存到草稿箱
                    ActionSheet.createBuilder(this, getSupportFragmentManager())
                            .setOtherButtonTitles("保存!","不需要保存!")
                            .setCancelButtonTitle("取消")
                            .setCancelableOnTouchOutside(true)
                            .setListener(this)
                            .show();
                    isCancel = true;
                    return true;
                }
            }else {
                setResult(1);
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancelBtn) {
        if(isCancelBtn){
            isCancel = false;
        }
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        //用户将书写的微博内容保存到数据库中
        if(index == 0){
            if(bean!=null){
                bean.setmConData(mEditContect.getText().toString());
                bean.setmConDate(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));
                new DBDao(this).update(bean);
                setResult(0);
                finish();
            }else {
                SaveDataBean bean = new SaveDataBean();
                bean.setmConData(mEditContect.getText().toString());
                bean.setmConDate(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));
                new DBDao(this).save(bean);
                setResult(1);
                finish();
            }
        }else if (index == 1) {
            setResult(1);
            finish();
        }
    }
}
