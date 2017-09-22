package com.timer;

import com.action.CheckUrlValidAction;
import com.utils.SegmentDemo;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.utils.checkUrlDao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class pullSegment {
    @Autowired
    SegmentDemo segmentDemo;
    @Autowired
    checkUrlDao checkUrlDao;

    int sumNum = 0;

    CheckUrlValidAction checkUrlValidAction  = new CheckUrlValidAction();

    @Scheduled(cron="0/60 * * * * ?")//每60S执行一次
    public void pullSegmentWords()  {
        segmentDemo.test("");
    }

    @Scheduled(cron="0 0 0-23 * * ? ")//每一天执行一次
//    @Scheduled(cron="0/60 * * * * ?")//每10S执行一次
    public void checkUrlIsValid() throws Exception {
        String str = "";
        long pre=System.currentTimeMillis();
        List<Map<String, Object>> list12 = checkUrlDao.queryUrlIsValid("c_post_bar_12");
        List<Map<String, Object>> list13 = checkUrlDao.queryUrlIsValid("c_post_bar_13");
        List<Map<String, Object>> list15 = checkUrlDao.queryUrlIsValid("c_post_bar_15");
        checkAndPatch(list12,"c_post_bar_12");
        checkAndPatch(list13,"c_post_bar_13");
        checkAndPatch(list15,"c_post_bar_15");
        long post=System.currentTimeMillis();
        System.out.println("处理无效URL所用时间（单位：毫秒）："+ (post-pre));
    }

    public void checkAndPatch(List<Map<String, Object>> list,String tableName) throws Exception {
        for(int i=0;i<list.size();i++) {
            Map<String, Object> map = list.get(i);
            if(map.containsKey("PAGE_URL")) {
                String url = "";
                try {
                     url = map.get("PAGE_URL").toString();
                }catch (Exception e){

                }
                String id = map.get("FAVOR_ID").toString();
                if (!"".equals(url)) {
                    int checkFlag = checkUrlValidAction.checkUrl(url);
                    if (checkFlag == 1) {
                        System.out.println(url);
                        checkUrlDao.del(id,tableName);
                    }
                }
            }
            sumNum++;
            System.out.println(sumNum);
        }
    }
}
