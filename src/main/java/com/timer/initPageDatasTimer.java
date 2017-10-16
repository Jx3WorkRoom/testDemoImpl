package com.timer;


import com.utils.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.dao.accountListDao;
import com.dao.appearanceSaleDao;
import com.dao.propSaleDao;
import com.dao.goldExchangeListDao;
import com.dao.levelingListDao;

@Component
public class initPageDatasTimer {

    @Autowired
    accountListDao accountListDao;
    @Autowired
    appearanceSaleDao appearanceSaleDao;
    @Autowired
    propSaleDao propSaleDao;
    @Autowired
    goldExchangeListDao goldExchangeListDao;
    @Autowired
    levelingListDao levelingListDao;

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullAccountListDatas1() {
        Object accountList1 = new Object();
        int accountlistPageNum1 = 0;
        try {
            //accountList
            accountList1 = accountListDao.queryAccountListInfo2(1, 0, 10);
            accountlistPageNum1 = accountListDao.quertLength(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.accountList1 = accountList1;
            Commons.accountlistPageNum1 = accountlistPageNum1;
            System.out.println(accountlistPageNum1);
        }
    }

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullAccountListDatas2() {
        Object accountList2 = new Object();
        int accountlistPageNum2 = 0;
        try {
            //accountList
            accountList2 = accountListDao.queryAccountListInfo2(2, 0, 10);
            accountlistPageNum2 = accountListDao.quertLength(2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.accountList2 = accountList2;
            Commons.accountlistPageNum2 =accountlistPageNum2;
            System.out.println(accountlistPageNum2);
        }
    }

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullAppearanceSaleDatas1() {
        Object appearanceSaleList1 = new Object();
        int appearanceSalelistPageNum1 = 0;
        try {
            //appearanceSale
            appearanceSaleList1 = appearanceSaleDao.queryappearanceSaleInfo2(1, 0, 10);
            appearanceSalelistPageNum1 = appearanceSaleDao.quertLength(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.appearanceSaleList1 = appearanceSaleList1;
            Commons.appearanceSalelistPageNum1 = appearanceSalelistPageNum1;
            System.out.println(appearanceSalelistPageNum1);
        }
    }

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullAppearanceSaleDatas2() {
        Object appearanceSaleList2 = new Object();
        int appearanceSalelistPageNum2 = 0;
        try {
            appearanceSaleList2 = appearanceSaleDao.queryappearanceSaleInfo2(2, 0, 10);
            appearanceSalelistPageNum2 = appearanceSaleDao.quertLength(2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.appearanceSaleList2 = appearanceSaleList2;
            Commons.appearanceSalelistPageNum2 = appearanceSalelistPageNum2;
            System.out.println(appearanceSalelistPageNum2);
        }
    }

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullPropSaleDatas1() {
        Object propSaleList1 = new Object();
        int propSalelistPageNum1 = 0;
        try {
            //propSale
            propSaleList1 = propSaleDao.queryappearanceSaleInfo2(1, 0, 10);
            propSalelistPageNum1 = propSaleDao.quertLength(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.propSaleList1 = propSaleList1;
            Commons.propSalelistPageNum1 = propSalelistPageNum1;
            System.out.println(propSalelistPageNum1);
        }
    }

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullPropSaleDatas2() {
        Object propSaleList2 = new Object();
        int propSalelistPageNum2 = 0;
        try {
            //propSale
            propSaleList2 = propSaleDao.queryappearanceSaleInfo2(2, 0, 10);
            propSalelistPageNum2 = propSaleDao.quertLength(2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.propSaleList2 = propSaleList2;
            Commons.propSalelistPageNum2 = propSalelistPageNum2;
            System.out.println(propSalelistPageNum2);
        }
    }

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullGoldExchangeDatas1() {
        Object goldExchangeList1 = new Object();
        int goldExchangelistPageNum1 = 0;
        try {
            //goldExchange
            goldExchangeList1 = goldExchangeListDao.querygoldExchangeListInfo2(1, 0, 10);
            goldExchangelistPageNum1 = goldExchangeListDao.quertLength(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.goldExchange1 = goldExchangeList1;
            Commons.goldExchangelistPageNum1 = goldExchangelistPageNum1;
            System.out.println(goldExchangelistPageNum1);
        }
    }

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullGoldExchangeDatas2() {
        Object goldExchangeList2 = new Object();
        int goldExchangelistPageNum2 = 0;
        try {
            //goldExchange
            goldExchangeList2 = goldExchangeListDao.querygoldExchangeListInfo2(2, 0, 10);
            goldExchangelistPageNum2 = goldExchangeListDao.quertLength(2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.goldExchange2 = goldExchangeList2;
            Commons.goldExchangelistPageNum2 = goldExchangelistPageNum2;
            System.out.println(goldExchangelistPageNum2);
        }
    }

    @Scheduled(cron="0 0/1 * * * ? ")//每1分钟执行一次
    public void pullLevelingListDatas1() {
        Object levelingList1 = new Object();
        int levelingListPageNum1 = 0;
        try {
            //goldExchange
            levelingList1 = levelingListDao.querylevelingListInfo2(1, 0, 10);
            levelingListPageNum1 = levelingListDao.quertLength(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.levelingList1 = levelingList1;
            Commons.levelingListPageNum1 = levelingListPageNum1;
            System.out.println(levelingListPageNum1);
        }
    }

    @Scheduled(cron="0 0/2 * * * ? ")//每10分钟执行一次
    public void pullLevelingListDatas2() {
        Object levelingList2 = new Object();
        int levelingListPageNum2 = 0;
        try {
            //goldExchange
            levelingList2 = levelingListDao.querylevelingListInfo2(2, 0, 10);
            levelingListPageNum2 = levelingListDao.quertLength(2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Commons.levelingList2 = levelingList2;
            Commons.levelingListPageNum2 = levelingListPageNum2;
            System.out.println(levelingListPageNum2);
        }
    }

}
