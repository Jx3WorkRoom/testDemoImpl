package com.action;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUrlValidAction {
    public int  checkUrl(String urlStr) throws Exception {
        Map<String,Object> resmap=new HashMap<String,Object>();
        String detail = getOneHtml(urlStr);
        String  info = getTitle(detail);
        if(info.indexOf("404")>-1) {
            return 1;
        }else{
            return 0;
        }
    }

    /**
     *
     * @param s
     * @return 获得网页标题
     */
    public String getTitle(final String s)
    {
        String regex;
        String title = "";
        final List<String> list = new ArrayList<String>();
        regex = "<title>.*?</title>";
        final Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
        final Matcher ma = pa.matcher(s);
        while (ma.find())
        {
            list.add(ma.group());
        }
        for (int i = 0; i < list.size(); i++)
        {
            title = title + list.get(i);
        }
        return outTag(title);
    }

    /**
     *
     * @param s
     * @return 去掉标记
     */
    public String outTag(final String s)
    {
        return s.replaceAll("<.*?>", "");
    }

    public static String getOneHtml(String urlString)throws Exception{
        InputStreamReader in = new InputStreamReader(new URL(urlString).openStream());
        // read contents into string buffer
        StringBuilder input = new StringBuilder();
        int ch;
        while ((ch = in.read()) != -1) input.append((char) ch);
        //System.out.println(input);
        return input.toString();
    }

}
