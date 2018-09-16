package cn.wdq.controller;

import cn.wdq.common.singleton.Singleton;
import cn.wdq.common.singleton.SingletonH;
import cn.wdq.entities.ReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping
@Controller
public class TestController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    static{
        System.out.println("TestController");
    }
    @RequestMapping("/singleTest")
    @ResponseBody
    public void singleTest(String str){
        List<String> list = SingletonH.getInstance();
        list.add(str);
        System.out.println(list.hashCode());
    }

}
