package cn.wdq.entities;

public class GroupUser {
    private int pk_group;
    private String groupCode;
    private String groupName;

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
}
