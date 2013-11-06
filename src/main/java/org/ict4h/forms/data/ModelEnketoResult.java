package org.ict4h.forms.data;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

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

    public String getName() throws DocumentException{
        if(!hasResult()) return "";
        final Document document = new SAXReader().read(new StringReader(transform));
        return ((DefaultElement)document.getRootElement().element("model").element("instance").elementIterator().next()).getName();
    }
}
