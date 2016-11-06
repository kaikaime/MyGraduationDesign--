package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.FansBean;
import com.example.lvkaixue.appmeager.protocol.BaseProtocl;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.XUtil;

import java.util.Map;

/**
 * Created by lvkaixue on 2016/8/30.
 */
public class FansDataProtocol extends BaseProtocl<FansBean>  {
    private final static String url="https://graph.qq.com/relation/get_fanslist";
    private final static String REQNUM ="reqnum";
    private final static String STARTINDEX ="startindex";
    private final static String MODE ="mode";
    private final static String MODEINDEX="1";
    private String startindex;//页数
    private String requm;//取值范围
    public FansDataProtocol(String startindex,String requm){
        this.startindex = startindex;
        this.requm  = requm;
    }
    @Override
    protected Proto initProtoData() {
        Proto proto = new Proto();
        proto.mClass = FansBean.class;
        Map<String,String> mMap = StringUtils.getMap();
        {
            mMap.put(REQNUM,requm);
            mMap.put(STARTINDEX,startindex);
            mMap.put(MODE,MODEINDEX);
        }

        proto.mMap = mMap;
        proto.mUrl=url;
        return proto;
    }

    @Override
    protected XUtil.MethodName returnMethodName() {
        return XUtil.MethodName.GETSYSC;
    }
}
