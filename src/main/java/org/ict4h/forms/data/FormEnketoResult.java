package org.ict4h.forms.data;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

public class FormEnketoResult extends EnketoResult{

    public FormEnketoResult(String transform) {
        super(transform);
    }


    public String getForm() throws DocumentException {
        if (!hasResult()) return "";
        org.dom4j.Document document = new SAXReader().read(new StringReader(transform));
        return document.getRootElement().element("form").asXML();


    }
}
