package com.timer;

import com.action.CheckUrlValidAction;
import com.utils.*;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class pullSegment {
    @Autowired
    SegmentDemo segmentDemo;

    @Scheduled(cron="0 0 2/12 * * ?")//每12个小时执行一次
    public void pullSegmentWords()  {
        segmentDemo.test("");
    }

}
