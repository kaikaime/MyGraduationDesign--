package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.AddContentBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.Map;

/**
 * Created by ll on 2016/8/12.
 */
public class AddContentProtocol extends BaseProtocl<AddContentBean>{
    private static final  String url = "https://graph.qq.com/t/add_t";
    private String arr[];
    public AddContentProtocol(String arr[]){this.arr = arr;}
    @Override
    protected Proto initProtoData() {
        Map<String,String> mMap = StringUtils.getMap();
        mMap.put(Constant.contentKey,arr[0]);
        mMap.put(Constant.syncFlagKey,arr[1]);
        Proto mProto = new Proto();
        mProto.mClass = AddContentBean.class;
        mProto.mUrl = url;
        mProto.mMap = mMap;
        return mProto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.POST;
    }


    /**
     *   Map<String,String> mMap = new HashMap<>();
     mMap.put("content",content);
     mMap.put("syncflag",flag);
     Proto mProto = new Proto();
     mProto.mClass = AddContentBean.class;
     mProto.mUrl = "https://graph.qq.com/t/add_t";
     mProto.mMap = mMap;
     return mProto;
     */
}
