package cn.wdq.test;

import cn.wdq.dao.UIDImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TestMybatisSpringJava {
    @Test
    public void test01(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        UIDImpl uIDImpl=ctx.getBean("uIDImpl",UIDImpl.class);
        //访问数据库
//        List userInfos=uIDImpl.getAllUserInfo();
//        for (Object userInfo:userInfos){
//            System.out.println(userInfo);
//        }
//        assertNotNull(userInfos);
    }
}
