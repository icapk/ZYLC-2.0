package com.example.gte.sms_demo_12;

import android.util.Xml;

import com.example.gte.sms_demo_12.domain.Name;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GTE on 2016/12/12.
 */

public class NameService {
    public static List<Name> getNames(InputStream xml) {
        List<Name> names = null;
        Name name = null;
        try {


            XmlPullParser pullParser = Xml.newPullParser();

            //为pull解析器设置要解析的XML数据
            pullParser.setInput(xml, "UTF-8");
            int event = pullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        names = new ArrayList<Name>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("list".equals(pullParser.getName())) {
                            int id = new Integer(pullParser.getAttributeValue(0));
                            name = new Name();
                            name.setId(id);
                        }
                        if ("mNumber".equals(pullParser.getName())) {
                            //.nextText 获取节点后面的text文本数据
                            String mNumber = pullParser.nextText();
                            name.setmNumber(mNumber);
                        }
                        if ("pNumber".equals(pullParser.getName())) {
                            String pNumber = pullParser.nextText();
                            name.setpNumber(pNumber);
                        }
                        if ("mBeiZhu".equals(pullParser.getName())) {
                            String mBeiZhu = pullParser.nextText();
                            name.setmBeiZhu(mBeiZhu);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("list".equals(pullParser.getName())) {
                            names.add(name);
                            name = null;
                        }
                        break;
                }
                //进入下一个节点
                event = pullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }
}
