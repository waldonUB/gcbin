package cn.wdq.common.listener;

import cn.wdq.dao.AdminDAOImpl;
import cn.wdq.dao.UIDImpl;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * Session监听器(好久之前的代码,先不动)
 * @author waldon
 * */

public class SessionListener implements HttpSessionListener {
    private Logger logger=Logger.getLogger(SessionListener.class);
    private static AdminDAOImpl adminDAOImpl;
    private static UIDImpl uIDImpl;
    static {
        System.out.println("进入sessionListener");
        ApplicationContext cxt=new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        adminDAOImpl=cxt.getBean("adminDAOImpl",AdminDAOImpl.class);
        uIDImpl=cxt.getBean("uIDImpl",UIDImpl.class);
        uIDImpl.clearAll();//重启服务器清除login_info
    }
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	    //SessionListener未注册@Autowire会加载不到类
		HttpSession session=se.getSession();
		String str=(String) session.getAttribute("sessionKey");
        JSONObject jsonObject=JSONObject.parseObject(str);
		String user_name=jsonObject.getString("user_name");
        int i=adminDAOImpl.kicking(user_name);//删除在该用户在login_info表的信息
        if(i>0){
            logger.info("SessionListener:用户 "+user_name+"的登录信息已清除");
        }else{
            logger.error("SessionListener:用户 "+user_name+"的登录信息清除失败");
        }
    }

}
