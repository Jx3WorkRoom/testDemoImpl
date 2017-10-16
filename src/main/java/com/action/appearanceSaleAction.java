package com.action;

import com.utils.Commons;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.appearanceSaleService;
import com.dao.userDao;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "appearanceSaleAction", description = "外观交易页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("appearanceSale")
public class appearanceSaleAction {
    @Autowired
    appearanceSaleService appearanceSaleService;

    @Autowired
    userDao userDao;

    @ApiOperation(value="获取外观交易页面填充信息", notes="默认返回根据发布时间排倒序的最近十条数据和选择框及数据字典等数据",produces = "application/json")
    @RequestMapping(value="appearanceSale",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAppearanceSaleAction(
            @RequestParam(value="tradeType",required=false,defaultValue = "0") int tradeType,
            @RequestParam(value="areaSelection",required=false,defaultValue ="") String areaSelection,
            @RequestParam(value="shape",required=false,defaultValue ="") String shape,
            @RequestParam(value="pageNumSelected",required=false,defaultValue ="1") int pageNumSelected,
            @RequestParam(value="startNum",required=false,defaultValue ="0") int startNum,
            @RequestParam(value="endNum",required=false,defaultValue ="20") int endNum
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = new Object();
        Object pageList = new Object();
        if(!"".equals(shape)||!"".equals(areaSelection)) {
            dataList = appearanceSaleService.queryAppearanceSaleInfo(tradeType, areaSelection, shape, pageNumSelected, startNum, endNum);
            pageList = appearanceSaleService.queryPageListNum();
        }else{
            if(tradeType==1) {
                if(startNum!=0){
                    dataList = appearanceSaleService.queryAppearanceSaleInfo(tradeType, areaSelection, shape, pageNumSelected, startNum, endNum);
                    pageList = Commons.appearanceSalelistPageNum1;
                }else {
                    dataList = Commons.appearanceSaleList1;
                    pageList = Commons.appearanceSalelistPageNum1;
                }
            }else {
                if(startNum!=0){
                    dataList = appearanceSaleService.queryAppearanceSaleInfo(tradeType, areaSelection, shape, pageNumSelected, startNum, endNum);
                    pageList = Commons.appearanceSalelistPageNum2;
                }else {
                    dataList = Commons.appearanceSaleList2;
                    pageList = Commons.appearanceSalelistPageNum2;
                }
            }
        }
        resmap.put("datas", dataList);
        resmap.put("pageList", pageList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询外观交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取外观交易页面搜索框填充信息", notes="",produces = "application/json")
    @RequestMapping(value="accountListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListSelectionAction(
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object SelectionList = appearanceSaleService.querySelectionListInfo();
        resmap.put("selecttions", SelectionList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询外观交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
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
        String IsvaliInfo = appearanceSaleService.userIsvalid(userName,mainId,isValided,replyTime);
        resmap.put("info", IsvaliInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="获取外观页面查看源信息", notes="默认返回数据",produces = "application/json")
    @RequestMapping(value="appearanceSaleSource",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAppearanceSaleSourceAction(
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="sourceType",required=true) int sourceType,
            @RequestParam(value="userName",required=true) String userName,
            @RequestParam(value="userId",required=true) int userId
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = appearanceSaleService.queryappearanceSaleSource(mainId,sourceType,userId,userName);
        if(!"".equals(userName)) {
            appearanceSaleService.addUserFollow(mainId, userName);
        }
        resmap.put("datas", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
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
        Object dataList = appearanceSaleService.protDisable(mainId,userName);
        resmap.put("info", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("提交失效信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="获取用户ID", notes="默认返回数据",produces = "application/json")
    @RequestMapping(value="getUserId",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getUserId(
            @RequestParam(value="username",required=true) String userName
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object userId = userDao.getUserId(userName);
        resmap.put("userId", userId);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("获取用户ID接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

}
