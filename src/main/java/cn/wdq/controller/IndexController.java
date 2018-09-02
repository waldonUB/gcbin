package cn.wdq.controller;

import cn.wdq.entities.ReturnModel;
import cn.wdq.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 图表地图界面数据的统计
 * @author waldon
 * */
@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 查询在线用户
     * @return 所有在线用户信息集合
     */
    @RequestMapping("/query_last")
    @ResponseBody
    public ReturnModel query_last() {
        ReturnModel model = new ReturnModel();
        List list = indexService.query_last();
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    /**
     * 查询上线时间柱状图
     * @return 所有用户本年内上线活跃度的统计集合
     */
    @RequestMapping("/login_bar")
    @ResponseBody
    public ReturnModel loginBar() {
        ReturnModel model = new ReturnModel();
        List list = indexService.query_time();
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    /**
     * 查询全国用户的地区分布
     * @return 地区名字及该地区用户数量的集合
     */
    @RequestMapping("/map_distribution")
    @ResponseBody
    public ReturnModel mapDistribution() {
        ReturnModel model = new ReturnModel();
        List list = indexService.query_map();
        model.setSuccess(true);
        model.setData(list);
        return model;
    }
}
