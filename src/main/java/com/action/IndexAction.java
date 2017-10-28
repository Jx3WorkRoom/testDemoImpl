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

    @ApiOperation(value="查询系统日志", notes="查询系统日志",produces = "application/json")
    @RequestMapping(value="getSysLog",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getindexInfo(
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = indexService.queryIndexInfo();
        resmap.put("datas", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询系统日志接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

}
