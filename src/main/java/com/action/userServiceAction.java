package com.action;

import com.service.myServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

import com.service.userServiceService;

@RestController
@Api(value = "userServiceAction", description = "查询用户服务信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("userService")
public class userServiceAction {
    @Autowired
    userServiceService userServiceService;

    @ApiOperation(value="获取用户服务信息", notes="",produces = "application/json")
    @RequestMapping(value="getServiceDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> queryUserInfoByUserId(
            @RequestParam(value="searchInfo",required=false,defaultValue ="") String searchInfo,
            @RequestParam(value="searchType",required=false,defaultValue ="0") int searchType,
            @RequestParam(value="type",required=false,defaultValue ="0") int type,
            @RequestParam(value="num",required=false,defaultValue ="0") int num
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object datas = new Object();
        if(type==1) {
            datas = userServiceService.getServiceDetail(searchInfo,searchType);
        }else if(type==2){
            datas = userServiceService.getServiceDetail2(searchInfo,searchType,num);
            Object pageList = userServiceService.queryPageListNum();
            resmap.put("pageList", pageList);
        }else if(type==3){
            datas = userServiceService.getServiceDetail3(searchInfo,searchType,num);
            Object pageList = userServiceService.queryPageListNum();
            resmap.put("pageList", pageList);
        }
        resmap.put("datas", datas);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("获取用户服务信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
