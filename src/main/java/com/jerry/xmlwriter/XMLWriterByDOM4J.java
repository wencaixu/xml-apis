package com.jerry.xmlwriter;

import com.jerry.common.XMLConfigDom4JUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.jerry.common.XmlTag.*;

/**
 * @author Jerry Hsu  - 徐文才
 */
public class XMLWriterByDOM4J<E> {

    private static final XMLConfigDom4JUtils XML_CONFIG_DOM_4_J_UTILS = new XMLConfigDom4JUtils();

    public void insertElement(Map<E, E> element, String xmlPath) {
        createElement(element, xmlPath);
    }

    private void createElement(Map<E, E> element, String xmlPath) {
        Document xmlConfig = XML_CONFIG_DOM_4_J_UTILS.getDom4JDocument(xmlPath);
        Element root = xmlConfig.getRootElement();

        Element food = root.addElement(FOOD);
        Element name = food.addElement(NAME);
        Element price = food.addElement(PRICE);
        Element desc = food.addElement(DESC);

        name.setText(element.get(NAME).toString());
        price.setText(element.get(PRICE).toString());
        desc.setText(element.get(DESC).toString());

        xmlPath = Objects.requireNonNull(
                this.getClass().getClassLoader().getResource(xmlPath)).getPath();
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(new File(xmlPath)));
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            format.setNewlines(true);
            format.setIndentSize(5);
            format.setLineSeparator("\n");
            writer.write(xmlConfig);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<String, String> xmlNode = new HashMap<>(3);
        xmlNode.put("name", "酸辣土豆丝");
        xmlNode.put("price", "10¥");
        xmlNode.put("description", "家常菜");

        new XMLWriterByDOM4J<String>().insertElement(xmlNode, "./xml/henan-dishes.xml");
    }

}
