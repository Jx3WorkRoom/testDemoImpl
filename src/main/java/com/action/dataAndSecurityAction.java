package com.action;


import com.service.accountListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.dataAndSecurityService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "dataAndSecurityAction", description = "用户资料与安全", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("dataAndSecurity")
public class dataAndSecurityAction {
    @Autowired
    dataAndSecurityService  dataAndSecurityService;

    @ApiOperation(value="根据用户名获取用户信息", notes="",produces = "application/json")
    @RequestMapping(value="getUserInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getUserInfo(
            @RequestParam(value="userName",required=true) String userName
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object userInfo = dataAndSecurityService.getUserInfo(userName);
        resmap.put("datas",userInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="根据类别修改用户信息", notes="",produces = "application/json")
    @RequestMapping(value="editInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editInfo(
            @RequestParam(value="type",required=true) int type,
            @RequestParam(value="newValue",required=true) String newValue,
            @RequestParam(value="userId",required=true) int userId
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = dataAndSecurityService.editInfo(type,newValue,userId);
        resmap.put("info",info);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
