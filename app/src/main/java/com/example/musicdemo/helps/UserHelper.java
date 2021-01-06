package com.example.musicdemo.helps;

/**
 * 用户自动登录
 * 1.用户登录
 *      1.当用户登录时，利用SharePreferences保存用户的用户标记
 *      2.利用全局单例类UserHelp保存登录用户信息
 *          1.用户登录之后，
 *          2.当用户重新打开应用程序，检测SharePreferences是否存在登录用户标记
 *          ，如果存在则为UserHelp赋值，并且进入主页，如果不存在了，则进入登录页面
 * 2.用户退出
 *      1.删除掉SharePreferences保存的用户标记，退出到登录页面
 */
public class UserHelper  {

    private static UserHelper instance;

    private UserHelper(){};

    public static UserHelper getInstance(){
        if (instance==null){
            synchronized (UserHelper.class){
                if (instance==null){
                    instance=new UserHelper();
                }
            }
        }
        return instance;
    }

    public static void setInstance(UserHelper instance) {
        UserHelper.instance = instance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

}






















