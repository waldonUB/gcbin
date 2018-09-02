package cn.wdq.common.user;

import cn.wdq.entities.UserInfo;

public class GetUserInfo {
    private static ThreadLocal<UserInfo> local = new ThreadLocal<>();

    public static void set (UserInfo userInfo){
        local.set(userInfo);
    }

    public static UserInfo get(){
        return local.get();
    }
}
