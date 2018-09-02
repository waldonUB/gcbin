package cn.wdq.entities;

import java.io.Serializable;

/**
 * 社区模块点赞信息
 * @author waldon
 * */
public class PraiseInfo implements Serializable{
    /**点赞主键*/
    private int pk_praise;
    /**话题主键*/
    private int pk_blog;
    /**点赞用户*/
    private String user_name;

    public int getPk_praise() {
        return pk_praise;
    }

    public void setPk_praise(int pk_praise) {
        this.pk_praise = pk_praise;
    }

    public int getPk_blog() {
        return pk_blog;
    }

    public void setPk_blog(int pk_blog) {
        this.pk_blog = pk_blog;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
