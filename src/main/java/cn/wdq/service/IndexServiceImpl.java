package cn.wdq.service;

import cn.wdq.dao.IndexDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    static IndexDAOImpl indexDAOImpl;
    static{
        System.out.println("进入了index_static模块");
        ApplicationContext cxt=new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        indexDAOImpl=cxt.getBean("indexDAOImpl",IndexDAOImpl.class);
    }
    @Override
    public List query_last() {
        List list=indexDAOImpl.query_last();
        return list;
    }

    @Override
    public List query_time() {
        List list=indexDAOImpl.query_time();
        return list;
    }

    @Override
    public List query_map() {
        List list=indexDAOImpl.query_map();
        return list;
    }
}
