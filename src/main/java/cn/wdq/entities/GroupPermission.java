package cn.wdq.entities;

public class GroupPermission {
    /**权限主键*/
    private int pk_permission;
    /**用户组主键*/
    private int pk_group;
    /**用户组编码*/
    private String group_code;
    /**用户组名称*/
    private String group_name;
    /**权限树主键*/
    private String pk_resource;

    public int getPk_permission() {
        return pk_permission;
    }

    public void setPk_permission(int pk_permission) {
        this.pk_permission = pk_permission;
    }

    public int getPk_group() {
        return pk_group;
    }

    public void setPk_group(int pk_group) {
        this.pk_group = pk_group;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getPk_resource() {
        return pk_resource;
    }

    public void setPk_resource(String pk_resource) {
        this.pk_resource = pk_resource;
    }
}
