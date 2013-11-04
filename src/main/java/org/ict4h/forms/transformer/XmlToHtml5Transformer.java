package org.ict4h.forms.transformer;

import org.dom4j.DocumentException;
import org.ict4h.forms.data.EnketoResult;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface XmlToHtml5Transformer {
    EnketoResult transform(String xFormXml) throws TransformerException, IOException, DocumentException;
}
