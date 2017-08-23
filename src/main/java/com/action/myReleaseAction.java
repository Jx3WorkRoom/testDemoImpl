package com.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.myReleaseService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "myReleaseAction", description = "我的发布", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("myRelease")
public class myReleaseAction {
    @Autowired
    myReleaseService myReleaseService;

    @ApiOperation(value="根据用户名获取用户发布信息", notes="",produces = "application/json")
    @RequestMapping(value="myReleaseInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> myReleaseInfo(
            @RequestParam(value="userName",required=true) String userName,
            @RequestParam(value="num",required=true) int num
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object userInfo = myReleaseService.myReleaseInfo(userName,num);
        Object pageList = myReleaseService.queryPageListNum();
        resmap.put("datas",userInfo);
        resmap.put("pageList", pageList);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="根据记录id删除用户发布信息", notes="",produces = "application/json")
    @RequestMapping(value="removeRecord",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> removeRecord(
            @RequestParam(value="ids",required=true) String[] ids
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = myReleaseService.removeRecord(ids);
        resmap.put("info",info);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

}
