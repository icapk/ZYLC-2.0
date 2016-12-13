package com.example.gte.sms_demo_12.domain;

/**
 * Created by GTE on 2016/12/12.
 */

public class Name {
    private Integer id;
    private String mNumber;
    private String pNumber;
    private String mBeiZhu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getmBeiZhu() {
        return mBeiZhu;
    }

    public void setmBeiZhu(String mBeiZhu) {
        this.mBeiZhu = mBeiZhu;
    }

    @Override
    public String toString() {
        return "Name{" +
                "id=" + id +
                ", mNumber='" + mNumber + '\'' +
                ", pNumber='" + pNumber + '\'' +
                ", mBeiZhu='" + mBeiZhu + '\'' +
                '}';
    }
}
