package com.action;

import com.service.myReleaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.userManageService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "userManageAction", description = "功能权限管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("userManage")
public class userManageAction {
    @Autowired
    userManageService userManageService;

    @ApiOperation(value="查询功能模块", notes="",produces = "application/json")
    @RequestMapping(value="userManageInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> userManageInfo(
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object userInfo = userManageService.userManageInfo();
        resmap.put("datas",userInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询功能模块接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="查询信息定制权限", notes="",produces = "application/json")
    @RequestMapping(value="userManageInfo2",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> userManageInfo2(
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object userInfo = userManageService.userManageInfo();
        resmap.put("datas",userInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询功能模块接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="删除信息权限模块", notes="",produces = "application/json")
    @RequestMapping(value="delModel",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> delMod(
            @RequestParam(value="modId",required=false,defaultValue ="") int modId
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = userManageService.delMod(modId);
        resmap.put("info",info);
        long post=System.currentTimeMillis();
        System.out.println("删除信息权限模块接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="新增信息权限模块", notes="",produces = "application/json")
    @RequestMapping(value="addMod",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> addMod(
            @RequestParam(value="BELONG_WEB",required=false,defaultValue ="") String BELONG_WEB,
            @RequestParam(value="modId",required=true) int modId,
            @RequestParam(value="MOD_NAME",required=false,defaultValue ="") String MOD_NAME,
            @RequestParam(value="modType",required=false,defaultValue ="0") int modType,
            @RequestParam(value="visitRole",required=false,defaultValue ="0") int visitRole,
            @RequestParam(value="registRole",required=false,defaultValue = "0") int registRole,
            @RequestParam(value="serverCost",required=false,defaultValue ="") String serverCost,
            @RequestParam(value="serverNum",required=false,defaultValue ="") String serverNum
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = userManageService.addMod(BELONG_WEB,modId,MOD_NAME,modType,visitRole,registRole,serverCost,serverNum);
        resmap.put("info",info);
        long post=System.currentTimeMillis();
        System.out.println("修改信息权限模块接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="修改信息权限模块", notes="",produces = "application/json")
    @RequestMapping(value="editMod",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editMod(
            @RequestParam(value="BELONG_WEB",required=false,defaultValue ="") String BELONG_WEB,
            @RequestParam(value="modId",required=true) int modId,
            @RequestParam(value="MOD_NAME",required=false,defaultValue ="") String MOD_NAME,
            @RequestParam(value="modType",required=false,defaultValue ="0") int modType,
            @RequestParam(value="visitRole",required=false,defaultValue ="0") int visitRole,
            @RequestParam(value="registRole",required=false,defaultValue = "0") int registRole,
            @RequestParam(value="serverCost",required=false,defaultValue ="") String serverCost,
            @RequestParam(value="serverNum",required=false,defaultValue ="") String serverNum
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = userManageService.editMod(BELONG_WEB,modId,MOD_NAME,modType,visitRole,registRole,serverCost,serverNum);
        resmap.put("info",info);
        long post=System.currentTimeMillis();
        System.out.println("修改信息权限模块接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="查询模块详情", notes="",produces = "application/json")
    @RequestMapping(value="modDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> modDetail(
            @RequestParam(value="modId",required=true) int modId
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object userInfo = userManageService.modDetail(modId);
        resmap.put("datas",userInfo);
        long post=System.currentTimeMillis();
        System.out.println("查询功能模块接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }



    @ApiOperation(value="删除模块详情", notes="",produces = "application/json")
    @RequestMapping(value="delMolDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> delMolDetail(
            @RequestParam(value="recordId",required=true) String recordId
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = userManageService.delMolDetail(recordId);
        resmap.put("info",info);
        long post=System.currentTimeMillis();
        System.out.println("删除模块详情接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="新增或者修改模块详情", notes="",produces = "application/json")
    @RequestMapping(value="newModDetail",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> newModDetail(
            @RequestParam(value="recordId",required=false,defaultValue = "") String recordId,
            @RequestParam(value="belondWeb",required=false,defaultValue = "") String belondWeb,
            @RequestParam(value="modId",required=false,defaultValue = "") String modId,
            @RequestParam(value="modName",required=false,defaultValue = "") String modName,
            @RequestParam(value="costNum",required=false,defaultValue = "") String costNum,
            @RequestParam(value="canNum",required=false,defaultValue = "") String canNum,
            @RequestParam(value="type",required=true) int type
    ) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = userManageService.newModDetail(recordId,belondWeb,modId,modName,costNum,canNum,type);
        resmap.put("info",info);
        long post=System.currentTimeMillis();
        System.out.println("删除模块详情接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
