package com.action;

import com.service.userInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.myServiceService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "myServiceAction", description = "查询我的服务信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("myService")
public class myServiceAction {
    @Autowired
    myServiceService myServiceService;

    @ApiOperation(value="获取服务信息通过userId", notes="",produces = "application/json")
    @RequestMapping(value="getServiceDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> queryUserInfoByUserId(
            @RequestParam(value="username",required=false,defaultValue ="0") String username,
            @RequestParam(value="type",required=false,defaultValue ="0") int type,
            @RequestParam(value="num",required=false,defaultValue ="0") int num
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object datas = new Object();
        if(type==1) {
            datas = myServiceService.queryServiceInfoByUserId(username);
        }else if(type==2){
            datas = myServiceService.queryServiceInfoByUserId2(username,num);
            Object pageList = myServiceService.queryPageListNum();
            resmap.put("pageList", pageList);
        }else if(type==3){
            datas = myServiceService.queryServiceInfoByUserId3(username,num);
            Object pageList = myServiceService.queryPageListNum();
            resmap.put("pageList", pageList);
        }
        resmap.put("datas", datas);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
