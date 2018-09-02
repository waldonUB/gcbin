package cn.wdq.mapping;

/**
 * 监听、过滤、拦截
 * @author waldon
 * */
public interface CommonDAO {
    /**
     * 查询该cuserid在线的用户数
     * @param cuserid 用户主键
     * @return 在线的用户数
     * */
    int loginInterceptor(String cuserid);
}
