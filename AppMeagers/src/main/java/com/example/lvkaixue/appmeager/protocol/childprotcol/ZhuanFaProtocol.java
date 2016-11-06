package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.ZhuanFaBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.HashMap;

/**
 * Created by kaikaime on 2016/10/26.
 */
public class ZhuanFaProtocol extends BaseProtocl<ZhuanFaBean> {
    private String item = "";

    public ZhuanFaProtocol(String item) {
        this.item = item;
    }

    @Override
    protected Proto initProtoData() {
        Proto proto = new Proto();
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put(Constant.ZHUANFA_KEY, Constant.ZHUANFA_VAUE);
        mMap.put(Constant.ITEM_KEY, item);
        proto.mClass = ZhuanFaBean.class;
        proto.mUrl = Constant.getUrl;
        proto.mMap = mMap;
        return proto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
