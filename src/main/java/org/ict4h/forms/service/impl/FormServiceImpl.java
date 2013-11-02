package org.ict4h.forms.service.impl;

import org.ict4h.forms.data.CompositeEnketoResult;
import org.ict4h.forms.service.FormService;
import org.ict4h.forms.transformer.XFormToHtml5Transformer;

public class FormServiceImpl implements FormService{

    private final XFormToHtml5Transformer modelToJsonTransformer;

    public FormServiceImpl(XFormToHtml5Transformer modelToJsonTransformer) {
        this.modelToJsonTransformer = modelToJsonTransformer;
    }

    @Override
    public CompositeEnketoResult create(String xml) {
        return (CompositeEnketoResult) modelToJsonTransformer.transform(xml);
    }
}
