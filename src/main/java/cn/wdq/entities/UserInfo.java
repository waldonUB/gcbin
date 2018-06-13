package cn.wdq.entities;

import java.io.Serializable;

/**
 * 用户信息
 * @author waldon
 */
public class UserInfo implements Serializable {

    /**用户主键*/
    private String cuserid;
    /**用户名*/
    private String user_name;
    /**密码*/
    private String password;
    /**生效标记*/
    private int dr;
    /**用户类型*/
    private int user_type;
    /**是否锁定状态*/
    private int is_lock;
    /**是否在线*/
    private int is_online;
    /**用户头像base64*/
    private String head_img;
    /**最近登录时间*/
    private String last_time;
    /**所属地区*/
    private String district;

    public String getCuserid() {
        return cuserid;
    }

    public void setCuserid(String cuserid) {
        this.cuserid = cuserid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(int is_lock) {
        this.is_lock = is_lock;
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

}

