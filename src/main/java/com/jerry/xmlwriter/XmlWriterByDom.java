package com.jerry.xmlwriter;

import com.jerry.common.XMLConfigDomUtils;
import com.jerry.common.XmlTag;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * DOM方式解析，写入数据
 *
 * @author Jerry Hsu 徐文才
 */
public class XmlWriterByDom {

    private static XMLConfigDomUtils xmlConfig = XMLConfigDomUtils.getInstance();

    public void xmlInsert(Map<String, String> xmlNode, String xmlPath) {
        Document doc = xmlConfig.getDocument(xmlPath);
        Text nodeValue;

        Element root = doc.getDocumentElement();

        Element food = doc.createElement(XmlTag.FOOD);
        Element name = doc.createElement(XmlTag.NAME);
        Element price = doc.createElement(XmlTag.PRICE);
        Element desc = doc.createElement(XmlTag.DESC);

        nodeValue = doc.createTextNode(xmlNode.get(XmlTag.NAME));
        name.setTextContent(nodeValue.getTextContent());
        food.appendChild(name);

        nodeValue = doc.createTextNode(xmlNode.get(XmlTag.PRICE));
        price.setTextContent(nodeValue.getTextContent());
        food.appendChild(price);

        nodeValue = doc.createTextNode(xmlNode.get(XmlTag.DESC));
        desc.setTextContent(nodeValue.getTextContent());
        food.appendChild(desc);

        root.appendChild(food);

        try {
            xmlPath = Objects.requireNonNull(
                    this.getClass().getClassLoader().getResource(xmlPath)).getPath();
            TransformerFactory transformer = TransformerFactory.newInstance();
            Transformer trans = transformer.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlPath).toURI().getPath());
            System.out.println(source+" "+result);
            trans.setOutputProperty(OutputKeys.INDENT,"yes");
            trans.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<String, String> xmlNode = new HashMap<>(3);
        xmlNode.put("name", "酸辣土豆丝");
        xmlNode.put("price", "10¥");
        xmlNode.put("description", "家常菜");

        new XmlWriterByDom().xmlInsert(xmlNode, "./xml/henan-dishes.xml");
    }
}
