package com.jerry.xmlreader;

import com.jerry.common.XmlTag;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

/**
 * @author Jerry Hsu - 徐文才
 */
public class XMLReaderBySAX {

    public void parserXML(String xmlPath) {
        ConfigParser handler = new ConfigParser();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        File file = new File(Objects.requireNonNull(XMLReaderBySAX.class.getClassLoader().getResource(xmlPath)).getPath());
        factory.setNamespaceAware(false);
        factory.setValidating(false);
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(file,handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new XMLReaderBySAX().parserXML("./xml/henan-dishes.xml");
    }
}

class ConfigParser extends DefaultHandler {
    private String temp;
    private Stack<String> element = new Stack<>();
    private StringBuilder builder = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) {
        builder.delete(0, builder.length());
        element.push(name);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        temp = element.pop();
        if (temp.equals(XmlTag.NAME)) {
            System.out.println(XmlTag.NAME + ":" + builder.toString());
        } else if (temp.equals(XmlTag.PRICE)) {
            System.out.println(XmlTag.PRICE + ":" + builder.toString());
        } else if (temp.equals(XmlTag.DESC)) {
            System.out.println(XmlTag.DESC + ":" + builder.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        builder.append(ch,start,length);
    }
}
