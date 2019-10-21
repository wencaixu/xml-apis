package com.jerry.xmlreader;

import com.jerry.common.XMLConfigDomUtils;
import com.jerry.common.XmlTag;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM方式解析
 *
 * @author Jerry Hsu 徐文才
 */
public class XmlReaderByDom {

    private XMLConfigDomUtils xmlConfig = XMLConfigDomUtils.getInstance();

    public void parserXML(String xmlPath) {
        Document doc = xmlConfig.getDocument(xmlPath);
        getChildElement(doc);
        System.out.println("========================");
        Node menuRoot = doc.getDocumentElement();
        NodeList childNodes = menuRoot.getChildNodes();
        getChildElement(childNodes);
    }

    private static void getChildElement(NodeList items) {
        int size = items.getLength();
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            Node item = items.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                if (!item.getNodeName().equals(XmlTag.FOOD)) {
                    System.out.print(item.getNodeName() + ":");
                }
                getChildElement(item.getChildNodes());
            } else if (item.getNodeType() == Node.TEXT_NODE) {
                String nodeText = item.getTextContent().trim();
                if (nodeText.length() != 0) {
                    System.out.println(nodeText);
                }
            }
        }
    }

    private static void getChildElement(Document doc) {
        NodeList elementsByTagName = doc.getElementsByTagName(XmlTag.FOOD);
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            Node item = elementsByTagName.item(i);
            NodeList childNodes = item.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node node = childNodes.item(j);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String nodeName = node.getNodeName();
                    String nodeValue= node.getTextContent();
                    System.out.println(nodeName+":"+nodeValue);
                }
            }
        }
    }


    public static void main(String[] args) {
        new XmlReaderByDom().parserXML("xml/henan-dishes.xml");
    }

}
