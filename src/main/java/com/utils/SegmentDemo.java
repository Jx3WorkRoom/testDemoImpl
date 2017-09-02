package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Repository;

@Repository
public class SegmentDemo {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public SegmentDemo(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    static HashMap<String, String> informNameSet = new HashMap<String, String>();
    static HashSet<String> userWordSet = Sets.newHashSet();

    public Map<String,Set<String>> test(String str) {

        String sql = "select keyword_name, keyword_big_name, keyword_name_g from b_post_bar_big";

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                String keyword_name = rs.getString(1);
                String keyword_big_name = rs.getString(2);
                String keyword_name_g = rs.getString(3);

                informNameSet.put(keyword_name, keyword_name_g);
                userWordSet.add(keyword_name + "\t" + keyword_big_name + "\t10000");
            }
        });

        for (int i = 1; i < 10; i++) {
            userWordSet.add(i + "k" + "\tuser_price\t10000");
            userWordSet.add(i + "K" + "\tuser_price\t10000");

            for (int j = 1; j < 100; j++) {
                userWordSet.add(i + "k" + j + "\tuser_price\t10000");
                userWordSet.add(i + "K" + j + "\tuser_price\t10000");
            }
        }
        System.out.println(informNameSet);

        Forest forest = new Forest();
        for (String userWord : userWordSet) {
            Library.insertWord(forest, userWord);
        }

        DicLibrary.put(DicLibrary.DEFAULT, DicLibrary.DEFAULT, forest);

        String[] jinfa = { "一代", "二代", "三代", "猴", "狐", "鸡", "蝶", "苏", "喵", "高考", "川普", "叽", "泡面", "女神" };
        Set<String> jinfaSet = new HashSet<String>(Arrays.asList(jinfa));
        System.out.println(jinfaSet);

        String[] hongfa = { "羊", "猴", "鸡", "4", "5", "6", "7", "一代", "二代", "叽", "四", "五", "六", "七", "四周", "五周", "六周	",
                "七周" };
        Set<String> hongfaSet = new HashSet<String>(Arrays.asList(hongfa));

        String[] hezi = { "元宵	", "中秋", "中秋蓝", "中秋粉", "七夕	", "红", "黑", "蓝", "青" };
        Set<String> heziSet = new HashSet<String>(Arrays.asList(hezi));

        String data = str;
        Result segResult = (Result) DicAnalysis.parse(data.replaceAll("[\\pP\\p{Punct}]", ""));
        System.out.println(segResult.toString());

        Set<String> qufu = new HashSet<String>();
        Set<String> tixin = new HashSet<String>();
        Set<String> price = new HashSet<String>();
        Set<String> menpai = new HashSet<String>();
        Set<String> xinfa = new HashSet<String>();
        Set<String> title = new HashSet<String>();
        Set<String> waiguan = new HashSet<String>();
        Set<String> horse = new HashSet<String>();
        Set<String> arm = new HashSet<String>();
        Set<String> stra = new HashSet<String>();
        Set<String> pend = new HashSet<String>();
        Set<String> qiza = new HashSet<String>();
        Set<String> zhanghao = new HashSet<String>();
        Set<String> daoju = new HashSet<String>();
        Set<String> dailian = new HashSet<String>();
        Set<String> jinbi = new HashSet<String>();
        // 存在门派体型词
        int mptxCount = 0;
        // int price_num = 0;
        int i = 0;
        for (Term term : segResult) {

            if (term.getNatureStr().equalsIgnoreCase("user_menpi")) {
                menpai.add(informNameSet.get(informNameSet.get(term.getName())));
            }
            if (term.getNatureStr().equalsIgnoreCase("user_mptixing")) {
                mptxCount++;
                tixin.add(informNameSet.get(term.getName()));
            }
            if (term.getNatureStr().equalsIgnoreCase("user_xinfa")) {
                xinfa.add(informNameSet.get(term.getName()));
            }
            if (term.getNatureStr().equalsIgnoreCase("user_chenghao")) {
                title.add(informNameSet.get(term.getName()));
            }
            if (term.getNatureStr().equalsIgnoreCase("user_waiguan")) {
                waiguan.add(informNameSet.get(term.getName()));
                if (term.getName().endsWith("金")) {
                    for (int j = 1; j < 100; j++) {
                        int index = i - j;
                        if (index > 0) {
                            String nature = segResult.get((i - j)).getNatureStr();
                            String name = segResult.get((i - j)).getName();
                            if (!nature.startsWith("user_")) {
                                if (jinfaSet.contains(name)) {
                                    waiguan.add(informNameSet.get(name + "金"));
                                }
                            } else {
                                break;
                            }
                        }

                    }
                }

                if (term.getName().endsWith("红")) {
                    for (int j = 1; j < 100; j++) {
                        int index = i - j;
                        if (index > 0) {
                            String nature = segResult.get((i - j)).getNatureStr();
                            String name = segResult.get((i - j)).getName();
                            if (!nature.startsWith("user_")) {
                                if (hongfaSet.contains(name)) {
                                    waiguan.add(informNameSet.get(name + "红"));
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }

                if (term.getName().contains("盒")) {
                    for (int j = 1; j < 100; j++) {
                        int index = i - j;
                        if (index > 0) {
                            String nature = segResult.get((i - j)).getNatureStr();
                            String name = segResult.get((i - j)).getName();
                            if (!nature.startsWith("user_")) {
                                if (heziSet.contains(name)) {
                                    waiguan.add(informNameSet.get(name + "盒子"));
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            if (term.getNatureStr().equalsIgnoreCase("user_maqiqu")) {
                horse.add(informNameSet.get(term.getName()));
            }
            if (term.getNatureStr().equalsIgnoreCase("user_wuqi")) {
                arm.add(informNameSet.get(term.getName()));
            }
            if (term.getNatureStr().equalsIgnoreCase("user_qiyu")) {
                stra.add(informNameSet.get(term.getName()));
            }
            if (term.getNatureStr().equalsIgnoreCase("user_guajian")) {
                pend.add(informNameSet.get(term.getName()));
            }
            if (term.getNatureStr().equalsIgnoreCase("user_qufu")) {
                qufu.add(informNameSet.get(term.getName()));
            }

            if (term.getNatureStr().equalsIgnoreCase("user_price")) {
                price.add(term.getName());
            }

            if (term.getNatureStr().equalsIgnoreCase("user_qiza")) {
                qiza.add(term.getName());
            }

            if (term.getNatureStr().equalsIgnoreCase("user_xingxi")) {
                zhanghao.add(informNameSet.get(term.getName()));
            }

            if (term.getNatureStr().equalsIgnoreCase("user_daoju")) {
                daoju.add(informNameSet.get(term.getName()));
            }

            if (term.getNatureStr().equalsIgnoreCase("user_dailian")) {
                dailian.add(informNameSet.get(term.getName()));
            }

            if (term.getNatureStr().equalsIgnoreCase("user_jinbi")) {
                jinbi.add(informNameSet.get(term.getName()));
            }

            i++;
        }
        Map<String,Set<String>> resMap = new HashMap<String,Set<String>>();
        System.out.println(qufu.toString());
        if(qufu.size()>0) {
            resMap.put("qufu", qufu);
        }
        System.out.println(tixin.toString());
        if(qufu.size()>0) {
            resMap.put("tixin", tixin);
        }
        System.out.println(price.toString());
        if(qufu.size()>0) {
            resMap.put("price", price);
        }
        System.out.println(menpai.toString());
        if(qufu.size()>0) {
            resMap.put("menpai", menpai);
        }
        System.out.println(xinfa.toString());
        if(qufu.size()>0) {
            resMap.put("xinfa", xinfa);
        }
        System.out.println(title.toString());
        if(qufu.size()>0) {
            resMap.put("title", title);
        }
        System.out.println(waiguan.toString());
        if(qufu.size()>0) {
            resMap.put("waiguan", waiguan);
        }
        System.out.println(horse.toString());
        if(qufu.size()>0) {
            resMap.put("horse", horse);
        }
        System.out.println(arm.toString());
        if(qufu.size()>0) {
            resMap.put("arm", arm);
        }
        System.out.println(stra.toString());
        if(qufu.size()>0) {
            resMap.put("stra", stra);
        }
        System.out.println(pend.toString());
        if(qufu.size()>0) {
            resMap.put("pend", pend);
        }
        System.out.println(qiza.toString());
        if(qufu.size()>0) {
            resMap.put("qiza", qiza);
        }
        System.out.println(zhanghao.toString());
        if(qufu.size()>0) {
            resMap.put("zhanghao", zhanghao);
        }
        System.out.println(daoju.toString());
        if(qufu.size()>0) {
            resMap.put("daoju", daoju);
        }
        System.out.println(dailian.toString());
        if(qufu.size()>0) {
            resMap.put("dailian", dailian);
        }
        System.out.println(jinbi.toString());
        if(qufu.size()>0) {
            resMap.put("jinbi", jinbi);
        }
        return resMap;
    }
}
