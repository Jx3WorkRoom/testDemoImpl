package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.beans.binding.ObjectExpression;
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

import com.utils.Md5Util2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCTestDemo2 {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public JDBCTestDemo2(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

	static HashMap<String, String> informNameSet = new HashMap<String, String>();
	static LinkedHashSet <String> userWordSet = Sets.newLinkedHashSet ();


	public void run1() throws Exception {
        getUserDefineWord();
        Forest forest = new Forest();
        for (String userWord : userWordSet) {
            Library.insertWord(forest, userWord);
        }
        DicLibrary.put(DicLibrary.DEFAULT, DicLibrary.DEFAULT, forest);
    }

    public void run2() throws Exception {
        getTzInfo();
    }
	/**
	 * 获取用户自定义词
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void getUserDefineWord() throws Exception {
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

		 //收、求、买、蹲、购  转
		 userWordSet.add("收\tuser_trade\t10000");
		 userWordSet.add("求\tuser_trade\t10000");
		 userWordSet.add("买\tuser_trade\t10000");
		 userWordSet.add("蹲\tuser_trade\t10000");
		 userWordSet.add("购\tuser_trade\t10000");
		 userWordSet.add("转\tuser_trade\t10000");

		System.out.println(informNameSet);
	}

	public static void getKk(){
		String testStr="597.【1k4二少】姨妈绿不期流蕴+落英解语粉披风踏秋+1白2成2页拓印6捏脸3武双修2w";
		Result segResult = DicAnalysis.parse(testStr.replaceAll("[\\pP\\p{Punct}]", " "));
		System.out.println(segResult.toString());
	}

	public static void getTzInfo1() {
		//String data = "《8K5—电点秀萝》五六红一代狐猴金蓝公主人面不期中秋红舞步情人枕狐狸毛34成衣10披风喷火小熊猫夜话珠盏秀萝";
		String data = "电一长安出只狐金兰亭2w09装分3w5资历的秀姐，求大佬带走！！<br>            ";
		Result segResult = DicAnalysis.parse(data.replaceAll("[\\pP\\p{Punct}]", " "));
		System.out.println(segResult.toString());
	}

	public void getTzInfo() throws Exception {
        String createTime = Commons.chargeTime;
        if("".equals(createTime)){
            String sql = "select max(CREATETIME) CREATETIME from C_POST_BAR_12 ";
            try {
                Map<String, Object> map = jdbcTemplate.queryForList(sql).get(0);
                if (map.containsKey("CREATETIME")) {
                    createTime = map.get("CREATETIME").toString();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String tzSql = "";
        if("".equals(createTime)){
            tzSql = "select THEME_ID, THEME_NAME, POST_BAR, REPLY_ID, POST_BAR_CLASS, BELONG_FLOOR, PAGE_NUM, REPLY_TIME, REPLY_CONTENT, "
                    + "PAGE_URL from A_POST_BAR_JX3_2 where 1=1 order by CREATETIME desc limit 1000";
        }else{
            tzSql = "select THEME_ID, THEME_NAME, POST_BAR, REPLY_ID, POST_BAR_CLASS, BELONG_FLOOR, PAGE_NUM, REPLY_TIME, REPLY_CONTENT, "
                    + "PAGE_URL from A_POST_BAR_JX3_2 where 1=1 and CREATETIME > '"+createTime+"' order by CREATETIME desc limit 1000";
        }
		// 帖子内容rcn
		String[] jinfa = { "一代", "二代", "三代", "猴", "狐", "鸡", "蝶", "苏", "喵", "高考", "川普", "叽", "泡面", "女神" };
		//List<String> jinfaList = FileUtils.readLines(new File("gold.txt"), Charset.forName("UTF-8"));
		Set<String> jinfaSet = new LinkedHashSet <String>(Arrays.asList(jinfa));
		System.out.println(jinfaSet);

		String[] hongfa = { "羊", "猴", "鸡", "4", "5", "6", "7", "8", "一代", "二代", "叽", "四", "五", "六", "七","八", "四周", "五周", "六周",
				"七周", "八周" };
		Set<String> hongfaSet = new LinkedHashSet <String>(Arrays.asList(hongfa));

		String[] hezi = { "元宵", "中秋", "中秋蓝", "中秋粉", "七夕", "红", "黑", "蓝", "青", "白", "糖", "唐" };
		Set<String> heziSet = new LinkedHashSet <String>(Arrays.asList(hezi));

        jdbcTemplate.query(tzSql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				//Reader inStream = null;
				String data;
				String THEME_ID = rs.getString(1);
				String THEME_NAME = rs.getString(2);
				String POST_BAR = rs.getString(3);
				String REPLY_ID = rs.getString(4);
				int POST_BAR_CLASS = rs.getInt(5);
				int BELONG_FLOOR = rs.getInt(6);
				int PAGE_NUM = rs.getInt(7);
				data = rs.getString(9);
				String PAGE_URL = rs.getString(10);
				int trade_type = 2;
				if (data != null) {
					//System.out.println(data);

					Result segResult = DicAnalysis.parse(data.replaceAll("[\\pP\\p{Punct}]", " "));

					Set<String> qufu = new LinkedHashSet <String>();
					Set<String> tixin = new LinkedHashSet <String>();
					Set<String> price = new LinkedHashSet <String>();
					Set<String> menpai = new LinkedHashSet <String>();
					Set<String> xinfa = new LinkedHashSet <String>();
					Set<String> title = new LinkedHashSet <String>();
					Set<String> waiguan = new LinkedHashSet <String>();
					Set<String> horse = new LinkedHashSet <String>();
					Set<String> arm = new LinkedHashSet <String>();
					Set<String> stra = new LinkedHashSet <String>();
					Set<String> pend = new LinkedHashSet <String>();
					Set<String> qiza = new LinkedHashSet <String>();
					Set<String> zhanghao = new LinkedHashSet <String>();
					Set<String> daoju = new HashSet<String>();
					Set<String> dailian = new LinkedHashSet <String>();
					Set<String> jinbi = new LinkedHashSet <String>();
					// 存在门派体型词
					int mptxCount = 0;
					int wgCount = 0;
					int djCount = 0;
					int dlCount = 0;
					int jbCount = 0;

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
							wgCount++;
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
							djCount++;
							daoju.add(informNameSet.get(term.getName()));
						}

						if (term.getNatureStr().equalsIgnoreCase("user_dailian")) {
							dailian.add(informNameSet.get(term.getName()));
						}

						if (term.getNatureStr().equalsIgnoreCase("user_jinbi")) {
							jinbi.add(informNameSet.get(term.getName()));
						}

						if (term.getNatureStr().equalsIgnoreCase("user_trade")) {
							trade_type = 1;
						}

						i++;
					}

					boolean isContinue = true;

					StringBuffer sb = new StringBuffer();
					sb.append(qufu);
					sb.append(tixin);
					sb.append(waiguan);
//					String sql = "insert into C_POST_BAR_11(RECORD_ID,THEME_ID,THEME_NAME,POST_BAR,POST_ID,POST_BAR_CLASS,BELONG_FLOOR,PAGE_NUM,REPLY_TIME,"
//							+ "REPLY_CONTENT,PAGE_URL,FAVOR_ID,createtime,updatetime) values(UUID(),?,?,?,?,?,?,?,?,?,?, nextval('SEQUENCE_3'),sysdate(),sysdate())";
					//一、抽取欺诈记录出现欺诈词的表中任意一个，则将记录写入欺诈表（POST_BAR_11）
//					if (qiza.size() > 0) {
//						Object[] batchArgs = new Object[10];
//						batchArgs[0] = THEME_ID;
//						batchArgs[1] = THEME_NAME;
//						batchArgs[2] = POST_BAR;
//						batchArgs[3] = REPLY_ID;
//						batchArgs[4] = POST_BAR_CLASS;
//						batchArgs[5] = BELONG_FLOOR;
//						batchArgs[6] = PAGE_NUM;
//						batchArgs[7] = rs.getString(8);
//						batchArgs[8] = data.length() < 1000 ? data : data.substring(0, 1000);
//						batchArgs[9] = PAGE_URL;
//						jdbcTemplate.update(sql, batchArgs);
//						System.out.println("欺诈：" + qiza);
//						isContinue = false;
//					}
					//System.out.println(isContinue);

					String sqlMPTX = "insert into C_POST_BAR_12(RECORD_ID,THEME_ID,THEME_NAME,POST_BAR,POST_ID,POST_BAR_CLASS,BELONG_FLOOR,PAGE_NUM,REPLY_TIME,"
							+ "REPLY_CONTENT,PAGE_URL,BAR_SOUR_TYPE,BELONG_QF,TIXIN,PRICE_NUM,MENPAI_NAME,XINFA_NAME,TITLE_NAME,WAIGUAN_NAME,HORSE_NAME,"
							+ "ARM_NAME,STRA_NAME,PEND_NAME,main_id,TRADE_TYPE,source_type,url_valid,FAVOR_ID,createtime,updatetime) "
							+ "values(UUID(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,nextval('SEQUENCE_3'),sysdate(),sysdate())";
					try {

						List<Object[]> batchArgsList = Lists.newArrayList();

						if (mptxCount > 1) {
							//System.out.println("门派体型词：" + mptxResult);

							LinkedHashSet <String> zhResult = new LinkedHashSet <String>();
							// 存在账号词
							for (String ZH : zhanghao) {
								if (ZH != null) {
									zhResult.add(ZH);
								}
							}

							if (zhResult.size() > 4 && tixin.size() == 1 && isContinue) {
								//System.out.println(zhResult);
								// 2-1.体型词是同一个，且出现“账号详情”词出现4个以上不重复词，则记为一个记录集，写入账号交易表，给记录一个标记“2”
								Object[] args = new Object[24];
								args[0] = THEME_ID;
								args[1] = THEME_NAME;
								args[2] = POST_BAR;
								args[3] = REPLY_ID;
								args[4] = POST_BAR_CLASS;
								args[5] = BELONG_FLOOR;
								args[6] = PAGE_NUM;
								args[7] = rs.getString(8);
								args[8] = data.length() < 1000 ? data : data.substring(0, 1000);
								args[9] = PAGE_URL;
								args[10] = 2;

								args[11] = qufu.size()>0?qufu.iterator().next() + "":"";
								args[12] = tixin.size()>0?tixin.iterator().next() + "":"";
								args[13] = price.size()>0?price.iterator().next() + "":"";
								args[14] = Arrays.toString(menpai.toArray());
								args[15] = Arrays.toString(xinfa.toArray());
								args[16] = Arrays.toString(title.toArray());
								args[17] = Arrays.toString(waiguan.toArray());
								args[18] = Arrays.toString(horse.toArray());
								args[19] = Arrays.toString(arm.toArray());
								args[20] = Arrays.toString(stra.toArray());
								args[21] = Arrays.toString(pend.toArray());
								args[22] = Md5Util2.getMd5(sb.toString());
								args[23] = trade_type;
								batchArgsList.add(args);
								isContinue = false;
							}

							if ((data.indexOf("赠") >= 0 || data.indexOf("同账号") >= 0 || data.indexOf("同一账号") >= 0
									|| data.indexOf("同帐号") >= 0) && tixin.size() <= 3 && isContinue) {
								// 2-2.对剩余记录中，体型词小于等于3，且正文出现特殊词“赠”、“同账号”、“同一账号”、“同帐号”任意一个，则记为一个记录集，写入账号交易表，给记录一个标记“3”
								Object[] args = new Object[24];
								args[0] = THEME_ID;
								args[1] = THEME_NAME;
								args[2] = POST_BAR;
								args[3] = REPLY_ID;
								args[4] = POST_BAR_CLASS;
								args[5] = BELONG_FLOOR;
								args[6] = PAGE_NUM;
								args[7] = rs.getString(8);
								args[8] = data.length() < 1000 ? data : data.substring(0, 1000);
								args[9] = PAGE_URL;
								args[10] = 3;
								args[11] = qufu.size()>0?qufu.iterator().next() + "":"";
								args[12] = tixin.size()>0?tixin.iterator().next() + "":"";
								args[13] = price.size()>0?price.iterator().next() + "":"";
								args[14] = Arrays.toString(menpai.toArray());
								args[15] = Arrays.toString(xinfa.toArray());
								args[16] = Arrays.toString(title.toArray());
								args[17] = Arrays.toString(waiguan.toArray());
								args[18] = Arrays.toString(horse.toArray());
								args[19] = Arrays.toString(arm.toArray());
								args[20] = Arrays.toString(stra.toArray());
								args[21] = Arrays.toString(pend.toArray());
								args[22] = Md5Util2.getMd5(sb.toString());
								args[23] = trade_type;
								batchArgsList.add(args);
								isContinue = false;
							} else {
								// 2-3.对剩余记录集按换行符分割为多条记录，形成记录集，写入账号表表，给记录一个标记“4”
								String[] splitDatas = data.split("<br>");

								for (String splitData : splitDatas) {
									//System.out.println(splitData);
									Result splitDataSegResult = DicAnalysis
											.parse(splitData.replaceAll("[\\pP\\p{Punct}]", " "));
									trade_type = 2;
									qufu = new LinkedHashSet <String>();
									tixin = new LinkedHashSet <String>();
									price = new LinkedHashSet <String>();
									menpai = new LinkedHashSet <String>();
									xinfa = new LinkedHashSet <String>();
									title = new LinkedHashSet <String>();
									waiguan = new LinkedHashSet <String>();
									horse = new LinkedHashSet <String>();
									arm = new LinkedHashSet <String>();
									stra = new LinkedHashSet <String>();
									pend = new LinkedHashSet <String>();
									qiza = new LinkedHashSet <String>();
									zhanghao = new LinkedHashSet <String>();
									daoju = new HashSet <String>();
									dailian = new LinkedHashSet <String>();
									jinbi = new LinkedHashSet <String>();

									// int price_num = 0;
									int i_ = 0;
									for (Term term : splitDataSegResult) {

										if (term.getNatureStr().equalsIgnoreCase("user_menpi")) {
											menpai.add(informNameSet.get(term.getName()));
										}
										if (term.getNatureStr().equalsIgnoreCase("user_mptixing")) {
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
													int index = i_ - j;
													if (index > 0) {
														String nature = splitDataSegResult.get(index).getNatureStr();
														String name = splitDataSegResult.get(index).getName();
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
													int index = i_ - j;
													if (index > 0) {
														String nature = splitDataSegResult.get(index).getNatureStr();
														String name = splitDataSegResult.get(index).getName();
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
													int index = i_ - j;
													if (index > 0) {
														String nature = splitDataSegResult.get(index).getNatureStr();
														String name = splitDataSegResult.get(index).getName();
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
											qiza.add(informNameSet.get(term.getName()));
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

										if (term.getNatureStr().equalsIgnoreCase("user_trade")) {
											trade_type = 1;
										}
										i_++;
									}
									if (tixin.size() > 0) {
										sb = new StringBuffer();
										sb.append(REPLY_ID);
										sb.append(qufu);
										sb.append(tixin);
										sb.append(waiguan);
										Object[] args = new Object[24];
										args[0] = THEME_ID;
										args[1] = THEME_NAME;
										args[2] = POST_BAR;
										args[3] = REPLY_ID;
										args[4] = POST_BAR_CLASS;
										args[5] = BELONG_FLOOR;
										args[6] = PAGE_NUM;
										args[7] = rs.getString(8);
										args[8] = splitData;
										args[9] = PAGE_URL;
										args[10] = 4;
										args[11] = qufu.size()>0?qufu.iterator().next() + "":"";
										args[12] = tixin.size()>0?tixin.iterator().next() + "":"";
										args[13] = price.size()>0?price.iterator().next() + "":"";
										args[14] = Arrays.toString(menpai.toArray());
										args[15] = Arrays.toString(xinfa.toArray());
										args[16] = Arrays.toString(title.toArray());
										args[17] = Arrays.toString(waiguan.toArray());
										args[18] = Arrays.toString(horse.toArray());
										args[19] = Arrays.toString(arm.toArray());
										args[20] = Arrays.toString(stra.toArray());
										args[21] = Arrays.toString(pend.toArray());
										args[22] = Md5Util2.getMd5(sb.toString());
										args[23] = trade_type;
										batchArgsList.add(args);
									}

								}
								isContinue = false;
							}
							// System.out.println("-------------------------------------");
						} else {

							if (tixin.size() == 1 && isContinue) {
								// 1.若只出现了唯一的一个体型词，则记为一个记录集，写入账号交易表，给记录一个标记"1"(BAR_SOUR_TYPE字段下同)
								Object[] args = new Object[24];
								args[0] = THEME_ID;
								args[1] = THEME_NAME;
								args[2] = POST_BAR;
								args[3] = REPLY_ID;
								args[4] = POST_BAR_CLASS;
								args[5] = BELONG_FLOOR;
								args[6] = PAGE_NUM;
								args[7] = rs.getString(8);
								args[8] = data.length() < 1000 ? data : data.substring(0, 1000);
								args[9] = PAGE_URL;
								args[10] = 1;
								args[11] = qufu.size()>0?qufu.iterator().next() + "":"";
								args[12] = tixin.size()>0?tixin.iterator().next() + "":"";
								args[13] = price.size()>0?price.iterator().next() + "":"";
								args[14] = Arrays.toString(menpai.toArray());
								args[15] = Arrays.toString(xinfa.toArray());
								args[16] = Arrays.toString(title.toArray());
								args[17] = Arrays.toString(waiguan.toArray());
								args[18] = Arrays.toString(horse.toArray());
								args[19] = Arrays.toString(arm.toArray());
								args[20] = Arrays.toString(stra.toArray());
								args[21] = Arrays.toString(pend.toArray());
								args[22] = Md5Util2.getMd5(sb.toString());
								args[23] = trade_type;
								batchArgsList.add(args);
								isContinue=false;
								System.out.println(isContinue);
							} else {
								LinkedHashSet <String> mpResult = new LinkedHashSet <String>();
								// 存在账号词
								for (String mp : menpai) {
									if (mp != null) {
										mpResult.add(mp);
									}
								}

								if (mpResult.size() > 0 && isContinue) {
									// 源表“回贴内容 REPLY_CONTENT”字段中，包含交易词“收 、出、求、蹲”、且最少带一个门派词《特征词-门派表(B_POST_BAR_1)》的记录集，给记录一个标记“5”
									// System.out.println("门派：" + mpResult);
									Object[] args = new Object[24];
									args[0] = THEME_ID;
									args[1] = THEME_NAME;
									args[2] = POST_BAR;
									args[3] = REPLY_ID;
									args[4] = POST_BAR_CLASS;
									args[5] = BELONG_FLOOR;
									args[6] = PAGE_NUM;
									args[7] = rs.getString(8);
									args[8] = data.length() < 1000 ? data : data.substring(0, 1000);
									args[9] = PAGE_URL;
									args[10] = 5;
									args[11] = qufu.size()>0?qufu.iterator().next() + "":"";
									args[12] = tixin.size()>0?tixin.iterator().next() + "":"";
									args[13] = price.size()>0?price.iterator().next() + "":"";
									args[14] = Arrays.toString(menpai.toArray());
									args[15] = Arrays.toString(xinfa.toArray());
									args[16] = Arrays.toString(title.toArray());
									args[17] = Arrays.toString(waiguan.toArray());
									args[18] = Arrays.toString(horse.toArray());
									args[19] = Arrays.toString(arm.toArray());
									args[20] = Arrays.toString(stra.toArray());
									args[21] = Arrays.toString(pend.toArray());
									args[22] = Md5Util2.getMd5(sb.toString());
									args[23] = trade_type;
									batchArgsList.add(args);
									isContinue=false;
									System.out.println(isContinue);
								}

								if (xinfa.size() > 0 && isContinue) {
									// 源表“回贴内容 REPLY_CONTENT”字段中，包含交易词“收 、出、求、蹲”、且最少带一个心法词《特征词-门派心法表(B_POST_BAR_3)》的记录集，给记录一个标记“6”
									// System.out.println("心法：" + xfResult);
									Object[] args = new Object[24];
									args[0] = THEME_ID;
									args[1] = THEME_NAME;
									args[2] = POST_BAR;
									args[3] = REPLY_ID;
									args[4] = POST_BAR_CLASS;
									args[5] = BELONG_FLOOR;
									args[6] = PAGE_NUM;
									args[7] = rs.getString(8);
									args[8] = data.length() < 1000 ? data : data.substring(0, 1000);
									args[9] = PAGE_URL;
									args[10] = 6;
									args[11] = qufu.size()>0?qufu.iterator().next() + "":"";
									args[12] = tixin.size()>0?tixin.iterator().next() + "":"";
									args[13] = price.size()>0?price.iterator().next() + "":"";
									args[14] = Arrays.toString(menpai.toArray());
									args[15] = Arrays.toString(xinfa.toArray());
									args[16] = Arrays.toString(title.toArray());
									args[17] = Arrays.toString(waiguan.toArray());
									args[18] = Arrays.toString(horse.toArray());
									args[19] = Arrays.toString(arm.toArray());
									args[20] = Arrays.toString(stra.toArray());
									args[21] = Arrays.toString(pend.toArray());
									args[22] = Md5Util2.getMd5(sb.toString());
									args[23] = trade_type;
									batchArgsList.add(args);
									isContinue=false;
									System.out.println(isContinue);
								}
							}
						}
						jdbcTemplate.batchUpdate(sqlMPTX, batchArgsList);

					} catch (Exception e) {
						e.printStackTrace();
					}

					if (wgCount > 0 && isContinue) {
						// System.out.println("外观：" + wgResult);
						String sqlWg = "insert into C_POST_BAR_13(RECORD_ID,THEME_ID,THEME_NAME,POST_BAR,POST_ID,POST_BAR_CLASS,BELONG_FLOOR,PAGE_NUM,REPLY_TIME,"
								+ "POST_CONTENT,PAGE_URL,VIEW_NAME,WAIGUAN_NAME,MAIN_ID,TRADE_TYPE,url_valid,source_type,FAVOR_ID,createtime,updatetime) "
								+ "values(UUID(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,nextval('SEQUENCE_3'),sysdate(),sysdate())";
						List<Object[]> batchArgsList = Lists.newArrayList();

						try {
							if (waiguan.size() > 1) {
								String[] splitDatas = data.split("<br>");

								int i_ = 0;
								for (String splitData : splitDatas) {
									waiguan = new LinkedHashSet <String>();
									trade_type = 2;
									Result splitDataSegResult = DicAnalysis
											.parse(splitData.replaceAll("[\\pP\\p{Punct}]", " "));
									for (Term term : splitDataSegResult) {
										if (term.getNatureStr().equalsIgnoreCase("user_waiguan")) {
											waiguan.add(informNameSet.get(term.getName()));
											if (term.getName().endsWith("金")) {
												for (int j = 1; j < 100; j++) {
													int index = i_ - j;
													if (index > 0) {
														String nature = splitDataSegResult.get(index).getNatureStr();
														String name = splitDataSegResult.get(index).getName();
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
													int index = i_ - j;
													if (index > 0) {
														String nature = splitDataSegResult.get(index).getNatureStr();
														String name = splitDataSegResult.get(index).getName();
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
													int index = i_ - j;
													if (index > 0) {
														String nature = splitDataSegResult.get(index).getNatureStr();
														String name = splitDataSegResult.get(index).getName();
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

										if (term.getNatureStr().equalsIgnoreCase("user_trade")) {
											trade_type = 1;
										}
									}
									// System.out.println(splitData);
									Object[] args = new Object[14];
									args[0] = THEME_ID;
									args[1] = THEME_NAME;
									args[2] = POST_BAR;
									args[3] = REPLY_ID;
									args[4] = POST_BAR_CLASS;
									args[5] = BELONG_FLOOR;
									args[6] = PAGE_NUM;
									args[7] = rs.getString(8);
									args[8] = splitData;
									args[9] = PAGE_URL;
									args[10] = Arrays.toString(waiguan.toArray());
									args[11] = Arrays.toString(waiguan.toArray());
									args[12] = Md5Util2.getMd5(REPLY_ID+splitData);
									args[13] = trade_type;
									batchArgsList.add(args);
								}
							} else {
								Object[] args = new Object[14];
								args[0] = THEME_ID;
								args[1] = THEME_NAME;
								args[2] = POST_BAR;
								args[3] = REPLY_ID;
								args[4] = POST_BAR_CLASS;
								args[5] = BELONG_FLOOR;
								args[6] = PAGE_NUM;
								args[7] = rs.getString(8);
								args[8] = data;
								args[9] = PAGE_URL;
								args[10] = Arrays.toString(waiguan.toArray());
								args[11] = Arrays.toString(waiguan.toArray());
								args[12] = Md5Util2.getMd5(REPLY_ID+data);
								args[13] = trade_type;
								batchArgsList.add(args);
							}
							jdbcTemplate.batchUpdate(sqlWg, batchArgsList);
							isContinue = false;
							System.out.println(isContinue);
						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println(data);
						}
					}

					if (djCount > 0 && isContinue) {
						// System.out.println("道具：" + djResult);
						String sqlDj = "insert into C_POST_BAR_15(RECORD_ID,THEME_ID,THEME_NAME,POST_BAR,POST_ID,POST_BAR_CLASS,BELONG_FLOOR,"
								+ "PAGE_NUM,REPLY_TIME,POST_CONTENT,PAGE_URL,PROP_NAME,PRICE_NUM,TRADE_TYPE,MAIN_ID,source_type,url_valid,FAVOR_ID,createtime,updatetime) "
								+ "values (UUID(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,nextval('SEQUENCE_3'),sysdate(),sysdate())";
						List<Object[]> batchArgsList = Lists.newArrayList();

						try {
							if (daoju.size() > 1) {
								String[] splitDatas = data.split("<br>");

								for (String splitData : splitDatas) {
									// System.out.println(splitData);
									daoju = new HashSet<String>();
									Result splitDataSegResult = DicAnalysis.parse(splitData.replaceAll("[\\pP\\p{Punct}]", " "));
									trade_type = 2;
									for (Term term : splitDataSegResult) {
										if (term.getNatureStr().equalsIgnoreCase("user_daoju")) {
											daoju.add(informNameSet.get(term.getName()));
										}
										if (term.getNatureStr().equalsIgnoreCase("user_trade")) {
											trade_type = 1;
										}
									}
									System.out.println(daoju);
									Object[] args = new Object[14];
									args[0] = THEME_ID;
									args[1] = THEME_NAME;
									args[2] = POST_BAR;
									args[3] = REPLY_ID;
									args[4] = POST_BAR_CLASS;
									args[5] = BELONG_FLOOR;
									args[6] = PAGE_NUM;
									args[7] = rs.getString(8);
									args[8] = splitData;
									args[9] = PAGE_URL;
									args[10] = "";
									args[11] = price.size()>0?price.iterator().next() + "":"";
									args[12] = trade_type;
									args[13] = Md5Util2.getMd5(REPLY_ID+splitData);
									batchArgsList.add(args);
								}

							} else {
								System.out.println(daoju);
								Object[] args = new Object[14];
								args[0] = THEME_ID;
								args[1] = THEME_NAME;
								args[2] = POST_BAR;
								args[3] = REPLY_ID;
								args[4] = POST_BAR_CLASS;
								args[5] = BELONG_FLOOR;
								args[6] = PAGE_NUM;
								args[7] = rs.getString(8);
								args[8] = data;
								args[9] = PAGE_URL;
								args[10] = "";
								args[11] = price.size()>0 ? price.iterator().next() + "" : "";
								args[12] = trade_type;
								args[13] = Md5Util2.getMd5(REPLY_ID+data);;
								batchArgsList.add(args);
							}
							jdbcTemplate.batchUpdate(sqlDj, batchArgsList);
							isContinue = false;
							System.out.println(isContinue);
						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println(data);
						}

						// System.out.println("-------------------------------------");
					}

					if (dlCount > 0 && isContinue) {
						String sqlDL = "insert into C_POST_BAR_17(RECORD_ID,THEME_ID,THEME_NAME,POST_BAR,POST_ID,POST_BAR_CLASS,BELONG_FLOOR,PAGE_NUM,REPLY_TIME,"
								+ "POST_CONTENT,PAGE_URL,source_type,FAVOR_ID,createtime,updatetime) values(UUID(),?,?,?,?,?,?,?,?,?,?,1,nextval('SEQUENCE_3'),sysdate(),sysdate())";
						List<Object[]> batchArgsList = Lists.newArrayList();

						try {
							if (dailian.size() > 1) {
								String[] splitDatas = data.split("<br>");

								for (String splitData : splitDatas) {
									// System.out.println(splitData);
									Object[] args = new Object[10];
									args[0] = THEME_ID;
									args[1] = THEME_NAME;
									args[2] = POST_BAR;
									args[3] = REPLY_ID;
									args[4] = POST_BAR_CLASS;
									args[5] = BELONG_FLOOR;
									args[6] = PAGE_NUM;
									args[7] = rs.getString(8);
									args[8] = splitData;
									args[9] = PAGE_URL;
									batchArgsList.add(args);
								}
							} else {
								Object[] args = new Object[10];
								args[0] = THEME_ID;
								args[1] = THEME_NAME;
								args[2] = POST_BAR;
								args[3] = REPLY_ID;
								args[4] = POST_BAR_CLASS;
								args[5] = BELONG_FLOOR;
								args[6] = PAGE_NUM;
								args[7] = rs.getString(8);
								args[8] = data.length() < 1000 ? data : data.substring(0, 1000);
								args[9] = PAGE_URL;
								batchArgsList.add(args);

							}
							jdbcTemplate.batchUpdate(sqlDL, batchArgsList);
							isContinue = false;
							System.out.println(isContinue);
						} catch (SQLException e) {
							System.out.println(data);
							e.printStackTrace();
						}

						// System.out.println("-------------------------------------");

					}

//					if (jbCount > 1 && isContinue) {
						// System.out.println("金币：" + jbResult);
//						String sqlJb = "insert into C_POST_BAR_19(RECORD_ID,THEME_ID,THEME_NAME,POST_BAR,POST_ID,POST_BAR_CLASS,BELONG_FLOOR,PAGE_NUM,REPLY_TIME,"
//								+ "POST_CONTENT,PAGE_URL,FAVOR_ID,createtime,updatetime) values(UUID(),?,?,?,?,?,?,?,?,?,?, nextval('SEQUENCE_3'),sysdate(),sysdate())";
//						List<Object[]> batchArgsList = Lists.newArrayList();
//
//						try {
//							if (jinbi.size() > 1) {
//								String[] splitDatas = data.split("<br>");
//
//								for (String splitData : splitDatas) {
//									Object[] args = new Object[10];
//									args[0] = THEME_ID;
//									args[1] = THEME_NAME;
//									args[2] = POST_BAR;
//									args[3] = REPLY_ID;
//									args[4] = POST_BAR_CLASS;
//									args[5] = BELONG_FLOOR;
//									args[6] = PAGE_NUM;
//									args[7] = rs.getString(8);
//									args[8] = splitData;
//									args[9] = PAGE_URL;
//									batchArgsList.add(args);
//								}
//
//							} else {
//								Object[] args = new Object[10];
//								args[0] = THEME_ID;
//								args[1] = THEME_NAME;
//								args[2] = POST_BAR;
//								args[3] = REPLY_ID;
//								args[4] = POST_BAR_CLASS;
//								args[5] = BELONG_FLOOR;
//								args[6] = PAGE_NUM;
//								args[7] = rs.getString(8);
//								args[8] = data.length() < 1000 ? data : data.substring(0, 1000);
//								args[9] = PAGE_URL;
//								batchArgsList.add(args);
//
//							}
//							jdbcTemplate.batchUpdate(sqlJb, batchArgsList);
//							isContinue = false;
//							System.out.println(isContinue);
//						} catch (SQLException e) {
//							System.out.println(data);
//							e.printStackTrace();
//						}

						// System.out.println("-------------------------------------");
					//}

					if (isContinue) {
						String sqlLJ = "insert into C_POST_BAR_20(RECORD_ID,THEME_ID,THEME_NAME,POST_BAR,POST_ID,POST_BAR_CLASS,BELONG_FLOOR,PAGE_NUM,REPLY_TIME,"
								+ "REPLY_CONTENT,PAGE_URL,FAVOR_ID,createtime,updatetime) values(UUID(),?,?,?,?,?,?,?,?,?,?, nextval('SEQUENCE_3'),sysdate(),sysdate())";
						try {
							List<Object[]> batchArgsList = Lists.newArrayList();

							Object[] args = new Object[10];
							args[0] = THEME_ID;
							args[1] = THEME_NAME;
							args[2] = POST_BAR;
							args[3] = REPLY_ID;
							args[4] = POST_BAR_CLASS;
							args[5] = BELONG_FLOOR;
							args[6] = PAGE_NUM;
							args[7] = rs.getString(8);
							args[8] = data;
							args[9] = PAGE_URL;
							batchArgsList.add(args);
							jdbcTemplate.batchUpdate(sqlLJ, batchArgsList);
							System.out.println(isContinue);
						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println(data);
							System.out.println(qufu);
						}
					}

				}
			}
		});
	}
}
