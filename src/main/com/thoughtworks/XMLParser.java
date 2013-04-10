package com.thoughtworks;

import javax.xml.parsers.SAXParserFactory;
import java.net.URL;

public class XMLParser {

    private URL url;

    public XMLParser(URL url) {
        this.url = url;
    }

    public Context parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            Context context = new Context();
            factory.newSAXParser().parse(url.openStream(), new XMLHandler(context));
            return context;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
