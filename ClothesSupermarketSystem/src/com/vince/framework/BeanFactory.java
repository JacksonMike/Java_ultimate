package com.vince.framework;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * 创建Bean的factory类
 */
public class BeanFactory {

    private HashMap<String, HashMap<String, String>> beans = null;

    public static BeanFactory beanFactory = null;

    //双检单例模式
    public static BeanFactory init() {
        if (beanFactory == null) {
            synchronized (BeanFactory.class) {
                if (beanFactory == null) {
                    System.out.println("BeanFactory init...");
                    beanFactory = new BeanFactory("bean.xml");
                    System.out.println("BeanFactory init success.");
                }
            }
        }
        return beanFactory;
    }

    private BeanFactory(String xml) {
        System.out.println("loading..." + xml);
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xml);
        //1 创建DOM4J的解析器对象
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(is);
            Element rootElement = document.getRootElement();
            Iterator<Element> iter = rootElement.elementIterator();
            beans = new HashMap<>();
            while (iter.hasNext()) {
                HashMap<String, String> bean = new HashMap<>();
                Element e = iter.next();
                String sid = e.attributeValue("id");
                bean.put(sid, e.attributeValue("class"));
                beans.put(sid, bean);
            }
            is.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建业务对象的方法
    public Object getBean(String id) {
        while (beans.containsKey(id)) {
            HashMap<String, String> beanMap = beans.get(id);
            String className = beanMap.get(id);
            try {
                return Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
