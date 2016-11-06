package com.example.lvkaixue.appmeager.protocol;


import com.example.lvkaixue.appmeager.utils.ChildHandler;
import com.example.lvkaixue.appmeager.utils.XUtil;

import org.xutils.http.RequestParams;

import java.util.Map;

/**
 * Created by lvkaixue on 2016/7/3.
 */
public abstract class BaseProtocl<T> {
    private ChildHandler.StaticChildHandler handler;
    public <T> T GetPost(){
        return requestNetWork();
    }
    public <T> T GetPost(ChildHandler.StaticChildHandler handler){
        this.handler = handler;
        return requestNetWork();
    }

    private <T> T requestNetWork(){
        try {
            Proto proto = initProtoData();
            XUtil.MethodName methodName = returnMethodName();
            //get方法
            if(methodName.getMethod().equals(XUtil.GET)){
                 XUtil.Get(proto.mUrl, proto.mMap);
            }

            //post方法
            else if(methodName.getMethod().equals(XUtil.POST)) {
                if (handler!=null) {
                    XUtil.post(proto.mUrl, proto.mMap,handler);
                }else {
                    XUtil.post(proto.mUrl, proto.mMap,null);

                }
            }

            else if(methodName.getMethod().equals(XUtil.GETSYSC)){
                return (T) XUtil.getSysc(proto.mUrl,proto.mMap,proto.mClass);
            }
            else if(methodName.getMethod().equals(XUtil.POSTSYSC)){
               return (T) XUtil.postSysc(proto.mUrl,proto.mMap,proto.mClass);
            }
        }catch (Exception e){
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }



    protected abstract Proto initProtoData();
    protected abstract XUtil.MethodName returnMethodName();
    public class Proto{
        public  Class<T> mClass;
        public Map<String ,String> mMap;
        public String mUrl;
    }
}
