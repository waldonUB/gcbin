package cn.wdq.entities;

import java.io.Serializable;

/**
 * 评论信息
 * */
public class CommentInfo implements Serializable{
    int pk_comment;
    int pk_blog;
    String cm_content;
    String cm_praise;
    String cm_time;
    String user_name;
    String head_img;

    public int getPk_comment() {
        return pk_comment;
    }

    public void setPk_comment(int pk_comment) {
        this.pk_comment = pk_comment;
    }

    public int getPk_blog() {
        return pk_blog;
    }

    public void setPk_blog(int pk_blog) {
        this.pk_blog = pk_blog;
    }

    public String getCm_content() {
        return cm_content;
    }

    public void setCm_content(String cm_content) {
        this.cm_content = cm_content;
    }

    public String getCm_praise() {
        return cm_praise;
    }

    public void setCm_praise(String cm_praise) {
        this.cm_praise = cm_praise;
    }

    public String getCm_time() {
        return cm_time;
    }

    public void setCm_time(String cm_time) {
        this.cm_time = cm_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }
}
