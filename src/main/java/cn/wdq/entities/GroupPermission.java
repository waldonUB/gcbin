package cn.wdq.entities;

public class GroupPermission {
    /**权限主键*/
    private int pk_permission;
    /**用户组主键*/
    private int pk_group;
    /**用户组编码*/
    private String groupCode;
    /**用户组名称*/
    private String groupName;
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

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPk_resource() {
        return pk_resource;
    }

    public void setPk_resource(String pk_resource) {
        this.pk_resource = pk_resource;
    }
}
