package com.jerry.xmlwriter;

import com.jerry.common.XMLConfigDom4JUtils;
import com.jerry.common.XmlTag;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.jerry.common.XmlTag.*;


public class XMLWriterBySAX<T> {

    private static final XMLConfigDom4JUtils XML_CONFIG_DOM_4_J_UTILS = new XMLConfigDom4JUtils();

    public void insertXmlElement(Map<T, T> map, String xmlPath) {
        insert(map,xmlPath);
    }

    private void insert(Map<T, T> map, String xmlPath) {
        SAXReader reader = XML_CONFIG_DOM_4_J_UTILS.getSaxReader();

        Element food = DocumentHelper.createElement(FOOD);
        Element name = DocumentHelper.createElement(NAME);
        Element price = DocumentHelper.createElement(PRICE);
        Element desc = DocumentHelper.createElement(DESC);

        name.setText(map.get(NAME).toString());
        price.setText(map.get(PRICE).toString());
        desc.setText(map.get(DESC).toString());

        food.add(name);
        food.add(price);
        food.add(desc);

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
        try {
            Document doc = reader.read(is);
            Element root = doc.getRootElement();

            root.add(food);

            OutputFormat format = new OutputFormat();
            // 指定XML编码
            format.setEncoding("UTF-8");
            format.setNewlines(true);
            format.setIndent(true);
            format.setTrimText(true);
            xmlPath = Objects.requireNonNull(XMLWriterBySAX.class.getClassLoader().getResource(xmlPath)).getPath();
            OutputStream out = new FileOutputStream(xmlPath);
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(doc);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<String, String> xmlNode = new HashMap<>(3);
        xmlNode.put("name", "酸辣粉");
        xmlNode.put("price", "100¥");
        xmlNode.put("description", "家常菜");

        new XMLWriterBySAX<String>().insertXmlElement(xmlNode, "./xml/henan-dishes.xml");
    }


}
