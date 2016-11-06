package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.PhotoItemBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvkaixue on 2016/9/11.
 */
public class PhotoItemProtocol extends BaseProtocl<PhotoItemBean> {
    private static final String url = "http://"+ StringUtils.getIP()+"/MeagerWebService/IndexServletIndex";
    private static final String key = "req_p";
    private String req;

    public PhotoItemProtocol(String req) {
        this.req = req;
    }

    @Override
    protected Proto initProtoData() {
        Proto proto = new Proto();
        Map<String, String> mMap = new HashMap<>();
        mMap.put(key, req);
        proto.mUrl = url;
        proto.mMap = mMap;
        proto.mClass = PhotoItemBean.class;
        return proto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
