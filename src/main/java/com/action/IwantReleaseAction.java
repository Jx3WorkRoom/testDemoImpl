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
    @RequestMapping(value="saveWyjbInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListAction(
            @RequestParam(value="cheatType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="tixin",required=false,defaultValue ="") String tixin,
            @RequestParam(value="roleName",required=false,defaultValue ="") String roleName,
            @RequestParam(value="cheatIntro",required=false,defaultValue="") String cheatIntro,
            @RequestParam(value="cheatInfo",required=false,defaultValue ="") String cheatInfo,
            @RequestParam(value="pageUrl",required=false,defaultValue ="") String pageUrl
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveWyjbInfo(tradeType,belongQf,tixin,roleName,cheatIntro,cheatInfo,pageUrl);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="外观交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveWgjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveWgjyInfoAction(
            @RequestParam(value="cheatType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="viewName",required=false,defaultValue ="") String viewName,
            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveWgjyInfo(tradeType,belongQf,viewName,priceNum,favorInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="道具交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveDjjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveDjjyInfoAction(
            @RequestParam(value="tradeType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="propName",required=false,defaultValue ="") String propName,
            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveDjjyInfo(tradeType,belongQf,propName,priceNum,favorInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="金币交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveJbjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveDjjyInfoAction(
            @RequestParam(value="tradeType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="goldTotal",required=false,defaultValue ="") int goldTotal,
            @RequestParam(value="unitPrice",required=false,defaultValue ="") int unitPrice,
            @RequestParam(value="ifSplit",required=false,defaultValue="") int ifSplit,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveJbjyInfo(tradeType,belongQf,goldTotal,unitPrice,ifSplit,favorInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="代练代打", notes="保存",produces = "application/json")
    @RequestMapping(value="saveDlddInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveDlddInfoAction(
            @RequestParam(value="tradeType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveDlddInfo(tradeType,belongQf,favorInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="账号快售快速发布", notes="保存",produces = "application/json")
    @RequestMapping(value="saveZhssInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveZhssInfoAction(
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="tixin",required=false,defaultValue ="") String tixin,
            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
            @RequestParam(value="accoInfo",required=false,defaultValue ="") String accoInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveZhssInfo(belongQf,tixin,priceNum,accoInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

}
