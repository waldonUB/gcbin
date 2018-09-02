package cn.wdq.service;

/**
 * 过滤、监听、拦截操作
 * @author waldon
 * */
public interface CommonService {
    /**
     * 查询该用户id在线情况
     * @param cuserid 用户主键
     * @return 该ID在线人数 1 在线 0 下线
     * */
    int loginInterceptor(String cuserid);
}
