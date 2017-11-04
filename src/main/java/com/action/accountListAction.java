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
            @RequestParam(value="lowPrice",required=false,defaultValue ="0") String lowPrice,
            @RequestParam(value="highPrice",required=false,defaultValue ="0") String highPrice,
            @RequestParam(value="hasChecked",required=false,defaultValue ="true") String hasChecked,
            @RequestParam(value="pageNumSelected",required=false,defaultValue ="1") int pageNumSelected,
            @RequestParam(value="startNum",required=false,defaultValue ="0") int startNum,
            @RequestParam(value="endNum",required=false,defaultValue ="30") int endNum
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = new Object();
        Object pageList = new Object();
        if(!"".equals(shape)||!"".equals(info)||!"".equals(areaSelection)||!"0".equals(lowPrice)||!"0".equals(highPrice)) {
            dataList = accountListService.queryAccountListInfo(tradeType, areaSelection, shape, info, pageNumSelected, startNum, endNum,lowPrice,highPrice,hasChecked);
            pageList = accountListService.queryPageListNum();
        }else{
            if(tradeType==1) {
                if(startNum!=0){
                    dataList = accountListService.queryAccountListInfo(tradeType, areaSelection, shape, info, pageNumSelected, startNum, endNum,lowPrice,highPrice,hasChecked);
                    pageList = Commons.accountlistPageNum1;
                }else {
                    dataList = Commons.accountList1;
                    pageList = Commons.accountlistPageNum1;
                }
            }else{
                if(startNum!=0){
                    dataList = accountListService.queryAccountListInfo(tradeType, areaSelection, shape, info, pageNumSelected, startNum, endNum,lowPrice,highPrice,hasChecked);
                    pageList = Commons.accountlistPageNum2;
                }else {
                    dataList = Commons.accountList2;
                    pageList = Commons.accountlistPageNum2;
                }
            }
        }
        Object segMentWordMap = Commons.segMentWordMap;
        resmap.put("datas", dataList);
        resmap.put("pageList", pageList);
        resmap.put("segMentWordMap", segMentWordMap);
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
        Object faxinList = accountListService.queryFaxinListInfo();
        Object heziList = accountListService.queryHeziListInfo();
        Object pifengList = accountListService.queryPifengListInfo();
        Object wuxianList = accountListService.queryWuxianListInfo();
        Object liuxianList = accountListService.queryliuxianListInfo();
        Object chengyiList = accountListService.querychengyiListInfo();
        Object qiyuList = accountListService.queryqiyuListInfo();
        Object c5List = accountListService.queryc5ListInfo();
        Object guajianList = accountListService.queryguajianListInfo();
        resmap.put("selecttions", SelectionList);
        resmap.put("tixin", tixinList);
        resmap.put("faxin", faxinList);
        resmap.put("hezi", heziList);
        resmap.put("pifeng", pifengList);
        resmap.put("wuxian", wuxianList);
        resmap.put("liuxian", liuxianList);
        resmap.put("chengyi", chengyiList);
        resmap.put("qiyu", qiyuList);
        resmap.put("c5", c5List);
        resmap.put("guajian", guajianList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取账号交易详情页面信息", notes="通过favor_id定位具体详情",produces = "application/json")
    @RequestMapping(value="accountDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountDetailAction(
            @RequestParam(value="favorId",required=true) int favorId,
            @RequestParam(value="sourceType",required=true) int sourceType,
            @RequestParam(value="userName",required=false,defaultValue = "") String userName
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object accountDetail = accountListService.queryAccountDetailInfo(favorId,sourceType);
        accountListService.addUserFollow(favorId,userName);
        resmap.put("datas", accountDetail);
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
            @RequestParam(value="userName",required=true) String userName,
            @RequestParam(value="startNum",required=false,defaultValue ="0") int startNum,
            @RequestParam(value="endNum",required=false,defaultValue ="10") int endNum

    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = accountListService.queryaccountDetailSource(mainId,sourceType,userId,startNum,endNum,userName);
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

    @ApiOperation(value="查询是否已经收藏", notes="",produces = "application/json")
    @RequestMapping(value="queryHasCollected",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> queryHasCollected(
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="username",required=true) String username
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = accountListService.queryHasCollected(mainId,username);
        resmap.put("info", info);
        long post=System.currentTimeMillis();
        System.out.println("提交失效详情记录接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="单个外观成交价格", notes="",produces = "application/json")
    @RequestMapping(value="appearancePrice1",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> appearancePrice1(
            @RequestParam(value="qufu",required=true) String qufu,
            @RequestParam(value="viewName",required=true) String viewName,
            @RequestParam(value="priceNum",required=true) int priceNum,
            @RequestParam(value="favorDate",required=true) String favorDate,
            @RequestParam(value="userID",required=true) String userID
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = accountListService.appearancePrice1(qufu,viewName,priceNum,favorDate,userID);
        resmap.put("info", info);
        long post=System.currentTimeMillis();
        System.out.println("单个外观成交价格接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="批量外观成交价格", notes="",produces = "application/json")
    @RequestMapping(value="appearancePrice2",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> appearancePrice2(
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="username",required=true) String username
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = accountListService.queryHasCollected(mainId,username);
        resmap.put("info", info);
        long post=System.currentTimeMillis();
        System.out.println("批量外观成交价格接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="单个外观预期价格", notes="",produces = "application/json")
    @RequestMapping(value="appearancePrice3",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> appearancePrice3(
            @RequestParam(value="qufu",required=true) String qufu,
            @RequestParam(value="viewName",required=true) String viewName,
            @RequestParam(value="viewContent",required=true) String viewContent,
            @RequestParam(value="priceLow",required=false,defaultValue = "0") int priceLow,
            @RequestParam(value="priceHigh",required=false,defaultValue = "0") int priceHigh,
            @RequestParam(value="priceHN",required=false,defaultValue = "0") int priceHN,
            @RequestParam(value="PRICE_HN_HIGH",required=false,defaultValue = "0") int PRICE_HN_HIGH,
            @RequestParam(value="favorDate",required=true) String favorDate,
            @RequestParam(value="userID",required=true) String userID
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = accountListService.appearancePrice3(qufu,viewName,viewContent,priceLow,priceHigh,priceHN,PRICE_HN_HIGH,favorDate,userID);
        resmap.put("info", info);
        long post=System.currentTimeMillis();
        System.out.println("单个外观预期价格接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="批量外观预期价格", notes="",produces = "application/json")
    @RequestMapping(value="appearancePrice4",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> appearancePrice4(
            @RequestParam(value="mainId",required=true) String mainId,
            @RequestParam(value="username",required=true) String username
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = accountListService.queryHasCollected(mainId,username);
        resmap.put("info", info);
        long post=System.currentTimeMillis();
        System.out.println("批量外观预期价格接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="保存用户搜索记录", notes="",produces = "application/json")
    @RequestMapping(value="keepQuery",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> keepQueryAction(
            @RequestParam(value="tradeType",required=false,defaultValue = "") String tradeType,
            @RequestParam(value="areaSelection",required=false,defaultValue = "") String areaSelection,
            @RequestParam(value="menpai",required=false,defaultValue = "") String menpai,
            @RequestParam(value="tixin",required=false,defaultValue = "") String tixin,
            @RequestParam(value="faxin",required=false,defaultValue = "") String faxin,
            @RequestParam(value="hezi",required=false,defaultValue = "") String hezi,
            @RequestParam(value="pifeng",required=false,defaultValue = "") String pifeng,
            @RequestParam(value="wuxian",required=false,defaultValue = "") String wuxian,
            @RequestParam(value="liuxian",required=false,defaultValue = "") String liuxian,
            @RequestParam(value="chengyi",required=false,defaultValue = "") String chengyi,
            @RequestParam(value="qiyu",required=false,defaultValue = "") String qiyu,
            @RequestParam(value="chengwu",required=false,defaultValue = "") String chengwu,
            @RequestParam(value="guajia",required=false,defaultValue = "") String guajia,
            @RequestParam(value="lowPrice",required=false,defaultValue = "0") String lowPrice,
            @RequestParam(value="highPrice",required=false,defaultValue = "0") String highPrice,
            @RequestParam(value="info",required=false,defaultValue = "") String info,
            @RequestParam(value="username",required=false,defaultValue = "") String username,
            @RequestParam(value="fanganName",required=false,defaultValue = "") String fanganName
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object result = accountListService.keepQuery(tradeType,areaSelection,menpai,tixin,faxin,hezi,pifeng,wuxian,liuxian,chengyi,qiyu,chengwu,guajia,lowPrice,highPrice,info,username,fanganName);
        resmap.put("info", result);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("保存用户搜索记录接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
