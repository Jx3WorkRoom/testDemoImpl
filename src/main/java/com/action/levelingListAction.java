package com.action;

import com.dao.userDao;
import com.utils.Commons;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.levelingListService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "levelingListAction", description = "代练代打页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("levelingList")
public class levelingListAction {
    @Autowired
    levelingListService levelingListService;

    @Autowired
    com.dao.userDao userDao;

    @ApiOperation(value="获取代练代打页面填充信息", notes="默认返回根据发布时间排倒序的最近十条数据和选择框及数据字典等数据",produces = "application/json")
    @RequestMapping(value="levelingList",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAppearanceSaleAction(
            @RequestParam(value="needType",required=false,defaultValue = "0") int needType,
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
           dataList = levelingListService.queryLevelingListInfo(needType, areaSelection, shape, pageNumSelected, startNum, endNum);
           pageList = levelingListService.queryPageListNum(needType);
        }else{
            if(needType==1) {
                if(startNum!=0){
                    dataList = levelingListService.queryLevelingListInfo(needType, areaSelection, shape, pageNumSelected, startNum, endNum);
                    pageList = Commons.levelingListPageNum1;
                }else {
                    dataList = Commons.levelingList1;
                    pageList = Commons.levelingListPageNum1;
                }
            }else{
                if(startNum!=0){
                    dataList = levelingListService.queryLevelingListInfo(needType, areaSelection, shape, pageNumSelected, startNum, endNum);
                    pageList = Commons.levelingListPageNum2;
                }else {
                    dataList = Commons.levelingList2;
                    pageList = Commons.levelingListPageNum2;
                }
            }
        }
        resmap.put("datas", dataList);
        resmap.put("pageList", pageList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询代练代打接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取代练带打页面搜索框填充信息", notes="",produces = "application/json")
    @RequestMapping(value="levelingListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListSelectionAction(
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object SelectionList = levelingListService.querySelectionListInfo();
        resmap.put("selecttions", SelectionList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询练带打页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
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
        String IsvaliInfo = levelingListService.userIsvalid(userName,mainId,isValided,replyTime);
        resmap.put("info", IsvaliInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询收藏与失效接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取代练代打页面查看源信息", notes="默认返回数据",produces = "application/json")
    @RequestMapping(value="levelingListSource",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAppearanceSaleSourceAction(
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="sourceType",required=true) int sourceType,
            @RequestParam(value="userName",required=true) String userName,
            @RequestParam(value="userId",required=true) int userId

    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = levelingListService.querylevelingListSource(mainId,sourceType,userId,userName);
        levelingListService.addUserFollow(mainId,userName);
        resmap.put("datas", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询代练代打接口执行时间（单位：毫秒）："+ (post-pre));
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
        Object dataList = levelingListService.protDisable(mainId,userName);
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

    @ApiOperation(value="获取代练代打详情页面", notes="默认返回数据",produces = "application/json")
    @RequestMapping(value="levelingDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> levelingDetail(
            @RequestParam(value="favorId",required=true) String favorId,
            @RequestParam(value="userId",required=true) String userId,
            @RequestParam(value="sourceType",required=true) int sourceType
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object accountDetail = levelingListService.queryLevelingDetailInfo(favorId,userId,sourceType);
        resmap.put("datas", accountDetail);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易详情页面接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

}
