package cn.wdq.entities;

import java.io.Serializable;

/**
 * 社区模块评论信息
 * @author waldon
 * */
public class CommentInfo implements Serializable{
    /**评论主键*/
    int pk_comment;
    /**话题主键*/
    int pk_blog;
    /**评论内容*/
    String cm_content;
    /**评论点赞*/
    String cm_praise;
    /**评论时间*/
    String cm_time;
    /**评论用户*/
    String user_name;
    /**评论用户头像*/
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
