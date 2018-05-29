package cn.wdq.service;

import cn.wdq.entities.ResourceModel;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
	/**
	 * 获取不同用户类型的用户列表
	 * */
	public List queryUsertype(String userType, int page) throws RuntimeException, SQLException;
	/**
	 * 获取总页数
	 * */
	public int queryAllpage() throws SQLException;
	/**s
	 * 模糊查询
	 * */
	public List querySearch(String user_name) throws RuntimeException, SQLException;
	/**
	 * 删除用户
	 * */
	public boolean deleteCustomer(String user_name) throws RuntimeException, SQLException;
	/**
	 * 踢人下线
	 * */
	public boolean kicking(String user_name) throws RuntimeException;

	/**
	 * 树形菜单保存
	 * */
	public boolean save_tree(ResourceModel resourceModel);
	/**
	 * 根据名称或者编码校验
	 * */
	public List queryByName(ResourceModel resourceModel);
	/**
	 * 编辑态
	 * */
	public boolean edit_tree(ResourceModel resourceModel);
	/**
	 * 树形菜单查询
	 * */
	public List<ResourceModel> query_tree();
	/**
     * 删除树节点
     * */
	public boolean delete_tree(ResourceModel resourceModel);
}
