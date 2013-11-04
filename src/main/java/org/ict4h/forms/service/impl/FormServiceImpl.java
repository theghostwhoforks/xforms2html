package org.ict4h.forms.service.impl;

import org.dom4j.DocumentException;
import org.ict4h.forms.data.CompositeEnketoResult;
import org.ict4h.forms.service.FormService;
import org.ict4h.forms.transformer.XmlTransformer;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class FormServiceImpl implements FormService {

    private final XmlTransformer modelToJsonTransformer;

    public FormServiceImpl(XmlTransformer modelToJsonTransformer) {
        this.modelToJsonTransformer = modelToJsonTransformer;
    }

    @Override
    public CompositeEnketoResult create(String xml) throws TransformerException, IOException, DocumentException {
        return (CompositeEnketoResult) modelToJsonTransformer.transform(xml);
    }
}
