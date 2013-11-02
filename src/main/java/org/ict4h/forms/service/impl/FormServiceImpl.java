package org.ict4h.forms.service.impl;

import org.ict4h.forms.data.CompositeEnketoResult;
import org.ict4h.forms.service.FormService;
import org.ict4h.forms.transformer.XmlToHtml5Transformer;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class FormServiceImpl implements FormService{

    private final XmlToHtml5Transformer modelToJsonTransformer;

    public FormServiceImpl(XmlToHtml5Transformer modelToJsonTransformer) {
        this.modelToJsonTransformer = modelToJsonTransformer;
    }

    @Override
    public CompositeEnketoResult create(String xml) throws TransformerException, IOException {
        return (CompositeEnketoResult) modelToJsonTransformer.transform(xml);
    }
}
