package com.example.gte.sms_demo_12.domain;

import com.example.gte.sms_demo_12.mulu_list.Person;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by GTE on 2016/12/21.
 */

public class NameServices  {



    private String m;
    private String n;
        public static List<Name> getNames(InputStream xml) {
            List<Name> names = null;
            Name name = null;
//
//            try {
//                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder builder = builderFactory.newDocumentBuilder();
//                Document document = builder.parse(getAssets().open("contacts_data.xml"));
//                Element element = document.getDocumentElement();
//                NodeList lists = element.getElementsByTagName("list");
//                for (int i = 0; i < lists.getLength(); i++) {
//                    Element ele = (Element) lists.item(i);
//
//                    m = ele.getElementsByTagName("mNumber").item(0).getTextContent();
//                    n = ele.getElementsByTagName("pNumber").item(0).getTextContent().toString();
//
//                    names.add(new Person(m));
//
//                }
//
//
//            } catch (ParserConfigurationException e) {
//                e.printStackTrace();
//            } catch (SAXException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//                            if ("list".equals(pullParser.getName())) {
//                                int id = new Integer(pullParser.getAttributeValue(0));
//                                name = new Name();
//                                name.setId(id);
//                            }
//                            if ("mNumber".equals(pullParser.getName())) {
//                                //.nextText 获取节点后面的text文本数据
//                                String mNumber = pullParser.nextText();
//                                name.setmNumber(mNumber);
//                            }
//                            if ("pNumber".equals(pullParser.getName())) {
//                                String pNumber = pullParser.nextText();
//                                name.setpNumber(pNumber);
//                            }
//                            if ("mBeiZhu".equals(pullParser.getName())) {
//                                String mBeiZhu = pullParser.nextText();
//                                name.setmBeiZhu(mBeiZhu);
//                            }
//                            break;
//                        case XmlPullParser.END_TAG:
//                            if ("list".equals(pullParser.getName())) {
//                                names.add(name);
//                                name = null;
//                            }
//                            break;
//                    }
                    //进入下一个节点
      ;


            return names;
        }
}
