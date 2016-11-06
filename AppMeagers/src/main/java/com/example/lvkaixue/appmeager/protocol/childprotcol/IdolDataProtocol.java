package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.Map;

/**
 * Created by lvkaixue on 2016/9/2.
 */
public class IdolDataProtocol extends BaseProtocl<IdolBean> {
    private final static String url = "https://graph.qq.com/relation/get_idollist";
    private String reqnumValue;
    private String startindexValue;

    public IdolDataProtocol(String reqnum,String startindex){
        this.reqnumValue = reqnum;
        this.startindexValue = startindex;
        System.out.println("reqnum: "+reqnumValue+" startIndex: "+startindexValue);
    }
    @Override
    protected Proto initProtoData() {
        Proto proto = new Proto();
        Map<String, String> mMap =StringUtils.getMap();
        /**
         * reqnum=30&
         startindex=0
         */
        mMap.put(Constant.reqnumKey,reqnumValue);
        mMap.put(Constant.startindexKey,startindexValue);
        proto.mClass = IdolBean.class;
        proto.mMap = mMap;
        proto.mUrl = url;
        return proto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
