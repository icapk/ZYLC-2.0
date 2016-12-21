package com.example.gte.sms_demo_12.mulu_list;

/**
 * Created by GTE on 2016/11/22.
 */

import com.example.gte.sms_demo_12.Utils.pinyin_utils;
import com.example.gte.sms_demo_12.domain.Name;

public class Person {




    //姓名
    private String name;

    private String mNumber;
    private String pNumber;
    private String mBeiZhu;
    //拼音
    private String pinyin;
    //拼音首字母
    private String headerWord;

    public Person(String name,String pNumber,String beizhu) {
        this.name = name;
        this.pNumber = pNumber;
        this.mBeiZhu = beizhu;
        this.pinyin = pinyin_utils.getPinyin(name);
        headerWord = pinyin.substring(0, 1);
    }


    public String getPinyin() {
        return pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderWord() {
        return headerWord;
    }





    public String getpNumber() {
        return pNumber;
    }

    public  void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getmBeiZhu() {
        return mBeiZhu;
    }

    public  void setmBeiZhu(String mBeiZhu) {
        this.mBeiZhu = mBeiZhu;
    }
}

