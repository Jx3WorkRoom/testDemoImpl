package com.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.blackListService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "blackListAction", description = "黑榜页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("blackList")
public class blackListAction {
    @Autowired
    blackListService blackListService;

    @ApiOperation(value="获取黑鬼页面填充信息", notes="默认返回根据发布时间排倒序的最近十条数据和选择框及数据字典等数据",produces = "application/json")
    @RequestMapping(value="getblackListAction",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getblackListAction(
            @RequestParam(value="shape",required=false,defaultValue ="") String shape,
            @RequestParam(value="pageNumSelected",required=false,defaultValue ="1") int pageNumSelected,
            @RequestParam(value="startNum",required=false,defaultValue ="0") int startNum,
            @RequestParam(value="endNum",required=false,defaultValue ="20") int endNum
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = blackListService.queryblackListInfo(shape,pageNumSelected,startNum,endNum);
        Object pageList = blackListService.queryPageListNum();
        resmap.put("datas", dataList);
        resmap.put("pageList", pageList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询外观交易接口执行时间（单位：毫秒）："+ (post-pre));
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
        String IsvaliInfo = blackListService.userIsvalid(userName,mainId,isValided,replyTime);
        resmap.put("info", IsvaliInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取黑鬼页面信息", notes="通过favor_id定位具体详情",produces = "application/json")
    @RequestMapping(value="blackDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getblackDetailAction(
            @RequestParam(value="favorId",required=true) int favorId,
            @RequestParam(value="username",required=true) String username
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object SelectionList = blackListService.queryblackListByFavorIdInfo(favorId);
        blackListService.addUserFollow(favorId,username);
        resmap.put("datas", SelectionList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易详情页面接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
