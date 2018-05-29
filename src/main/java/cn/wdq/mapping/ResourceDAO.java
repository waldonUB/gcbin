package cn.wdq.mapping;

import cn.wdq.entities.ResourceModel;

import java.util.List;

public interface ResourceDAO {
    public int save_tree(ResourceModel resourceModel);
    public int edit_tree(ResourceModel resourceModel);
    public List queryByName(ResourceModel resourceModel);
    public List query_tree();
    public int delete_tree(ResourceModel resourceModel);
}
