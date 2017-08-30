package com.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.goldExchangeListService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "goldExchangeListAction", description = "金币交易页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("goldExchange")
public class goldExchangeListAction {
    @Autowired
    goldExchangeListService goldExchangeListService;

    @ApiOperation(value="获取金币交易页面填充信息", notes="默认返回根据发布时间排倒序的最近十条数据和选择框及数据字典等数据",produces = "application/json")
    @RequestMapping(value="goldExchange",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAppearanceSaleAction(
            @RequestParam(value="tradeType",required=false,defaultValue = "0") int tradeType,
            @RequestParam(value="areaSelection",required=false,defaultValue ="") String areaSelection,
            @RequestParam(value="pageNumSelected",required=false,defaultValue ="1") int pageNumSelected,
            @RequestParam(value="startNum",required=false,defaultValue ="0") int startNum,
            @RequestParam(value="endNum",required=false,defaultValue ="20") int endNum
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = goldExchangeListService.queryGoldExchangeListInfo(tradeType,areaSelection,pageNumSelected,startNum,endNum);
        Object pageList = goldExchangeListService.queryPageListNum(tradeType);
        resmap.put("datas", dataList);
        resmap.put("pageList", pageList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询金币交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取金币交易页面搜索框填充信息", notes="",produces = "application/json")
    @RequestMapping(value="goldExchangeSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListSelectionAction(
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object SelectionList = goldExchangeListService.querySelectionListInfo();
        resmap.put("selecttions", SelectionList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询金币交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    /**
     *
     * @param userName 当前用户名，
     * @param mainId
     * @param isValided 1：收藏，0：取消收藏
     * @return
     * @throws Exception
     */
    @ApiOperation(value="收藏与失效报告", notes="",produces = "application/json")
    @RequestMapping(value="userIsvalid",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> userIsvalid(
            @RequestParam(value="userName",required=true) String userName,
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="isValided",required=true) int isValided,
            @RequestParam(value="replyTime",required=true) String replyTime
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String IsvaliInfo = goldExchangeListService.userIsvalid(userName,mainId,isValided,replyTime);
        resmap.put("info", IsvaliInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取金币页面查看源信息", notes="默认返回数据",produces = "application/json")
    @RequestMapping(value="goldExchangeSource",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getGoldExchangeSourceAction(
            @RequestParam(value="userId",required=true) int userId,
            @RequestParam(value="userName",required=true) String userName,
            @RequestParam(value="mainId",required=true) String mainId

    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = goldExchangeListService.queryGoldExchangeSource(userId,userName);
        goldExchangeListService.addUserFollow(mainId,userName);
        resmap.put("datas", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询金币交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="提交失效信息", notes="默认返回数据",produces = "application/json")
    @RequestMapping(value="protDisable",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> protDisableAction(
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="userName",required=true) String userName
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = goldExchangeListService.protDisable(mainId,userName);
        resmap.put("info", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("提交失效信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
