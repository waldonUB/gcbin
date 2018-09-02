package cn.wdq.service;

import cn.wdq.dao.IndexDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    IndexDAOImpl indexDAOImpl;
    @Override
    public List query_last() {
        return indexDAOImpl.query_last();
    }

    @Override
    public List query_time() {
        return indexDAOImpl.query_time();
    }

    @Override
    public List query_map() {
        return indexDAOImpl.query_map();
    }
}
