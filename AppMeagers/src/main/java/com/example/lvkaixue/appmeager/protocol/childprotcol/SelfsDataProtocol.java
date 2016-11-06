package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.MeaterBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.XUtil;

/**
 * Created by lvkaixue on 2016/8/28.
 */
public class SelfsDataProtocol extends BaseProtocl<MeaterBean> {
    private static final String url = "https://graph.qq.com/user/get_info";
    @Override
    protected Proto initProtoData() {
        Proto mProto = new Proto();
        mProto.mClass = MeaterBean.class;
        mProto.mMap=StringUtils.getMap();
        mProto.mUrl=url;
        return mProto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
