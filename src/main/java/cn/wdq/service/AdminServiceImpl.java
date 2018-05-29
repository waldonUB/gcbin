package cn.wdq.service;

import cn.wdq.dao.ResDAOImpl;
import cn.wdq.dao.UIDImpl;
import cn.wdq.entities.ResourceModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
	String jdbc="com.mysql.jdbc.Driver";
	String connection="jdbc:mysql://localhost:3306/db_stanley";
	List list=new ArrayList();
	JSONArray jsonArray=new JSONArray();
	static UIDImpl uIDImpl;
	static ResDAOImpl resDAOImpl;
	static{
		System.out.println("进入了admin_static模块");
		ApplicationContext cxt=new ClassPathXmlApplicationContext("springmvc-servlet.xml");
		uIDImpl=cxt.getBean("uIDImpl",UIDImpl.class);
		resDAOImpl=cxt.getBean("resDAOImpl",ResDAOImpl.class);
	}
	public List queryUsertype(String userType,int page) throws RuntimeException, SQLException {
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		jsonArray.clear();
		StringBuffer sb=new StringBuffer("");
		if(userType.equals("online")){
			sb.append(" select * from login_info where is_online= 1 ");
		}else{
			sb.append(" select * from sm_user ");
			if(userType.equals("all")){
				sb.append(" limit "+page+",10 ");
			}else if(userType.equals("locked")){
				sb.append(" where is_lock = 1 ");
			}else if(userType.equals("admin")){
				sb.append(" where user_type=1 ");
			}
		}
		try {
			Class.forName(jdbc);
			con=DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sb.toString());
			res=pst.executeQuery();
			while(res.next()){
				JSONObject json=new JSONObject();
				json.put("cuserid", res.getString("cuserid"));
				json.put("user_name", res.getString("user_name"));
				json.put("password", res.getString("password"));
				json.put("dr", res.getInt("dr"));
				json.put("user_type", res.getInt("user_type"));
				json.put("is_lock", res.getInt("is_lock"));
				json.put("is_online", res.getInt("is_online"));
				json.put("last_time",res.getString("last_time"));
				jsonArray.add(json);
			}
		} catch (Exception e) {
			System.out.println("数据库获取数据异常");
		}finally {
				res.close();
				pst.close();
				con.close();
		}
		return jsonArray;
	}
	/**
	 * 查询总页数
	 * */
	public int queryAllpage() throws SQLException {
		int count=20;
		int pages=298;
		String sql=" select count(1) c from sm_user";
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		try {
			Class.forName(jdbc);
			con=DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sql.toString());
			res=pst.executeQuery();
			if(res.next()){
				count=res.getInt("c");
			}
			if(count%10!=0){
				pages=(count/10)+1;
			}else{
				pages=count/10;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			res.close();
			pst.close();
			con.close();
		}
		return pages;
	}
	/**
	 * 模糊查询
	 * */
	public List querySearch(String user_name) throws RuntimeException, SQLException {
		jsonArray.clear();
		String sql=" select * from sm_user where user_name like '%"+user_name+"%' ";
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		try {
			Class.forName(jdbc);
			con=DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sql.toString());
			res=pst.executeQuery();
			while(res.next()){
				JSONObject json=new JSONObject();
				json.put("cuserid", res.getString("cuserid"));
				json.put("user_name", res.getString("user_name"));
				json.put("password", res.getString("password"));
				json.put("dr", res.getInt("dr"));
				json.put("user_type", res.getInt("user_type"));
				json.put("is_lock", res.getInt("is_lock"));
				json.put("is_online", res.getInt("is_online"));
				jsonArray.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			res.close();
			pst.close();
			con.close();
		}
		return jsonArray;
	}
	public boolean deleteCustomer(String user_name) throws RuntimeException, SQLException {
		String sql=" delete from sm_user where user_name='"+user_name+"' ";
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		try {
			Class.forName(jdbc);
			con=DriverManager.getConnection(connection,"root","123456");
			pst=con.prepareStatement(sql);
			pst.executeUpdate();//返回影响的行数，可以定义为int
		} catch (Exception e) {
			throw new NullPointerException("删除的数据为空");
		}finally {
			pst.close();
			con.close();
		}
		return true;
	}
	/**
	 *踢人下线
	 * */
	@Override
	public boolean kicking(String user_name) throws RuntimeException {
		int rownum=uIDImpl.kicking(user_name);
		if(rownum!=0){
			return true;
		}else{
			return false;
		}

	}
	public boolean save_tree(ResourceModel resourceModel){
		int rownum=resDAOImpl.save_tree(resourceModel);
		if(rownum!=0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 编辑态
	 * */
	public boolean edit_tree(ResourceModel resourceModel){
		int rownum=resDAOImpl.edit_tree(resourceModel);
		if(rownum!=0){
			return true;
		}else{
			return false;
		}

	}
	/**
	 * 根据编码和名称来校验
	 * */
	public List queryByName(ResourceModel resourceModel){
		List list=resDAOImpl.queryByName(resourceModel);
		return list;
	}
    /**
     * 树形菜单查询
     * */
    public List query_tree(){
		List list=resDAOImpl.query_tree();
        return list;
    }
    /**
     * 删除树节点
     * */
    public boolean delete_tree(ResourceModel resourceModel){
        int rownum=resDAOImpl.delete_tree(resourceModel);
        if(rownum!=0){
            return true;
        }else{
            return false;
        }
    }
}
