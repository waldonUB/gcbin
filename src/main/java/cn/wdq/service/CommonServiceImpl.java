package cn.wdq.service;

import cn.wdq.dao.CommonDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    CommonDAOImpl commonDAOImpl;
    @Override
    public int loginInterceptor(String cuserid) {
        return commonDAOImpl.loginInterceptor(cuserid);
    }
}
