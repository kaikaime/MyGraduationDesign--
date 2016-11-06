package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.ZanBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.HashMap;

/**
 * Created by kaikaime on 2016/10/26.
 */
public class ZanProtocol extends BaseProtocl<ZanBean> {
    private String itemId;

    public ZanProtocol(String item) {
        this.itemId = item;
    }

    @Override
    protected Proto initProtoData() {
        Proto proto = new Proto();
        HashMap<String, String> mHashMap = new HashMap<>();
        mHashMap.put("item", "zan");
        mHashMap.put("item_id", itemId);
        proto.mClass = ZanBean.class;
        proto.mUrl = Constant.getUrl;
        proto.mMap = mHashMap;
        return proto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
