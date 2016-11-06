package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.MeagerMcountBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.Map;

/**
 * Created by lvkaixue on 2016/8/5.
 */
public class RepostProtocel extends BaseProtocl<MeagerMcountBean> {
    private final static String url ="https://graph.qq.com/t/get_repost_list";
    private String rootidValue;
    private String pageTimeValue;
    private String pageflagValue;
    private String twitteridVlaue;

    public RepostProtocel(String rootidValue,String pageTimeValue, String pageflagValue,String twitteridVlaue){
            this.rootidValue =rootidValue;
            this.pageflagValue = pageflagValue;
            this.pageTimeValue = pageTimeValue;
            this.twitteridVlaue=twitteridVlaue;
    }
    public RepostProtocel(){}
    @Override
    protected Proto initProtoData() {
        Map<String,String> mMap = StringUtils.getMap();
        mMap.put(Constant.flagKey, Constant.flagValue);
        mMap.put(Constant.rootidKey,rootidValue);
        mMap.put(Constant.pagetimeKey,rootidValue);
        mMap.put(Constant.pageflagKey,pageflagValue);
        mMap.put(Constant.reqnumKey,Constant.reqnumValue);
        mMap.put(Constant.twitteridKey,twitteridVlaue);
        Proto mProto = new Proto();
        mProto.mClass = MeagerMcountBean.class;
        mProto.mMap=mMap;
        mProto.mUrl=url;
        return mProto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
