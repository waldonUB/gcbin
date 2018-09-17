package cn.wdq.controller;

import cn.wdq.entities.ReturnModel;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    private Logger logger = Logger.getLogger(PermissionController.class);

    /**
     * 查询用户可拥有的节点权限
     * @param json 1.group_code 用户所属的用户组编码
     * */
    @RequestMapping("/queryPermission")
    @ResponseBody
    public ReturnModel queryPermission (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        return model;
    }

    /**
     * 保存该用户组所拥有的权限
     * @param json 1.group_code 用户所属的用户组编码
     * */
    @RequestMapping("/savePermission")
    @ResponseBody
    public ReturnModel savePermission (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        return model;
    }

    /**
     * 删除该用户组所拥有的权限
     * @param json 1.group_code 用户所属的用户组编码
     * */
    @RequestMapping("/deletePermission")
    @ResponseBody
    public ReturnModel deletePermission (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        return model;
    }
}
