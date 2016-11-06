package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.PhotoBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvkaixue on 2016/9/3.
 */
public class SelfPhotoProtocol extends BaseProtocl<PhotoBean>{
    private final static String url="https://graph.qq.com/photo/list_album";
    @Override
    protected Proto initProtoData() {
        Proto proto = new Proto();
        Map<String,String> mMap = new HashMap<>();
        mMap.put("access_token","EB8ECF44D132DC2F22AF9DC2311F636E");
        mMap.put("oauth_consumer_key","100330589");
        mMap.put("openid","B20A160AE7E0742A165857381A29176C");
        mMap.put("format","json");
        proto.mMap = mMap;
        proto.mClass = PhotoBean.class;
        proto.mUrl=url;
        return proto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
