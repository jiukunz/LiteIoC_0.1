package com.thoughtworks;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.net.URL;

public class XMLParser {

    private URL url;
    private Container container;

    public XMLParser(URL url,Container container) {
        this.container = container;
        this.url = url;
    }

    public Container parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            factory.newSAXParser().parse(new File("src/test/resource/bean.xml").toURI().toURL().openStream(), new XMLHandler(container));
            return container;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
