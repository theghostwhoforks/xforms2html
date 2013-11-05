package org.ict4h.forms.transformer;

import org.dom4j.DocumentException;
import org.ict4h.forms.data.EnketoResult;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface XmlTransformer {
    <T extends EnketoResult> T transform(Class<T> clazz, String xFormXml) throws TransformerException, IOException, DocumentException;
}
