package com.action;

import com.service.userService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
@Api(value = "UserAction", description = "用户表基本增删改查通用接口Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("User")
public class UserAction {
    @Autowired
    userService userService;
    @ApiOperation(value="获取用户详细信息", notes="根据conditions条件去获得用户信息详细信息",produces = "application/json")
    @RequestMapping(value="userInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getUserInfo(
            @RequestParam(value="username",required=false) String username
            ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = userService.queryUser(username);
        Object userInfo = userService.queryUserInfo(username);
        resmap.put("datas", dataList);
        resmap.put("userInfo", userInfo);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="修改用户详细信息", notes="根据条件去获得用户修改后信息根据id定位",produces = "application/json")
    @RequestMapping(value="editUser",method = RequestMethod.GET)
    @GET
    @Produces("application/json")
    public String editUser(
            @RequestParam(value="id") String id,
            @RequestParam(value="username") String username,
            @RequestParam(value="userGroup") Integer usergroup,
            @RequestParam(value="userAuthority") Integer userAuthority,
            @RequestParam(value="password") String password,
            @RequestParam(value="employeeNo") String employeeNo,
            @RequestParam(value="createTime") String createTime,
            @RequestParam(value="role") String role
            ) throws Exception {
        username = java.net.URLDecoder.decode(username,"utf-8");
        long pre=System.currentTimeMillis();
        userService.editUser(id,username,usergroup,userAuthority,password,employeeNo,createTime,role);
        long post=System.currentTimeMillis();
        System.out.println("修改用户接口执行时间（单位：毫秒）："+ (post-pre));
        return  "true";
    }

    @ApiOperation(value="新增用户", notes="新增用户",produces = "application/json")
    @RequestMapping(value = "addUser",method = RequestMethod.GET)
    @GET
    @Produces("application/json")
    public int addUser(
            @RequestParam(value="username") String username,
            @RequestParam(value="password") String password,
            @RequestParam(value="userGroup") Integer userGroup,
            @RequestParam(value="employeeNo") String employeeNo,
            @RequestParam(value="userAuthority") Integer userAuthority,
            @RequestParam(value="createTime") String createTime,
            @RequestParam(value="role") String role
    ) throws Exception {
        username = java.net.URLDecoder.decode(username,"utf-8");
        long pre=System.currentTimeMillis();
        int addSureFlag = userService.addUser(username,password,userGroup,employeeNo,userAuthority,createTime,role);
        long post=System.currentTimeMillis();
        System.out.println("新增用户接口执行时间（单位：毫秒）："+ (post-pre));
        return  addSureFlag;
    }

    @ApiOperation(value="删除用户", notes="根据id定位",produces = "application/json")
    @RequestMapping(value = "delUser",method = RequestMethod.GET)
    @GET
    @Produces("application/json")
    public int delUser(
            @RequestParam(value="ids") String[] ids
    ) throws Exception {
        long pre=System.currentTimeMillis();
        int delSureFlag = userService.delUser(ids);
        long post=System.currentTimeMillis();
        System.out.println("删除用户接口执行时间（单位：毫秒）："+ (post-pre));
        return delSureFlag;
    }

    @RequestMapping(value ="testAddTable")
    public  int testAddTable(
            @RequestParam(value="str") String str
    ) throws Exception {
        str = java.net.URLDecoder.decode(str,"utf-8");
        int testAddTableFlag = userService.testAddTable(str);
        return testAddTableFlag;
    }
}
