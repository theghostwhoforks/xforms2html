package org.ict4h.forms.data;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

public class ModelEnketoResult extends EnketoResult{

    public ModelEnketoResult(String transform) {
        super(transform);
    }

    public String getModel() throws DocumentException {
        if (!hasResult()) return "";
        final Document document =  new SAXReader().read(new StringReader(transform));
        return document.getRootElement().element("model").asXML();
    }
}
