package cn.wdq.controller;

import cn.wdq.entities.ReturnModel;
import cn.wdq.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    IndexService indexService;

    /**
     * 查询在线用户
     */
    @RequestMapping("/query_last")
    @ResponseBody
    public ReturnModel query_last() {
        ReturnModel model = new ReturnModel();
        List list = new ArrayList();
        list = indexService.query_last();
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    /**
     * 查询上线时间柱状图
     */
    @RequestMapping("/login_bar")
    @ResponseBody
    public ReturnModel loginBar() {
        ReturnModel model = new ReturnModel();
        List list = new ArrayList();
        list = indexService.query_time();
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    /**
     * 查询全国用户的地区分布
     */
    @RequestMapping("/map_distribution")
    @ResponseBody
    public ReturnModel mapDistribution() {
        ReturnModel model = new ReturnModel();
        List list = new ArrayList();
        list = indexService.query_map();
        model.setSuccess(true);
        model.setData(list);
        return model;
    }
}
