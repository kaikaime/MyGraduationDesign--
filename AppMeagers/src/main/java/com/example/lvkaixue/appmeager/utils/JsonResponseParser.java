package com.example.lvkaixue.appmeager.utils;

import com.google.gson.Gson;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

/**
 * Created by lvkaixue on 2016/7/4.
 */
public class JsonResponseParser implements ResponseParser {
    @Override
    public void checkResponse(UriRequest uriRequest) throws Throwable {}

    @Override
    public Object parse(Type type, Class<?> aClass, String s) throws Throwable {
        System.out.println("s: ========="+s);
        return new Gson().fromJson(s, aClass);
    }
    public static <T> T returnGson(String s,Class<T> aClass){
        return new Gson().fromJson(s,aClass);
    }
}
