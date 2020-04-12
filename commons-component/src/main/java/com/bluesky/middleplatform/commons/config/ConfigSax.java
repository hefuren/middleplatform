package com.bluesky.middleplatform.commons.config;

import java.io.FileReader;
import java.util.Enumeration;
import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ConfigSax {

    protected static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
    private static final int ROOT_LEVEL = 2;
    private static final int LEAVE_LEVEL = 3;
    private int level = 0;
    private Hashtable rootNode;
    private Hashtable leaveNode;
    private String leaveKey;

    public static void main(String[] args) throws Exception {
//        XMLReader xr = XMLReaderFactory
//                .createXMLReader("org.apache.xerces.parsers.SAXParser");

//        XMLReader reader = XMLReaderFactory.createXMLReader();
//
//        ConfigSax handler = new ConfigSax();
//        xr.setContentHandler(handler);
//        xr.setErrorHandler(handler);
//
//        for (int i = 0; i < args.length; i++) {
//            FileReader r = new FileReader(args[i]);
//            xr.parse(new InputSource(r));
//        }
//        handler.dumpNode();
//        Hashtable h = (Hashtable) handler.getNode().get("poem");
//        System.out.println(h.get("b").toString());
    }

    public void startDocument() {
        this.rootNode = new Hashtable();
    }

    public void endDocument() {
    }

    public void startElement(String uri, String name, String qName,
                             Attributes atts) {
        this.level += 1;

        if (this.level == 2) {
            this.leaveNode = new Hashtable();
            this.rootNode.put(qName, this.leaveNode);
        } else if (this.level == 3) {
            this.leaveKey = qName;
        }
    }

    public void endElement(String uri, String name, String qName) {
        this.level -= 1;
    }

    public void characters(char[] ch, int start, int length) {
        if (this.level == 3) {
            StringBuffer leaveValue = new StringBuffer();
            for (int i = start; i < start + length; i++) {
                switch (ch[i]) {
                    case '\\':
                        break;
                    case '"':
                        break;
                    case '\n':
                        break;
                    case '\r':
                        break;
                    case '\t':
                        break;
                    default:
                        leaveValue.append(ch[i]);
                }
            }

            this.leaveNode.put(this.leaveKey, leaveValue);
        }
    }

    public Hashtable getNode() {
        return this.rootNode;
    }

    public void dumpNode() {
        dumpNode(this.rootNode);
    }

    private void dumpNode(Hashtable node) {
        for (Enumeration e = node.keys(); e.hasMoreElements(); ) {
            String key = e.nextElement().toString();
            if ((node.get(key) instanceof Hashtable)) {
                dumpNode((Hashtable) node.get(key));
            }
        }
    }
}
