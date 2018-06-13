package cn.wdq.controller;

import cn.wdq.entities.ResourceModel;
import cn.wdq.entities.ReturnModel;
import cn.wdq.service.AdminService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * 导航栏节点注册及用户管理
 * @author waldon
 * */
@Controller
@RequestMapping
public class AdminController {
    private Logger logger = Logger.getLogger(AdminController.class);
    @Autowired
    AdminService admin;
    /**
     * 获取不同用户的状态列表
     * @param json include:1.userType 不同状态的用户
     * @return 所有属于该状态的用户集合
     */
    @RequestMapping("/query_usertype")
    @ResponseBody
    public ReturnModel queryUserType(@RequestBody JSONObject json) throws SQLException {
        ReturnModel model = new ReturnModel();
        String userType = json.getString("user_type");
        int page = json.getIntValue("page");
        List list = admin.queryUserType(userType, page);
        int pages = admin.queryAllPage();
        if (!list.isEmpty()) {
            model.setSuccess(true);
            model.setMessage("查询成功");
            model.setStatus(pages);//页数
            model.setData(list);
        } else {
            model.setSuccess(false);
            model.setMessage("查询的信息为空");
            model.setData(null);
            logger.info("method(query_usertype):查询的信息为空");
        }
        return model;
    }

    /**
     * 模糊查询
     * @param json include:1.user_name 用户名
     * @return 用户名中存在该关键字的所有用户集合
     */
    @RequestMapping("/query_search")
    @ResponseBody
    public ReturnModel query_search(@RequestBody JSONObject json) throws SQLException {
        ReturnModel model = new ReturnModel();
        String user_name = json.getString("user_name").trim();
        if (user_name.length() < 1) {
            model.setMessage("查询为空");
            model.setSuccess(false);
            return model;
        }
        List list = admin.querySearch(user_name);
        if (!list.isEmpty()) {
            model.setSuccess(true);
            model.setData(list);
        } else {
            model.setMessage("查询为空");
            model.setSuccess(false);
            logger.info("method(query_search):查询的信息为空");
        }
        return model;
    }

    /**
     * 删除该条用户数据
     * @param json include:1.user_name 用户名
     * @return 删除是否成功的状态
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnModel deleteUser(@RequestBody JSONObject json) throws SQLException {
        ReturnModel model = new ReturnModel();
        String user_name = json.getString("user_name").trim();
        int is_delete = admin.deleteCustomer(user_name);
        if (is_delete>0) {
            model.setSuccess(true);
            model.setMessage("删除成功");
        } else {
            model.setSuccess(false);
            model.setMessage("删除为空");
            logger.info("method(delete):删除为空");
        }
        return model;
    }

    /**
     * 踢人下线
     * @param user_name 用户名
     * @return 删除是否成功的状态
     */
    @RequestMapping("/kicking")
    @ResponseBody
    public ReturnModel kickUser(@RequestParam("user_name") String user_name) {
        ReturnModel model = new ReturnModel();
        int is_kick = admin.kicking(user_name);
        if (is_kick>0) {
            model.setSuccess(true);
            model.setMessage("删除成功");
        } else {
            model.setSuccess(false);
            model.setMessage("删除为空");
            logger.info("method(kicking):踢人下线失败");
        }
        return model;
    }

    /*Begin:模块注册开始后台API，主要是zTree树形菜单的数据构造*/

    /**
     * 保存树形菜单数据
     * @param resourceModel 当前卡片界面资源数据的实体类
     * @return 新增/编辑后节点的信息
     */
    @RequestMapping("/save_tree")
    @ResponseBody
    public ReturnModel saveTree(@RequestBody ResourceModel resourceModel) {
        ReturnModel model = new ReturnModel();
        String pk_resource = resourceModel.getPk_resource();
        String funcode = resourceModel.getFuncode();
        String funname = resourceModel.getFunname();
        boolean is_add = false;
        boolean is_edit = false;
        try {
            if (funcode == null || funcode.trim().length() == 0) {
                model.setSuccess(false);
                model.setMessage("编码为空");
                logger.info("method(save_tree):编码为空");
                throw new NullPointerException("编码为空");//回滚
            } else if (funname == null || funname.trim().length() == 0) {
                model.setSuccess(false);
                model.setMessage("名称为空");
                logger.info("method(save_tree):名称为空");
                throw new NullPointerException("名称为空");
            }
            List list_before = admin.queryByName(resourceModel);
            if (pk_resource == null || pk_resource.trim().length() == 0) {//新增节点
                if (!list_before.isEmpty()) {
                    model.setSuccess(false);
                    model.setMessage("名称或编码已存在");
                    logger.info("method(save_tree):名称或编码已存在");
                    throw new NullPointerException("名称或编码已存在");
                } else {
                    is_add = admin.save_tree(resourceModel);
                }

            } else {//编辑态
                if (!list_before.isEmpty()) {
                    model.setSuccess(false);
                    model.setMessage("名称或编码已存在");
                    logger.info("method(save_tree):名称或编码已存在");
                    throw new NullPointerException("名称或编码已存在");
                } else {
                    is_edit = admin.edit_tree(resourceModel);
                }

            }

        } catch (RuntimeException e) {
            logger.error("method(save_tree):保存模块节点失败" + e);
        }
        try {
            if (is_add || is_edit) {
                resourceModel.setPk_resource(null);//置为空再查数据
                List list_after = admin.queryByName(resourceModel);
                model.setSuccess(true);
                model.setData(list_after);
            } else {
                model.setSuccess(false);
                logger.error("method(save_tree):保存树形菜单数据出错");
            }
        } catch (Exception e) {
            logger.error("method(save_tree):保存树形菜单数据出错" + e);
        }
        return model;
    }

    /**
     * 查询树形菜单
     *
     * @param json null
     * @return 注册模块节点信息集合
     */
    @RequestMapping("/query_tree")
    @ResponseBody
    public ReturnModel queryTree(@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        try {
            List list_tree = admin.query_tree();
            model.setData(list_tree);
            model.setSuccess(true);
        } catch (Exception e) {
            logger.error("method(query_tree):查询树出错了" + e);
        }
        return model;
    }

    /**
     * 删除树的节点
     * @param resourceModel 当前卡片界面资源数据的实体类
     * @return 删除是否成功的状态
     */
    @RequestMapping("/delete_tree")
    @ResponseBody
    public ReturnModel deleteTree(@RequestBody ResourceModel resourceModel) {
        ReturnModel model = new ReturnModel();
        try {
            boolean is_delete = admin.delete_tree(resourceModel);
            if (is_delete) {
                model.setSuccess(true);
            } else {
                model.setSuccess(false);
                model.setMessage("删除失败");
                logger.error("method(delete_tree):删除失败");
                throw new NullPointerException("删除失败");
            }
        } catch (RuntimeException e) {
            logger.error("method(delete_tree):删除失败" + e);
        }
        return model;
    }
}
