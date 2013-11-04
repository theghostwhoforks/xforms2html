package org.ict4h.forms.data;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

public class EnketoResult {
    private String transform;

    public EnketoResult(String transform) {
        this.transform = transform;
    }

    public String getForm() throws DocumentException {
        if (!hasResult()) return "";
        org.dom4j.Document document = new SAXReader().read(new StringReader(transform));
        return document.getRootElement().element("form").asXML();
    }

    public String getModel() throws DocumentException {
        if (!hasResult()) return "";
        final Document document =  new SAXReader().read(new StringReader(transform));
        return document.getRootElement().element("head").element("model").asXML();
    }

    private boolean hasResult() {
        return transform != null && !transform.isEmpty();
    }

}