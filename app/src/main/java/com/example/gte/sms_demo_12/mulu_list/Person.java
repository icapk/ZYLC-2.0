package com.example.gte.sms_demo_12.mulu_list;

/**
 * Created by GTE on 2016/11/22.
 */

import com.example.gte.sms_demo_12.Utils.pinyin_utils;

public class Person {

    //姓名
    private String name;
    //拼音
    private String pinyin;
    //拼音首字母
    private String headerWord;

    public Person(String name) {
        this.name = name;
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
}
