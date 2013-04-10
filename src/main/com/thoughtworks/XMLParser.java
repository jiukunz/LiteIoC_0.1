package com.thoughtworks;

import javax.xml.parsers.SAXParserFactory;
import java.net.URL;

public class XMLParser {

    private URL url;

    public XMLParser(URL url) {
        this.url = url;
    }

    public Container parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            Container container = new Container();
            factory.newSAXParser().parse(url.openStream(), new XMLHandler(container));
            return container;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
