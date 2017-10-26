package com.artisan.handler;

import com.artisan.common.constant.StatusCode;
import com.artisan.pojo.db.User;
import com.artisan.pojo.vo.ResultBean;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 * @author Leeyom Wang
 * @date 2017年10月26日 15:20
 */
@Api(description = "user管理", tags = "UserHandler", basePath = "/users")
@Controller
@RequestMapping("/users")
public class UserHandler extends BaseHandler{

    private static final Logger LOGGER = Logger.getLogger(UserHandler.class);

    @ApiOperation(value = "查询列表")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean getUserList() {
        ResultBean resultBean = new ResultBean();
        try {
            List<User> userList = userService.selectAll();
            resultBean.setData(userList);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Request User list Failed！");
            LOGGER.error("查询列表失败！");
        }
        return resultBean;
    }

    @ApiOperation(value = "根据id查询指定的User")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, defaultValue = "", dataType = "int", paramType = "path"),
    })
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultBean getUser(@PathVariable("id") Integer id) {
        ResultBean resultBean = new ResultBean();
        try {
            User user = userService.selectByPrimaryKey(id);
            resultBean.setData(user);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Failed to request User details！");
            LOGGER.error("查询指定的User失败！参数信息：id = " + id);
        }
        return resultBean;
    }

    @ApiOperation(value = "新增User")
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultBean add(@ApiParam(value = "新增User实体", required = true) @RequestBody User user) {
        ResultBean resultBean = new ResultBean();
        try {
            userService.insert(user);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Create User Failed！");
            LOGGER.error("新增User！参数信息：User = " + user.toString());
        }
        return resultBean;
    }

    @ApiOperation(value = "更新指定的User")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, defaultValue = "", dataType = "int", paramType = "path"),
    })
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultBean update(@PathVariable("id") Integer id, @ApiParam(value = "更新User详细信息", required = true) @RequestBody User user) {
        ResultBean resultBean = new ResultBean();
        try {
            User oldUser = userService.selectByPrimaryKey(id);
            oldUser.setAge(user.getAge());
            oldUser.setBirthday(user.getBirthday());
            oldUser.setPassword(user.getPassword());
            oldUser.setSex(user.getSex());
            oldUser.setuName(user.getuName());
            userService.updateByPrimaryKey(oldUser);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Update User failed！");
            LOGGER.error("更新失败！参数信息：id = " + id + ",User = " + user.toString());
        }
        return resultBean;
    }

    @ApiOperation(value = "根据id物理删除指定的User，需谨慎！")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, defaultValue = "", dataType = "int", paramType = "path"),
    })
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultBean delete(@PathVariable("id") Integer id) {
        ResultBean resultBean = new ResultBean();
        try {
            userService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            resultBean.setCode(StatusCode.HTTP_FAILURE);
            resultBean.setMsg("Delete User failed！");
            e.printStackTrace();
            LOGGER.error("删除失败！参数信息：id = " + id);
        }
        return resultBean;
    }

}

