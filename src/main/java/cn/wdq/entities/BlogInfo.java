package cn.wdq.entities;

import java.io.Serializable;

/**
 * 社区话题模块实体类
 * @author waldon
 * */
public class BlogInfo implements Serializable {
    /** 话题主键 */
    private int pk_blog;
    /** 用户主键 */
    private String cuserid;
    /** 用户名 */
    private String user_name;
    /** 用户头像 */
    private String head_img;
    /** 最近登录时间 */
    private String last_time;
    /** 话题标题 */
    private String blog_title;
    /** 话题内容 */
    private String blog_content;
    /** 话题点赞 */
    private int blog_praise;
    /** 话题评论 */
    private String blog_comment;
    /** 话题图片 */
    private String blog_image;
    /** 话题分类 */
    private String blog_classify;


    public int getPk_blog() {
        return pk_blog;
    }

    public void setPk_blog(int pk_blog) {
        this.pk_blog = pk_blog;
    }

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

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public String getBlog_content() {
        return blog_content;
    }

    public void setBlog_content(String blog_content) {
        this.blog_content = blog_content;
    }

    public int getBlog_praise() {
        return blog_praise;
    }

    public void setBlog_praise(int blog_praise) {
        this.blog_praise = blog_praise;
    }

    public String getBlog_comment() {
        return blog_comment;
    }

    public void setBlog_comment(String blog_comment) {
        this.blog_comment = blog_comment;
    }

    public String getBlog_image() {
        return blog_image;
    }

    public void setBlog_image(String blog_image) {
        this.blog_image = blog_image;
    }

    public String getBlog_classify() {
        return blog_classify;
    }

    public void setBlog_classify(String blog_classify) {
        this.blog_classify = blog_classify;
    }
}
