package cn.wdq.listener;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessionListener implements HttpSessionListener {
    String jdbc="com.mysql.jdbc.Driver";
    String connection="jdbc:mysql://localhost:3306/db_stanley";
    private Logger logger=Logger.getLogger(SessionListener.class);
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
        //试试自动跳转到主页
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		String str=(String) session.getAttribute("sessionKey");
        JSONObject jsonObject=JSONObject.parseObject(str);
		String user_name=jsonObject.getString("user_name");
        try {
            String sb=" delete from login_info where user_name='"+user_name+"' ";
            Connection con=null;
            PreparedStatement pst=null;
            try {
                Class.forName(jdbc);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                con = DriverManager.getConnection(connection,"root","123456");
                pst=con.prepareStatement(sb);
                pst.executeUpdate(sb);
                System.out.println("强制登陆成功");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                pst.close();
                con.close();
            }
            logger.info(user_name+"在session失效时已经注销");
        } catch (SQLException e) {
            logger.error("session失效时注销用户失败"+e);
            e.printStackTrace();
        }
        //HttpServletResponse response = ServletActionContext.getResponse();
        //HttpServletResponse httpServletResponse= null;//有错,用spring @auto注入，request(失败，Session监听器不能自动注入)
    }

}
