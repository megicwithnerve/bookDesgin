package com.magician.book.utils;

import com.magician.book.pojo.RechargeRecordEntity;
import com.magician.book.pojo.Rechargerecord;

import java.util.ArrayList;
import java.util.List;

public class ToEntity {

    public static List<RechargeRecordEntity> ToRechargeEntity(List<Rechargerecord> list){

        List<RechargeRecordEntity> result = new ArrayList<>();
//        for (Rechargerecord rechargerecord:list) {
//            result.add(new RechargeRecordEntity(rechargerecord));
//        }
        return result;
    }
}
