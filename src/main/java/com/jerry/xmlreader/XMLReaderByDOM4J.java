package com.jerry.xmlreader;

import com.jerry.common.XMLConfigDom4JUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.List;

/**
 * @author Jerry Hsu  - 徐文才
 */
public class XMLReaderByDOM4J {

    private XMLConfigDom4JUtils xmlConfigDom4JUtils = XMLConfigDom4JUtils.getInstance();

    private static final int FLAG = 0;

    public void parserXML(String xmlPath) {
        String xml = xmlPath.trim();
        if (xml.length() == 0 || "".equals(xml)) {
            return;
        }
        parserXML(xmlPath, FLAG);
    }

    private void parserXML(String xmlPath, int flag) {
        Document doc = xmlConfigDom4JUtils.getDom4JDocument(xmlPath);
        Element root = doc.getRootElement();
        List elements = root.elements();
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            getElementsValue(e);
        }
    }

    private static void getElementsValue(Element e) {
        if (e.isTextOnly()) {
            return;
        }
        Iterator elements = e.elements().iterator();
        while (elements.hasNext()) {
            Element e1 = (Element) elements.next();
            if (e1.getNodeType() == Node.ELEMENT_NODE) {
                System.out.print(e1.getName() + ":");
                System.out.println(e1.getStringValue());
                getElementsValue(e1);
            }
        }
    }

    public static void main(String[] args) {
        new XMLReaderByDOM4J().parserXML("xml/henan-dishes.xml");
    }
}
