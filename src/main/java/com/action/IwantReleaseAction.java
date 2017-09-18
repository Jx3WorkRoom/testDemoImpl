package com.action;


import com.service.IwantReleaseService;
import com.service.accountListService;
import com.utils.MyDateTimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@Api(value = "accountListAction", description = "账号交易页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("iwantRelease")
public class IwantReleaseAction {
    @Autowired
    accountListService accountListService;
    @Autowired
    IwantReleaseService iwantReleaseService;

    List<String> imgList= new LinkedList<>();

    @ApiOperation(value="我要举报", notes="保存",produces = "application/json")
    @RequestMapping(value="saveWyjbInfo",method = RequestMethod.POST)
    @Produces("application/json")
    public Map<String,Object> getAccountListAction(
//            @RequestParam(value="operate",required=false,defaultValue = "") String operate,
//            @RequestParam(value="favorId",required=false,defaultValue = "") int favorId,
//            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
//            @RequestParam(value="cheatType",required=false,defaultValue = "") int tradeType,
//            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
//            @RequestParam(value="tixin",required=false,defaultValue ="") String tixin,
//            @RequestParam(value="roleName",required=false,defaultValue ="") String roleName,
//            @RequestParam(value="cheatIntro",required=false,defaultValue="") String cheatIntro,
//            @RequestParam(value="cheatInfo",required=false,defaultValue ="") String cheatInfo,
//            @RequestParam(value="pageUrl",required=false,defaultValue ="") String pageUrl

            HttpServletRequest request,HttpServletResponse response
    )   {
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        try{
            String saveFileName = "";
//            System.out.println(request.getParameter("userId"));
//            System.out.println(request.getParameter("favorId"));
//            System.out.println(request.getParameter("favorId"));
//            System.out.println(java.net.URLDecoder.decode(belongQf,"utf-8"));
//            System.out.println(request.getParameter("tixin"));
//            System.out.println(request.getParameter("roleName"));
//            System.out.println(request.getParameter("cheatIntro"));
//            System.out.println(request.getParameter("cheatInfo"));
//            System.out.println(request.getParameter("cheatInfo"));

            System.out.println("收到图片!");
            MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;

            Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
            String time =new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");;
            String upaloadUrl = "D:\\JX3JZ\\"+time+"/";
//            upaloadUrl = "C:\\JX3JZ\\"+time+"/";
            String pathUrl = "/JX3JZ/"+time+"/";
            File dir = new File(upaloadUrl);
//            System.out.println(upaloadUrl);
            if(!dir.exists())//目录不存在则创建
                dir.mkdirs();

            System.out.println(files.values().size());

            for(MultipartFile file :files.values()){
//                String fileName=file.getOriginalFilename();
//                String saveFileName = recordId;
                String fileName=file.getOriginalFilename();
                int pixindex = fileName.lastIndexOf(".");
                String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
                saveFileName = recordId+fileName.substring(pixindex,fileName.length());//.substring(fileName.lastIndexOf(".").fileName.length());
                System.out.println("saveFileName: "+saveFileName);
                File tagetFile = new File(upaloadUrl,saveFileName);//创建文件对象
                if(!tagetFile.exists()){//文件名不存在 则新建文件，并将文件复制到新建文件中
                    try {
                        file.transferTo(tagetFile);
                        imgList.add(saveFileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                        imgList.add(saveFileName);
                    }
                }
            }
            String filePath = pathUrl+saveFileName;

            System.out.println("接收完毕");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            int imgNum = Integer.parseInt(request.getParameter("imgNum"));
            while(imgList.size()==imgNum) {
                String returnVal = "";
                String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
                String operate = request.getParameter("operate");
                String userId = request.getParameter("userId");
                int tradeType = request.getParameter("cheatType") == null ? 1 : Integer.parseInt(request.getParameter("cheatType"));
                String belongQf = request.getParameter("belongQf");
                String tixin = request.getParameter("tixin");
                String roleName = request.getParameter("roleName");
                String cheatIntro = request.getParameter("cheatIntro");
                String cheatInfo = request.getParameter("cheatInfo");
                String pageUrl = request.getParameter("pageUrl");
                if (operate.equals("save")) {
                    returnVal = iwantReleaseService.saveWyjbInfo(recordId, userId, tradeType, belongQf, tixin, roleName, cheatIntro, cheatInfo, pageUrl, imgList);
                } else {
                    int favorId = request.getParameter("favorId") == null ? -1 : Integer.parseInt(request.getParameter("favorId"));
                    returnVal = iwantReleaseService.upeditWyjbInfo(favorId, userId, tradeType, belongQf, tixin, roleName, cheatIntro, cheatInfo, pageUrl, imgList);
                }
                long post = System.currentTimeMillis();
                System.out.println("查询账号交易接口执行时间（单位：毫秒）：" + (post - pre));
                imgList.clear();
                return resmap;
            }
        }
        return null;
    }

    @ApiOperation(value="外观交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveWgjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveWgjyInfoAction(
            @RequestParam(value="operate",required=false,defaultValue = "") String operate,
            @RequestParam(value="favorId",required=false,defaultValue = "") int favorId,
            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
            @RequestParam(value="cheatType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="viewName",required=false,defaultValue ="") String viewName,
            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();

        String returnVal = "";
        if(operate.equals("save")){
            returnVal = iwantReleaseService.saveWgjyInfo(userId,tradeType,belongQf,viewName,priceNum,favorInfo);
        }else {
            returnVal = iwantReleaseService.upeditWgjyInfo(favorId, userId,tradeType,belongQf,viewName,priceNum,favorInfo);
        }
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="道具交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveDjjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveDjjyInfoAction(
            @RequestParam(value="operate",required=false,defaultValue = "") String operate,
            @RequestParam(value="favorId",required=false,defaultValue = "") int favorId,
            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
            @RequestParam(value="tradeType",required=false,defaultValue = "") int tradeType,
            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
            @RequestParam(value="propName",required=false,defaultValue ="") String propName,
            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = "";
        if(operate.equals("save")){
            returnVal = iwantReleaseService.saveDjjyInfo(userId,tradeType,belongQf,propName,priceNum,favorInfo);
        }else {
            returnVal = iwantReleaseService.upeditDjjyInfo(favorId, userId,tradeType,belongQf,propName,priceNum,favorInfo);
        }

        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="金币交易", notes="保存",produces = "application/json")
    @RequestMapping(value="saveJbjyInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveDjjyInfoAction(
            @RequestParam(value="operate",required=false,defaultValue = "") String operate,
            @RequestParam(value="favorId",required=false,defaultValue = "") int favorId,
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
        String returnVal = "";
        if(operate.equals("save")){
            returnVal = iwantReleaseService.saveJbjyInfo(userId,tradeType,belongQf,goldTotal,unitPrice,ifSplit,favorInfo);
        }else {
            returnVal = iwantReleaseService.updateJbjyInfo(favorId,userId,tradeType,belongQf,goldTotal,unitPrice,ifSplit,favorInfo);
        }
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }


    @ApiOperation(value="代练代打", notes="保存",produces = "application/json")
    @RequestMapping(value="saveDlddInfo",method = RequestMethod.POST)
    @Produces("application/json")
    public Map<String,Object> saveDlddInfoAction(
//            @RequestParam(value="operate",required=false,defaultValue = "") String operate,
//            @RequestParam(value="favorId",required=false,defaultValue = "") int favorId,
//            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
//            @RequestParam(value="needtype",required=false,defaultValue = "") int needtype,
//            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
//            @RequestParam(value="favorInfo",required=false,defaultValue="") String favorInfo
            HttpServletRequest request,HttpServletResponse response
    ){
        try{
            Map<String,Object> resmap=new HashMap<String,Object>();
            long pre=System.currentTimeMillis();
            String returnVal = "";


            String saveFileName = "";

            String operate = request.getParameter("operate");
            String userId = request.getParameter("userId");
            int needtype = request.getParameter("needtype")==null?-1:Integer.parseInt(request.getParameter("needtype"));
            String belongQf = request.getParameter("belongQf");
            String favorInfo = request.getParameter("favorInfo");
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;

            System.out.println(request.getParameter("operate"));
            System.out.println(request.getParameter("userId"));
            System.out.println(request.getParameter("needtype"));
            System.out.println(request.getParameter("belongQf"));
            System.out.println(request.getParameter("favorInfo"));


            System.out.println("收到图片!");
            MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;

            Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
            String time =new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");;
            String upaloadUrl = "D:\\JX3JZ\\"+time+"/";
            String pathUrl = "/JX3JZ/"+time+"/";
            File dir = new File(upaloadUrl);
//            System.out.println(upaloadUrl);
            if(!dir.exists())//目录不存在则创建
                dir.mkdirs();

            System.out.println(files.values().size());

            for(MultipartFile file :files.values()){
//                String fileName=file.getOriginalFilename();
//                String saveFileName = recordId;

                String fileName=file.getOriginalFilename();
                int pixindex = fileName.lastIndexOf(".");

                saveFileName = recordId+fileName.substring(pixindex,fileName.length());//.substring(fileName.lastIndexOf(".").fileName.length());
                System.out.println("saveFileName: "+saveFileName);

                File tagetFile = new File(upaloadUrl,saveFileName);//创建文件对象
                if(!tagetFile.exists()){//文件名不存在 则新建文件，并将文件复制到新建文件中
                    try {
                        file.transferTo(tagetFile);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String filePath = pathUrl+saveFileName;

            System.out.println("接收完毕");


            if(operate.equals("save")){
                returnVal = iwantReleaseService.saveDlddInfo(userId,needtype,belongQf,favorInfo,filePath);
            }else {
                int favorId = request.getParameter("favorId")==null?-1:Integer.parseInt(request.getParameter("favorId"));
                returnVal = iwantReleaseService.updateDlddInfo(favorId,userId,needtype,belongQf,favorInfo,filePath);
            }
            long post=System.currentTimeMillis();
            System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
            return resmap;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="账号快售快速发布", notes="保存",produces = "application/json")
    @RequestMapping(value="saveZhssInfo",method = RequestMethod.POST)
    @Produces("application/json")
    public Map<String,Object> saveZhssInfoAction(
//            @RequestParam(value="operate",required=false,defaultValue = "") String operate,
//            @RequestParam(value="favorId",required=false,defaultValue = "") int favorId,
//            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
//            @RequestParam(value="tradeType",required=false,defaultValue = "") int tradeType,
//            @RequestParam(value="belongQf",required=false,defaultValue ="") String belongQf,
//            @RequestParam(value="tixin",required=false,defaultValue ="") String tixin,
//            @RequestParam(value="priceNum",required=false,defaultValue ="") int priceNum,
//            @RequestParam(value="accoInfo",required=false,defaultValue ="") String accoInfo
            HttpServletRequest request,HttpServletResponse response
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        String returnVal = "";


        String saveFileName = "";

        String operate = request.getParameter("operate");
        String userId = request.getParameter("userId");
        int tradeType = request.getParameter("tradeType")==null?-1:Integer.parseInt(request.getParameter("tradeType"));
        String belongQf = request.getParameter("belongQf");
        String tixin = request.getParameter("tixin");
        int priceNum = request.getParameter("priceNum")==null?-1:Integer.parseInt(request.getParameter("priceNum"));    //价格
        String accoInfo = request.getParameter("accoInfo");
        String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;

        System.out.println(request.getParameter("operate"));
        System.out.println(request.getParameter("userId"));
        System.out.println(request.getParameter("needtype"));
        System.out.println(request.getParameter("belongQf"));
        System.out.println(request.getParameter("favorInfo"));


        System.out.println("收到图片!");
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;

        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
        String time =new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");;
        String upaloadUrl = "D:\\JX3JZ\\"+time+"/";
        String pathUrl = "/JX3JZ/"+time+"/";
        File dir = new File(upaloadUrl);
//            System.out.println(upaloadUrl);
        if(!dir.exists())//目录不存在则创建
            dir.mkdirs();

        System.out.println(files.values().size());

        for(MultipartFile file :files.values()){
//                String fileName=file.getOriginalFilename();
//                String saveFileName = recordId;

            String fileName=file.getOriginalFilename();
            int pixindex = fileName.lastIndexOf(".");

            saveFileName = recordId+fileName.substring(pixindex,fileName.length());//.substring(fileName.lastIndexOf(".").fileName.length());
            System.out.println("saveFileName: "+saveFileName);

            File tagetFile = new File(upaloadUrl,saveFileName);//创建文件对象
            if(!tagetFile.exists()){//文件名不存在 则新建文件，并将文件复制到新建文件中
                try {
                    file.transferTo(tagetFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String filePath = pathUrl+saveFileName;

        System.out.println("接收完毕");


        if(operate.equals("save")){
            returnVal = iwantReleaseService.saveZhssInfo(userId,tradeType,belongQf,tixin,priceNum,accoInfo,filePath);
        }else {
            int favorId = request.getParameter("favorId")==null?-1:Integer.parseInt(request.getParameter("favorId"));
            returnVal = iwantReleaseService.updateZhssInfo(favorId,userId,tradeType,belongQf,tixin,priceNum,accoInfo,filePath);
        }
        long post=System.currentTimeMillis();
        System.out.println("查询账号交易接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="账号收售详细发布", notes="保存",produces = "application/json")
    @RequestMapping(value="saveZhssxxfbInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> saveZhssxxfbInfoAction(
            @RequestParam(value="userId",required=false,defaultValue = "") String userId,
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
        int tradeType = 1;
        String returnVal = iwantReleaseService.saveZhssxxfbInfo(userId,tradeType,BELONG_QF,TIXIN,PRICE_NUM,FACE_NUM,BACK_NUM,WAIST_NUM,LEFT_NUM,RIGHT_NUM,qtxych,
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



//    @ApiOperation(value="获取道具名下拉框填充信息", notes="",produces = "application/json")
//    @RequestMapping(value="selectionList",method = RequestMethod.GET)
//    @Produces("application/json")
//    public Map<String,Object> getAccountListSelectionAction(
//            @RequestParam(value="type",required=false,defaultValue ="10") String type
//    ){
//        Map<String,Object> resmap=new HashMap<String,Object>();
//        long pre=System.currentTimeMillis();
//        Object SelectionList = accountListService.querySelectionListInfo();
//        Object tixinList = iwantReleaseService.queryTixinListInfo(type);
//        resmap.put("selecttions", SelectionList);
//        resmap.put("resultList", tixinList);
//        resmap.put("success", true);
//        long post=System.currentTimeMillis();
//        System.out.println("查询账号交易页面搜索框填充信息接口执行时间（单位：毫秒）："+ (post-pre));
//        return resmap;
//    }





    @ApiOperation(value="我要举报", notes="编辑",produces = "application/json")
    @RequestMapping(value="reportListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editReportAction(
            @RequestParam(value="mainId",required=false,defaultValue = "") String mainId
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();

        Object SelectionList = accountListService.querySelectionListInfo();
        Object tixinList = accountListService.queryTixinListInfo();
        Object dataList = iwantReleaseService.getReport(mainId);
        resmap.put("selecttions", SelectionList);
        resmap.put("tixinList", tixinList);
        resmap.put("info", dataList);
        resmap.put("success", true);

        long post=System.currentTimeMillis();
        System.out.println("我要举报编辑接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="外观交易", notes="编辑",produces = "application/json")
    @RequestMapping(value="appearanceTransactionListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editAppearanceTransactionAction(
            @RequestParam(value="type",required=false,defaultValue ="10") String type,
            @RequestParam(value="mainId",required=false,defaultValue = "") String mainId
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();

        Object SelectionList = accountListService.querySelectionListInfo();
        Object tixinList = iwantReleaseService.queryTixinListInfo(type);//外观名
        Object dataList = iwantReleaseService.getAppearanceTransaction(mainId);
        resmap.put("selecttions", SelectionList);
        resmap.put("resultList", tixinList);
        resmap.put("info", dataList);
        resmap.put("success", true);

        long post=System.currentTimeMillis();
        System.out.println("我要举报编辑接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="道具交易", notes="编辑",produces = "application/json")
    @RequestMapping(value="propTransactionListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editPropTransactionAction(
            @RequestParam(value="type",required=false,defaultValue ="10") String type,
            @RequestParam(value="mainId",required=false,defaultValue = "") String mainId
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();

        Object SelectionList = accountListService.querySelectionListInfo();
        Object tixinList = iwantReleaseService.queryTixinListInfo(type);//道具名
        Object dataList = iwantReleaseService.getPropTransaction(mainId);
        resmap.put("selecttions", SelectionList);
        resmap.put("resultList", tixinList);
        resmap.put("info", dataList);
        resmap.put("success", true);

        long post=System.currentTimeMillis();
        System.out.println("我要举报编辑接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="金币交易", notes="编辑",produces = "application/json")
    @RequestMapping(value="accountTransactionListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editAccountTransactionAction(
            @RequestParam(value="mainId",required=false,defaultValue = "") String mainId
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object SelectionList = accountListService.querySelectionListInfo();
        Object tixinList = accountListService.queryTixinListInfo();
        Object dataList = iwantReleaseService.getAccountTransaction(mainId);
        resmap.put("selecttions", SelectionList);
        resmap.put("resultList", tixinList);
        resmap.put("info", dataList);
        resmap.put("success", true);

        long post=System.currentTimeMillis();
        System.out.println("我要举报编辑接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="代练交易", notes="编辑",produces = "application/json")
    @RequestMapping(value="accountExchangeListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editAccountExchangeAction(
            @RequestParam(value="mainId",required=false,defaultValue = "") String mainId
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();

        Object SelectionList = accountListService.querySelectionListInfo();
        Object tixinList = accountListService.queryTixinListInfo();
        Object dataList = iwantReleaseService.getAccountExchange(mainId);
        resmap.put("selecttions", SelectionList);
        resmap.put("resultList", tixinList);
        resmap.put("info", dataList);
        resmap.put("success", true);

        long post=System.currentTimeMillis();
        System.out.println("我要举报编辑接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="账号收售快速发布", notes="编辑",produces = "application/json")
    @RequestMapping(value="quickReleaseListSelection",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editQuickReleaseAction(
            @RequestParam(value="type",required=false,defaultValue ="10") String type,
            @RequestParam(value="mainId",required=false,defaultValue = "") String mainId
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();

        Object SelectionList = accountListService.querySelectionListInfo();
        Object tixinList = accountListService.queryTixinListInfo();//体型
        Object dataList = iwantReleaseService.getQuickRelease(mainId);
        resmap.put("selecttions", SelectionList);
        resmap.put("tixinList", tixinList);
        resmap.put("info", dataList);
        resmap.put("success", true);

        long post=System.currentTimeMillis();
        System.out.println("我要举报编辑接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

}
