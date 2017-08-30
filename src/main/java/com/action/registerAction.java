package com.action;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.registerService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "registerAction", description = "用户注册", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("register")
public class registerAction {
    @Autowired
    registerService registerService;

    @ApiOperation(value="新增用户", notes="】",produces = "application/json")
    @RequestMapping(value="addUser",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> addUser(
            @RequestParam(value="loginName",required=true) String loginName,
            @RequestParam(value="userName",required=true) String userName,
            @RequestParam(value="loginWord",required=true) String loginWord,
            @RequestParam(value="tel",required=true) String tel
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = registerService.addUser(loginName,userName,loginWord,tel);
        resmap.put("info", info);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("新增用户接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
