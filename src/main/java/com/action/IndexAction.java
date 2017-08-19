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
import com.service.IndexService;

@RestController
@Api(value = "indexAction", description = "首页账号出售求购接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("index")
public class IndexAction {
    @Autowired
    IndexService indexService;

    @ApiOperation(value="获取首页出售求购信息", notes="默认返回根据发布时间排倒序的最近十条数据",produces = "application/json")
    @RequestMapping(value="index",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getindexInfo(
            /***
             * @Param:tradeType 2:“账号出售页面”或1:“账号求购页面”
             */
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList1 = indexService.queryIndexInfo(2);
        Object dataList2 = indexService.queryIndexInfo(1);
        Object dataList3 = indexService.queryIndex2Info(2);
        Object dataList4 = indexService.queryIndex2Info(1);
        Object dataList5 = indexService.queryIndex3Info(2);
        Object dataList6 = indexService.queryIndex3Info(1);
        resmap.put("datas1", dataList1);
        resmap.put("datas2", dataList2);
        resmap.put("datas3", dataList3);
        resmap.put("datas4", dataList4);
        resmap.put("datas5", dataList5);
        resmap.put("datas6", dataList6);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询首页出售求购接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

}
