package cn.wdq.entities;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class UserInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String cuserid;
    private String user_name;
    private String password;
    private int dr;
    private int user_type;
    private int is_lock;
    private int is_online;
    private String head_img;
    private String last_time;
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

    @Override
    public String toString() {
        JSONObject json=new JSONObject();
        return json.toJSONString("{"+
                "cuserid="+cuserid+
                ",user_name="+user_name+
                ",password="+password+
                ",dr="+dr+
                ",user_type="+user_type+
                ",is_lock="+is_lock+
                ",is_online="+is_online+
                "}");
    }
}

