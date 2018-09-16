package cn.wdq.entities;

import java.io.Serializable;

/**
 * 功能注册模块
 * @author waldon
 * */

public class ResourceModel implements Serializable {
    /**节点主键*/
    private String pk_resource;
    /**节点编码*/
    private String funcode;
    /**节点名称*/
    private String funname;
    /**节点类型*/
    private String restype;
    /**节点图表*/
    private String resicon;
    /**父节点主键*/
    private String pk_parent;
    /**节点地址*/
    private String urls;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private String creationtime;
    /**修改人*/
    private String modifier;
    /**修改时间*/
    private String modifiedtime;

    public String getPk_resource() {
        return pk_resource;
    }

    public void setPk_resource(String pk_resource) {
        this.pk_resource = pk_resource;
    }

    public String getFuncode() {
        return funcode;
    }

    public void setFuncode(String funcode) {
        this.funcode = funcode;
    }

    public String getFunname() {
        return funname;
    }

    public void setFunname(String funname) {
        this.funname = funname;
    }

    public String getRestype() {
        return restype;
    }

    public void setRestype(String restype) {
        this.restype = restype;
    }

    public String getResicon() {
        return resicon;
    }

    public void setResicon(String resicon) {
        this.resicon = resicon;
    }

    public String getPk_parent() {
        return pk_parent;
    }

    public void setPk_parent(String pk_parent) {
        this.pk_parent = pk_parent;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(String modifiedtime) {
        this.modifiedtime = modifiedtime;
    }
}
