package com.action;

import com.utils.SegmentDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("test")
public class testAction {
    @Autowired
    SegmentDemo segmentDemo;

    @RequestMapping(value="test",method = RequestMethod.GET)
    public void test(
            @RequestParam(value="str") String str
    ) {
        Map<String,Set<String>> resMap = segmentDemo.test(str);
    }
}
