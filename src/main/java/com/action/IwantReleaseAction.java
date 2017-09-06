package com.action;


import com.service.IwantReleaseService;
import com.service.accountListService;
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

@RestController
@Api(value = "accountListAction", description = "账号交易页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("iwantRelease")
public class IwantReleaseAction {
    @Autowired
    IwantReleaseService iwantReleaseService;

    @ApiOperation(value="我要举报", notes="保存",produces = "application/json")
    @RequestMapping(value="saveWyjbInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getAccountListAction(
            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
            @RequestParam(value="cheatType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="tixin",required=false,defaultValue ="") String tixin,
            @RequestParam(value="roleName",required=false,defaultValue ="") String roleName,
            @RequestParam(value="cheatIntro",required=false,defaultValue="") String cheatIntro,
            @RequestParam(value="cheatInfo",required=false,defaultValue ="") String cheatInfo,
            @RequestParam(value="pageUrl",required=false,defaultValue ="") String pageUrl
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveWyjbInfo(userId,tradeType,belongQf,tixin,roleName,cheatIntro,cheatInfo,pageUrl);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="外观交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveWgjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveWgjyInfoAction(
            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
            @RequestParam(value="cheatType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="viewName",required=false,defaultValue ="") String viewName,
            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveWgjyInfo(userId,tradeType,belongQf,viewName,priceNum,favorInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="道具交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveDjjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveDjjyInfoAction(
            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
            @RequestParam(value="tradeType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="propName",required=false,defaultValue ="") String propName,
            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveDjjyInfo(userId,tradeType,belongQf,propName,priceNum,favorInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="金币交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveJbjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveDjjyInfoAction(
            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
            @RequestParam(value="tradeType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="goldTotal",required=false,defaultValue ="") int goldTotal,
            @RequestParam(value="unitPrice",required=false,defaultValue ="") int unitPrice,
            @RequestParam(value="ifSplit",required=false,defaultValue="") int ifSplit,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveJbjyInfo(userId,tradeType,belongQf,goldTotal,unitPrice,ifSplit,favorInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="代练代打", notes="保存",produces = "application/json")
    @RequestMapping(value="saveDlddInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveDlddInfoAction(
            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
            @RequestParam(value="tradeType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveDlddInfo(userId,tradeType,belongQf,favorInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="账号快售快速发布", notes="保存",produces = "application/json")
    @RequestMapping(value="saveZhssInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveZhssInfoAction(
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="tixin",required=false,defaultValue ="") String tixin,
            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
            @RequestParam(value="accoInfo",required=false,defaultValue ="") String accoInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveZhssInfo(belongQf,tixin,priceNum,accoInfo);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="账号收售详细发布", notes="保存",produces = "application/json")
    @RequestMapping(value="saveZhssxxfbInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveZhssxxfbInfoAction(
            @RequestParam(value="belongQf",required=false,defaultValue ="") String BELONG_QF,//涉事区服
            @RequestParam(value="tixin",required=false,defaultValue ="") String TIXIN,//门派体型
            @RequestParam(value="priceNum",required=false,defaultValue ="") String PRICE_NUM,//价格
            @RequestParam(value="mbgjsl",required=false,defaultValue ="") String FACE_NUM,//面部挂件数量
            @RequestParam(value="bbgjsl",required=false,defaultValue ="") String BACK_NUM,//背部挂件数量
            @RequestParam(value="ybgjsl",required=false,defaultValue ="") String WAIST_NUM,//腰部挂件数量
            @RequestParam(value="zjssl",required=false,defaultValue ="") String LEFT_NUM,//左肩饰数量
            @RequestParam(value="yjssl",required=false,defaultValue ="") String RIGHT_NUM,//右肩饰数量
            @RequestParam(value="qtxych",required=false,defaultValue ="") String qtxych,//其它稀有称号???
            @RequestParam(value="zl",required=false,defaultValue ="") String CRED_NUM,//资历
            @RequestParam(value="fbwj",required=false,defaultValue ="") String TOP_NUM,//副本五甲
            @RequestParam(value="xfcz",required=false,defaultValue ="") String CONSUM_NUM,//消费充值
            @RequestParam(value="xfjf",required=false,defaultValue ="") String INTEG_NUM,//消费积分
            @RequestParam(value="yxjb",required=false,defaultValue ="") String GOLD_NUM,//游戏金币
            @RequestParam(value="cwfz",required=false,defaultValue ="") String PET_NUM,//宠物分值
            @RequestParam(value="jhsj",required=false,defaultValue ="") String CREATE_ACCO,//建号时间
            @RequestParam(value="kdsj",required=false,defaultValue ="") String CARD_TIME,//点卡时间
            @RequestParam(value="tbsl",required=false,defaultValue ="") String CURR_NUM,//通宝数量
            @RequestParam(value="en",required=false,defaultValue ="") String TWO_INPUT,//二内
            @RequestParam(value="sn",required=false,defaultValue ="") String THREE_INPUT,//三内
            @RequestParam(value="_95cw",required=false,defaultValue ="") String _95cw,//95橙武
            @RequestParam(value="_90cw",required=false,defaultValue ="") String _90cw,//90橙武
            @RequestParam(value="_80cw",required=false,defaultValue ="") String _80cw,//80橙武
            @RequestParam(value="_70cw",required=false,defaultValue ="") String _70cw,//70橙武
            @RequestParam(value="mptx",required=false,defaultValue ="") String mptx,//门派特效
            @RequestParam(value="_95xj",required=false,defaultValue ="") String XUANJIN_95,//95玄晶
            @RequestParam(value="_95xt",required=false,defaultValue ="") String XIAOTIE_95,//95小铁
            @RequestParam(value="_90xj",required=false,defaultValue ="") String XUANJIN_90,//90玄晶
            @RequestParam(value="_90xt",required=false,defaultValue ="") String XIAOTIE_90,//90小铁
            @RequestParam(value="_80xj",required=false,defaultValue ="") String XUANJIN_80,//80玄晶
            @RequestParam(value="_80xt",required=false,defaultValue ="") String XIAOTIE_80,//80小铁
            @RequestParam(value="_70xj",required=false,defaultValue ="") String XUANJIN_70,//70玄晶
            @RequestParam(value="_70xt",required=false,defaultValue ="") String XIAOTIE_70,//70小铁
            @RequestParam(value="pvpnz",required=false,defaultValue ="") String PVP_HPS,//PVP-奶装
            @RequestParam(value="pvenz",required=false,defaultValue ="") String PVE_HPS,//PVE-奶装
            @RequestParam(value="pvetz",required=false,defaultValue ="") String PVP_T,//PVE-T装
            @RequestParam(value="pvpdpsngz",required=false,defaultValue ="") String PVP_IN,//PVP-DPS-内功装
            @RequestParam(value="pvedpsngz",required=false,defaultValue ="") String PVE_IN,//PVE-DPS-内功装
            @RequestParam(value="pvpdpswgz",required=false,defaultValue ="") String PVP_OUT,//PVP-DPS-外功装
            @RequestParam(value="pvedpswgz",required=false,defaultValue ="") String PVE_OUT,//PVE-DPS-外功装
            @RequestParam(value="bcsm",required=false,defaultValue ="") String OTHER_EXPLAIN//补充说明
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = iwantReleaseService.saveZhssxxfbInfo(BELONG_QF,TIXIN,PRICE_NUM,FACE_NUM,BACK_NUM,WAIST_NUM,LEFT_NUM,RIGHT_NUM,qtxych,
                CRED_NUM,TOP_NUM,CONSUM_NUM,INTEG_NUM,GOLD_NUM,PET_NUM,CREATE_ACCO,CARD_TIME,CURR_NUM,TWO_INPUT,THREE_INPUT,_95cw,_90cw,_80cw,_70cw,
                mptx,XUANJIN_95,XIAOTIE_95,XUANJIN_90,XIAOTIE_90,XUANJIN_80,XIAOTIE_80,XUANJIN_70,XIAOTIE_70,
                PVP_HPS,PVE_HPS,PVP_T,PVP_IN,PVE_IN,PVP_OUT,PVE_OUT,OTHER_EXPLAIN);

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取特征词", notes="返回checkbox数据",produces = "application/json")
    @RequestMapping(value="getTzc",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getTzcAction(
            @RequestParam(value="type",required=false,defaultValue ="") String type,
            @RequestParam(value="parNum",required=false,defaultValue ="") String parNum,
            @RequestParam(value="cate",required=false,defaultValue ="") String cate
    ){
        /*Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = accountListService.queryAccountListInfo(tradeType,areaSelection,shape,info,pageNumSelected,startNum,endNum);
        Object pageList = accountListService.queryPageListNum();
        resmap.put("datas", dataList);
        resmap.put("pageList", pageList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;*/

        Map<String,Object> resmap=new HashMap<String,Object>();
        Object dataList = iwantReleaseService.getTzc(type, parNum, cate);
        resmap.put("datas", dataList);

        return resmap;
    }

}
