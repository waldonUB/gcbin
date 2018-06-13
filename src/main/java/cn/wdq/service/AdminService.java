package cn.wdq.service;

import cn.wdq.entities.ResourceModel;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户管理及导航栏节点注册
 * @author waldon
 * */
public interface AdminService {
	/**
	 * 获取不同用户类型的用户列表
	 * @param userType 用户状态
	 * @param page 页数
	 * @return 该状态下所有用户集合
	 * */
	List queryUserType(String userType, int page) throws RuntimeException, SQLException;
	/**
	 * 获取总页数
	 * @return 总页数
	 * */
	int queryAllPage() throws SQLException;
	/**
	 * 模糊查询
	 * @param user_name 用户名称
	 * @return 带该关键字的所有用户集合
	 * */
	List querySearch(String user_name) throws RuntimeException, SQLException;
	/**
	 * 删除用户
	 * @param user_name 用户名称
	 * @return 是否删除成功的状态
	 * */
	int deleteCustomer(String user_name) throws RuntimeException, SQLException;
	/**
	 * 踢人下线
	 * @param user_name 用户名称
	 * @return 是否删除成功的状态
	 * */
	int kicking(String user_name) throws RuntimeException;

	/**
	 * 树形菜单保存
	 * @param resourceModel include:1.pk_resource 节点主键 2.funcode 节点编码 3.funname 节点名称
	 * @return 是否保存成功的状态
	 * */
	boolean save_tree(ResourceModel resourceModel);
	/**
	 * 根据名称或者编码校验
	 * @param resourceModel include:1.pk_resource 节点主键 2.funcode 节点编码 3.funname 节点名称
	 * @return 该节点或编码的节点信息集合
	 * */
	List queryByName(ResourceModel resourceModel);
	/**
	 * 编辑态
	 * @param resourceModel include:1.pk_resource 节点主键 2.funcode 节点编码 3.funname 节点名称
	 * @return 是否编辑成功的状态
	 * */
	boolean edit_tree(ResourceModel resourceModel);
	/**
	 * 树形菜单查询
	 * @return 所有节点信息的集合
	 * */
	List<ResourceModel> query_tree();
	/**
     * 删除树节点
	 * @param resourceModel include:1.pk_resource 节点主键 2.funcode 节点编码 3.funname 节点名称
	 * @return 是否删除成功的状态
	 * */
	boolean delete_tree(ResourceModel resourceModel);
}
