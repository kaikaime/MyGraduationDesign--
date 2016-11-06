package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.PingLunBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.HashMap;

/**
 * Created by kaikaime on 2016/10/26.
 */
public class PingLunProtocel extends BaseProtocl<PingLunBean> {
    private String url = "";
    private String itemId = "";

    public PingLunProtocel(String item) {
        this.itemId = item;
    }

    @Override
    protected Proto initProtoData() {
        Proto proto = new Proto();
        HashMap<String, String> mHashMap = new HashMap<>();
        mHashMap.put("item", "pinglun");
        mHashMap.put("item_id", itemId);
        proto.mUrl = Constant.getUrl;
        proto.mClass = PingLunBean.class;
        proto.mMap = mHashMap;
        return proto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
