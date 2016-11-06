package com.example.lvkaixue.appmeager.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.fragment.SettingNetWorkFragment;
import com.example.lvkaixue.appmeager.single.SingleUser;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.ToastUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 通过调用Tencent类的login函数发起登录/校验登录态。
 *
 * 该API具有两个作用：
 * （1）如果开发者没有调用mTencent实例的setOpenId、setAccessToken API，
 *      则该API执行正常的登录操作；
 * （2）如果开发者先调用mTencent实例的setOpenId、setAccessToken
 *      API，则该API执行校验登录态的操作。如果登录态有效，则返回成功给应用，
 *      如果登录态失效，则会自动进入登录流程，将最新的登录态数据返回给应用
 *
 * @author super LVKAIXUE
 *
 */
public class LoginActivity extends AppCompatActivity {
    private  String  url = "http://"+ StringUtils.getIP()+"/MeagerWebService/LoginServletIndex";
    private final static String scope = "all";
    private Tencent mTencent;
    private static final String mAppids = "1105581496";
    private static final String mAppKeys = "JiXvj97t6VaHZFVI";
    private static final String mAppid = "1105581256";
    private static final String mAppKey = "MjsKFie4OXCYRFob";
    public final static String activityName = LoginActivity.class.getSimpleName();
    private IUiListener loginListener;
    private SharedPreferences sp;
    private EditText mEditUserName;
    private EditText mEditUserPass;
    private SingleUser singleUser;
    private String wifiIp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wifiIp =  getIntent().getStringExtra("wifiIp");
        setContentView(R.layout.login_activtiy);
        mEditUserName = (EditText) findViewById(R.id.edit_username);
        mEditUserPass = (EditText) findViewById(R.id.edit_userpass);
        mTencent = Tencent.createInstance(mAppid, LoginActivity.this);
        (((TextView) findViewById(R.id.qq_logo))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HandlerUtils.checkNetWork()) {
                    oauthLogin();   //授权登录
                } else {
                    checkNetWork();
                }

            }
        });
        initData();
    }

    //登录
    private void oauthLogin() {
        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            mTencent.login(this, scope, loginListener);
        }
    }

    private void initData() {
        loginListener = new IUiListener() {
            @Override
            public void onError(UiError arg0) {
                System.out.println("UIErro: "+arg0.toString());
            }
            @Override
            public void onComplete(Object value) {
                mTencent.getAppId();
                System.out.println("value: "+value.toString());
                if (value == null) {
                    return;
                }
                analysisJson(value.toString());
            }
            @Override
            public void onCancel() {
            }
        };
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if(data !=null){
                System.out.println("不是一个空的对象");
            }else{
                System.out.println("是一个空的对象");
            }
            Tencent.handleResultData(data, loginListener);
        }
    }
    /**
     * 检查网络是否连接，如果没有连接，就提醒用户连接网络
     */
    private void checkNetWork() {
        SettingNetWorkFragment settingNetWorkFragment = new SettingNetWorkFragment();
        settingNetWorkFragment.show(getSupportFragmentManager(), null);
    }
    //点击使用用户登录
    public void loginClink(View view){
        final String userName = mEditUserName.getText().toString();
        final String userPass = mEditUserPass.getText().toString();
        if(userName!=null && !"".equals(userName) && userPass!=null&&!"".equals(userPass)){
            if(!userName.matches("[1-9_a-zA-Z]\\d{6,15}")){
                ToastUtils.showToast("账号输入错误!");
            }else if (!userPass.matches("\\w{6,}")) {
                ToastUtils.showToast("密码输入错误!");
            }else {
                if(!HandlerUtils.checkNetWork()){
                    checkNetWork();
                }else {
                    //登录用户
                    RequestParams p = new RequestParams(url);
                    p.addBodyParameter("user_account",userName);
                    p.addBodyParameter("user_pass",userPass);
                    x.http().post(p, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(final String s) {
                            HandlerUtils.mHandlerRunUI(new Runnable() {
                                @Override
                                public void run() {
                                    LoginActivity.this.analysisJson(s);
                                }
                            });
                        }
                        @Override
                        public void onError(Throwable throwable, boolean b) {
                            System.out.println("throwable: "+throwable.toString());
                        }
                        @Override
                        public void onCancelled(CancelledException e) {}
                        @Override
                        public void onFinished() {}});
                }
            }
        }else {
            ToastUtils.showToast("账号或者密码不能为空!");
        }
    }

    private void analysisJson(String value){
        System.out.println("analysisJson: "+value);
        try {
            JSONObject jo = new JSONObject(value);
            String ret = jo.getString("ret");
            if ("0".equals(ret)) {
                ToastUtils.showToast("登录成功!");
                String openID = jo.optString("openid");
                String accessToken = jo.optString("access_token");
                String expires = jo.optString("expires_in");
                String pf = jo.optString("pf");
                String queryAuthorityCost = jo.optString("query_authority_cost0");
                String authorityCost = jo.optString("authority_cost");
                String pfKey = jo.optString("pfkey");
                String loginCost = jo.optString("login_cost");
                singleUser = SingleUser.getSingleUser();
                singleUser.setAccessToken(accessToken);
                singleUser.setAuthorityCost(authorityCost);
                singleUser.setExpiresIn(expires);
                singleUser.setLoginCost(loginCost);
                singleUser.setPf(pf);
                singleUser.setQueryAuthorityCost(queryAuthorityCost);
                singleUser.setPyKey(pfKey);
                singleUser.setOpenId(openID);
                singleUser.setOauthConsumerKey(mTencent.getAppId());
                System.out.println("accessToken: "+accessToken);
                //保存登陆时间
                sp = getSharedPreferences("config", MODE_PRIVATE);
                sp.edit().putString("time",new SimpleDateFormat("yyyyMMdd").format(new Date())).commit();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }else if("7714".equals(ret)){
                ToastUtils.showToast(jo.optString("msg", ""));
            }
        } catch (Exception e) {
        }
    }
}