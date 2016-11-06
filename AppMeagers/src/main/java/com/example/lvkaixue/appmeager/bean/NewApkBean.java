package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;

/**
 * Created by lvkaixue on 2016/10/2.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class NewApkBean implements Serializable{
    public int versionCode;
    public String versionName;
    public String desci;
    public String apkUrl;
}
