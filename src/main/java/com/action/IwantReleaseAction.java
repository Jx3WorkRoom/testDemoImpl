package com.action;


import com.service.IwantReleaseService;
import com.service.accountListService;
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

@RestController
@Api(value = "accountListAction", description = "账号交易页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("iwantRelease")
public class IwantReleaseAction {
    @Autowired
    IwantReleaseService iwantReleaseService;

    @ApiOperation(value="我要举报", notes="保存",produces = "application/json")
    @RequestMapping(value="reportSave",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListAction(
            @RequestParam(value="cheatType",required=false,defaultValue = " ") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="tixin",required=false,defaultValue ="") String tixin,
            @RequestParam(value="roleName",required=false,defaultValue ="") String roleName,
            @RequestParam(value="cheatIntro",required=false,defaultValue="") String cheatIntro,
            @RequestParam(value="cheatInfo",required=false,defaultValue ="") String cheatInfo,
            @RequestParam(value="pageUrl",required=false,defaultValue ="") String pageUrl
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveInfo(tradeType,belongQf,tixin,roleName,cheatIntro,cheatInfo,pageUrl);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }



}
