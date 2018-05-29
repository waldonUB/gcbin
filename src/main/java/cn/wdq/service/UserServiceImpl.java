package cn.wdq.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    /**
     * 关注对方
     * */
    public void followSb(String followName, String idolName) throws SQLException {
        Connection con=null;
        PreparedStatement pst=null;
        PreparedStatement pst2=null;
        String sb=" update sm_user set idol_name = '"+idolName+"' where user_name='"+followName+"' ";//关注此人
        String sb2=" update sm_user set follow_name = '"+followName+"' where user_name='"+idolName+"' ";//自动增加粉丝
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stanley","root","123456");
            pst=con.prepareStatement(sb);
            pst2=con.prepareStatement(sb2);
            pst.executeUpdate();
            pst2.executeUpdate();
        }catch (Exception e){

        }finally {
            pst.close();
            pst2.close();
            con.close();
        }
    }
}
