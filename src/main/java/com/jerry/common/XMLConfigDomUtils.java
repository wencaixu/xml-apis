package com.jerry.common;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * XML解析工具类
 *
 * @author Jerry Hsu 徐文才
 */
public class XMLConfigDomUtils {

    private static XMLConfigDomUtils instance = new XMLConfigDomUtils();

    public static XMLConfigDomUtils getInstance() {
        return instance;
    }

    /**
     * DOM方式
     * <p>
     * 建立XML Document文档
     *
     * @return
     */
    public DocumentBuilder getDocumentBuilder() {
        DocumentBuilder builder = null;
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return builder;
    }

    /**
     * DOM方式
     * <p>
     * 获取XML文档
     *
     * @param xmlName
     * @return
     */
    public Document getDocument(String xmlName) {
        DocumentBuilder builder = getDocumentBuilder();
        Document parse = null;
        String xmlPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource(xmlName)).getPath();
        try {
            parse = builder.parse(new File(xmlPath).toURI().getPath());
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * DOM方式
     *
     * @param doc       Document文档
     * @param xmlTag    Document标签名
     * @param i         标签索引
     * @return
     */
    public String getNodeValue(Document doc,String xmlTag,int i){
       return doc.getElementsByTagName(xmlTag).item(i).getNodeValue();
    }
}
