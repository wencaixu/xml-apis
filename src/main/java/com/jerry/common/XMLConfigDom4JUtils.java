package com.jerry.common;

import com.jerry.xmlreader.XMLReaderByDOM4J;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Objects;

public class XMLConfigDom4JUtils {

    private static final XMLConfigDom4JUtils XML_CONFIG_DOM_4_J_UTILS = new XMLConfigDom4JUtils();

    public static XMLConfigDom4JUtils getInstance(){
        return XML_CONFIG_DOM_4_J_UTILS;
    }

    public SAXReader getSaxReader(){
        SAXReader reader = new SAXReader();
        return reader;
    }

    public Document getDom4JDocument(String xmlPath){
        SAXReader saxReader = getSaxReader();
        Document doc = null;
        String xmlAbsolutePath = Objects.requireNonNull(
                XMLConfigDom4JUtils.class.getClassLoader().getResource(xmlPath)).getPath();
        try {
            doc = saxReader.read(new File(xmlAbsolutePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }

}
