package com.action;

import com.service.myReleaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.userManageService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "userManageAction", description = "功能权限管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("userManage")
public class userManageAction {
    @Autowired
    userManageService userManageService;

    @ApiOperation(value="查询功能模块", notes="",produces = "application/json")
    @RequestMapping(value="userManageInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> userManageInfo(
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object userInfo = userManageService.userManageInfo();
        resmap.put("datas",userInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询功能模块接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="查询信息定制权限", notes="",produces = "application/json")
    @RequestMapping(value="userManageInfo2",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> userManageInfo2(
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object userInfo = userManageService.userManageInfo();
        resmap.put("datas",userInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询功能模块接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
