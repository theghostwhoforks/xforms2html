package org.ict4h.forms.transformer.impl;

import org.ict4h.forms.data.EnketoResult;
import org.ict4h.forms.transformer.XmlToHtml5Transformer;
import org.ict4h.forms.transformer.pipeline.XslTransformPipeline;

import java.io.File;
import java.util.Stack;

public class XmlToHtml5TransformerImpl implements XmlToHtml5Transformer{
    private final XslTransformPipeline xslTransformPipeline;

    public XmlToHtml5TransformerImpl(XslTransformPipeline xslTransformPipeline) {
        this.xslTransformPipeline = xslTransformPipeline;
    }

    @Override
    public EnketoResult transform(String xFormXml) {
        final Stack<File> transformations = xslTransformPipeline.get();
        if(transformations.empty()){
            return new EnketoResult("");
        }
        return new EnketoResult("");
    }
}
