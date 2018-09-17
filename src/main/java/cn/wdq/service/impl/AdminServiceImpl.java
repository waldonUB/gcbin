package cn.wdq.service.impl;

import cn.wdq.dao.AdminDAOImpl;
import cn.wdq.dao.ResDAOImpl;
import cn.wdq.dao.UIDImpl;
import cn.wdq.entities.ResourceModel;
import cn.wdq.service.AdminService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	UIDImpl uIDImpl;
	@Autowired
	ResDAOImpl resDAOImpl;
	@Autowired
	AdminDAOImpl adminDAOImpl;

	@Override
	public List queryUserType(String userType,int page) throws RuntimeException{
		return adminDAOImpl.queryUserType(userType,page);
	}

	@Override
	public int queryAllPage(){
		int count=adminDAOImpl.queryAllPage();
        int pages;
        if(count%10!=0){
            pages=(count/10)+1;
        }else{
            pages=count/10;
        }
		return pages;
	}

	@Override
	public List querySearch(String user_name) throws RuntimeException{
		return adminDAOImpl.querySearch(user_name);
	}

	@Override
	public int deleteCustomer(String user_name) throws RuntimeException{
		return adminDAOImpl.deleteCustomer(user_name);
	}

	@Override
	public int kicking(String user_name) throws RuntimeException {
		return adminDAOImpl.deleteCustomer(user_name);
	}

	@Override
	public boolean save_tree(ResourceModel resourceModel){
		int rownum=resDAOImpl.save_tree(resourceModel);
		if(rownum!=0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean edit_tree(ResourceModel resourceModel){
		int rownum=resDAOImpl.edit_tree(resourceModel);
		if(rownum!=0){
			return true;
		}else{
			return false;
		}

	}

	@Override
	public List queryByName(ResourceModel resourceModel){
		return resDAOImpl.queryByName(resourceModel);
	}

	@Override
    public List query_tree(){
        return resDAOImpl.query_tree();
    }

	@Override
    public boolean delete_tree(ResourceModel resourceModel){
        int rownum=resDAOImpl.delete_tree(resourceModel);
        if(rownum!=0){
            return true;
        }else{
            return false;
        }
    }
}
