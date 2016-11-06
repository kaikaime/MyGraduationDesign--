package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.AboutPerBean;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.JsonResponseParser;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.ToastUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by lvkaixue on 2016/8/30.
 */
public class AboutPerProtocol{
    private final static String NAMEKEY = "name";
    private final static String FOPENIDSKEY ="fopenids";
    private  static String urlSucess="https://graph.qq.com/relation/add_idol";
    private  static String urlError="https://graph.qq.com/relation/del_idol";
    private AboutPerLinstener aboutPerLinstener;
    public AboutPerProtocol(AboutPerLinstener aboutPerLinstener){
        this.aboutPerLinstener = aboutPerLinstener;
    }
    public  void requestNetWorkAbout(String name,String fopenids){
        requestNetWork(name,fopenids,urlSucess);
    }
    public  void requestNetWorkCancel(String name,String fopenids) {
        requestNetWork(name,fopenids,urlError);
    }

    public  void requestNetWork(String name, String fopenids, final String url) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter(NAMEKEY, name);
        requestParams.addBodyParameter(FOPENIDSKEY,fopenids);

       for(String key:StringUtils.getMap().keySet()){
           requestParams.addBodyParameter(key,StringUtils.getMap().get(key).toString());
       }

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String s) {
                AboutPerBean aboutPerBean = JsonResponseParser.returnGson(s,AboutPerBean.class);
                ToastUtils.showToast(aboutPerBean.msg);
                HandlerUtils.mHandlerRefrenshUI(new Runnable() {
                    @Override
                    public void run() {
                        AboutPerBean aboutPerBean = JsonResponseParser.returnGson(s,AboutPerBean.class);
                        ToastUtils.showToast(aboutPerBean.msg);
                        if(aboutPerLinstener != null){
                            if(url.equals(urlSucess)){
                                aboutPerLinstener.getNetWorkState(true);
                            }else if(url.equals(urlError)){
                                aboutPerLinstener.getNetWorkState(false);
                            }
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                if(aboutPerLinstener != null){
                    aboutPerLinstener.getNetWorkState(false);
                }
            }

            @Override
            public void onCancelled(CancelledException e) {
                if(aboutPerLinstener != null){
                    aboutPerLinstener.getNetWorkState(false);
                }
            }

            @Override
            public void onFinished() {
            }
        });
    }

    public interface AboutPerLinstener{
        void getNetWorkState(boolean flag);
    }
}
