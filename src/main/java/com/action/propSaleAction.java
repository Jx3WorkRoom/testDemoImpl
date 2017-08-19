package com.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.propSaleService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "propSaleAction", description = "道具交易页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("propSale")
public class propSaleAction {
    @Autowired
    propSaleService propSaleService;

    @ApiOperation(value="获取道具交易页面填充信息", notes="默认返回根据发布时间排倒序的最近十条数据和选择框及数据字典等数据",produces = "application/json")
    @RequestMapping(value="propSale",method = RequestMethod.GET)
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
        Object dataList = propSaleService.queryPropSaleInfo(tradeType,areaSelection,shape,pageNumSelected,startNum,endNum);
        Object pageList = propSaleService.queryPageListNum();
        resmap.put("datas", dataList);
        resmap.put("pageList", pageList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询道具交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取道具交易页面搜索框填充信息", notes="",produces = "application/json")
    @RequestMapping(value="propSaleSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListSelectionAction(
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object SelectionList = propSaleService.querySelectionListInfo();
        Object tixinList = propSaleService.queryTixinListInfo();
        resmap.put("selecttions", SelectionList);
        resmap.put("tixin", tixinList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询道具交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
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
        String IsvaliInfo = propSaleService.userIsvalid(userName,mainId,isValided,replyTime);
        resmap.put("info", IsvaliInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取外观页面查看源信息", notes="默认返回数据",produces = "application/json")
    @RequestMapping(value="propSaleSource",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getPropSaleSourceAction(
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="sourceType",required=true) int sourceType,
            @RequestParam(value="userId",required=true) int userId

    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = propSaleService.queryPropSaleSource(mainId,sourceType,userId);
        propSaleService.addUserFollow(mainId);
        resmap.put("datas", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询道具交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="提交失效信息", notes="默认返回数据",produces = "application/json")
    @RequestMapping(value="protDisable",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> protDisableAction(
            @RequestParam(value="mainId",required=true) String mainId

    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = propSaleService.protDisable(mainId);
        resmap.put("info", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("提交失效信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
