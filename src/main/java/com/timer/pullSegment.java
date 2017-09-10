package com.timer;

import com.utils.SegmentDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class pullSegment {
    @Autowired
    SegmentDemo segmentDemo;

    @Scheduled(cron="0/60 * * * * ?")//每10S执行一次
    public void pullSegmentWords()  {
        segmentDemo.test("");
    }
}
