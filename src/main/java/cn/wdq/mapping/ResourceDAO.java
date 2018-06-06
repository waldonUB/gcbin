package cn.wdq.mapping;

import cn.wdq.entities.ResourceModel;

import java.util.List;

/**
 * 节点注册模块
 * @author waldon
 * */
public interface ResourceDAO {
    /**
     * 保存树
     * @param resourceModel include:1.pk_resource 节点主键 2.funcode 节点编码 3.funname 节点名称
     * @return 是否保存成功的状态 0:失败 1:成功
     * */
    int save_tree(ResourceModel resourceModel);
    /**
     * 编辑树
     * @param resourceModel include:1.pk_resource 节点主键 2.funcode 节点编码 3.funname 节点名称
     * @return 是否编辑成功的状态 0:失败 1:成功
     * */
    int edit_tree(ResourceModel resourceModel);
    /**
     * 查询树是否存在相同编码或者名称
     * @param resourceModel include:1.pk_resource 节点主键 2.funcode 节点编码 3.funname 节点名称
     * @return 有该名称或编码的节点信息
     * */
    List queryByName(ResourceModel resourceModel);
    /**
     * 查询所有节点
     * @return 所有节点的集合
     * */
    List query_tree();
    /**
     * 删除节点
     * @param resourceModel include:1.pk_resource 节点主键 2.funcode 节点编码 3.funname 节点名称
     * @return 是否删除成功的状态 0:失败 1:成功
     * */
    int delete_tree(ResourceModel resourceModel);
}
