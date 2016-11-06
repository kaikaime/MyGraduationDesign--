package com.example.lvkaixue.appmeager.single;

/**
 *、保存用户信息
 */
public class SingleUser {
    private SingleUser(){}
    private static SingleUser mSngleUser;
    private String oauthConsumerKey="100330589";
    private String accessToken="792C6E3706BF0409EAA7065029FD0797";
    private String openId="3CE402A692EECD314D0A09FF0D6AAE4E";
    private String authorityCost;
    private String expiresIn="7776000";
    private String loginCost="1551";
    private String pf ="desktop_m_qq-10000144-android-2002-";
    private String pyKey;
    private String queryAuthorityCost="0";

    public static SingleUser getSingleUser(){
       if(mSngleUser == null){
            synchronized (SingleUser.class){
                if(mSngleUser == null){
                    mSngleUser = new SingleUser();
                }
            }
        }
        return mSngleUser;
    }

    public String getOauthConsumerKey() {
        return oauthConsumerKey;
    }

    public void setOauthConsumerKey(String oauthConsumerKey) {
        this.oauthConsumerKey = oauthConsumerKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAuthorityCost() {
        return authorityCost;
    }

    public void setAuthorityCost(String authorityCost) {
        this.authorityCost = authorityCost;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getLoginCost() {
        return loginCost;
    }

    public void setLoginCost(String loginCost) {
        this.loginCost = loginCost;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPyKey() {
        return pyKey;
    }

    public void setPyKey(String pyKey) {
        this.pyKey = pyKey;
    }

    public String getQueryAuthorityCost() {
        return queryAuthorityCost;
    }

    public void setQueryAuthorityCost(String queryAuthorityCost) {
        this.queryAuthorityCost = queryAuthorityCost;
    }

}
