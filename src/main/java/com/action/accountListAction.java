package com.action;


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
import com.service.accountListService;

@RestController
@Api(value = "accountListAction", description = "账号交易页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("accountList")
public class accountListAction {
    @Autowired
    accountListService accountListService;

    @ApiOperation(value="获取账号交易页面填充信息", notes="默认返回根据发布时间排倒序的最近十条数据和选择框及数据字典等数据",produces = "application/json")
    @RequestMapping(value="accountList",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListAction(
            @RequestParam(value="tradeType",required=false,defaultValue = "1") int tradeType,
            @RequestParam(value="areaSelection",required=false,defaultValue ="") String areaSelection,
            @RequestParam(value="shape",required=false,defaultValue ="") String shape,
            @RequestParam(value="info",required=false,defaultValue ="") String info,
            @RequestParam(value="pageNumSelected",required=false,defaultValue ="1") int pageNumSelected,
            @RequestParam(value="startNum",required=false,defaultValue ="0") int startNum,
            @RequestParam(value="endNum",required=false,defaultValue ="10") int endNum
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = accountListService.queryAccountListInfo(tradeType,areaSelection,shape,info,pageNumSelected,startNum,endNum);
        Object pageList = accountListService.queryPageListNum();
        resmap.put("datas", dataList);
        resmap.put("pageList", pageList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="获取账号交易页面搜索框填充信息", notes="",produces = "application/json")
    @RequestMapping(value="accountListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListSelectionAction(
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object SelectionList = accountListService.querySelectionListInfo();
        Object tixinList = accountListService.queryTixinListInfo();
        Object infoList = accountListService.queryInfoListInfo();
        resmap.put("selecttions", SelectionList);
        resmap.put("tixin", tixinList);
        resmap.put("info", infoList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取账号交易详情页面信息", notes="通过favor_id定位具体详情",produces = "application/json")
    @RequestMapping(value="accountDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountDetailAction(
            @RequestParam(value="favorId",required=true) int favorId
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object SelectionList = accountListService.queryAccountDetailInfo(favorId);
        resmap.put("datas", SelectionList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易详情页面接口执行时间（单位：毫秒）："+ (post-pre));
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
        String IsvaliInfo = accountListService.userIsvalid(userName,mainId,isValided,replyTime);
        resmap.put("info", IsvaliInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易详情页面接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取账号详情页面查看源信息", notes="默认返回根据发布时间排倒序的最近十条数据和选择框及数据字典等数据",produces = "application/json")
    @RequestMapping(value="accountDetailSource",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListAction(
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="sourceType",required=true) int sourceType,
            @RequestParam(value="userId",required=true) int userId,
            @RequestParam(value="startNum",required=false,defaultValue ="0") int startNum,
            @RequestParam(value="endNum",required=false,defaultValue ="10") int endNum

    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = accountListService.queryaccountDetailSource(mainId,sourceType,userId,startNum,endNum);
        resmap.put("datas", dataList);
        if(sourceType==1) {
            Object pageList = accountListService.queryPageListNum();
            resmap.put("pageList", pageList);
        }
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="提交失效详情记录", notes="通过favor_id定位具体详情",produces = "application/json")
    @RequestMapping(value="accountDetailSubmitIsValid",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> accountDetailSubmitIsValid(
            @RequestParam(value="favorId",required=true) int favorId
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = accountListService.accountDetailSubmitIsValid(favorId);
        resmap.put("info", info);
        long post=System.currentTimeMillis();
        System.out.println("提交失效详情记录接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
