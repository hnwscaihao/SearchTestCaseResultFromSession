package com.gw.util;

import com.gw.service.ImportService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
*Dom4j解析xml
 */
public class AnalysisXML {

    private static final Log log = LogFactory.getLog(ImportService.class);

   public static Result resultXml(){
       Result r  = new Result();
       String jdsx = "Test Case";
       //1.创建Reader对象
       SAXReader reader = new SAXReader();
       //2.加载xml
       Document document = null;
       Map<String,String> m = new HashMap<>();
       try {
           document = reader.read(new File("src/main/resources/InfoMapping.xml"));
           //3.获取根节点
           Element rootElement = document.getRootElement();
           Iterator iterator = rootElement.elementIterator();
           while (iterator.hasNext()){
               Element stu = (Element) iterator.next();
               List<Attribute> attributes = stu.attributes();
//               System.out.println("======获取属性值======");
               String s = "";
               for (Attribute attribute : attributes) {
                   s = attribute.getValue();
//                   System.out.println(attribute.getValue());
               }
//               System.out.println("======遍历子节点======");
               if(s.equals(jdsx)){
                   Iterator iterator1 = stu.elementIterator();
                   while (iterator1.hasNext()){
                       Element stuChild = (Element) iterator1.next();
                       m.put(stuChild.attribute("name").getValue(),stuChild.attribute("reality").getValue());
//                       System.out.println("节点名："+stuChild.getName()+"---节点值："+stuChild.getStringValue());
                   }
               }
           }
       } catch (DocumentException e) {
           e.printStackTrace();
       }
       r.setData(m);
       return r;
   }

   public static void main(String[] args){
       resultXml();
   }
}
