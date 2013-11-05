package org.ict4h.forms.transformer.impl;

import org.dom4j.DocumentException;
import org.ict4h.forms.data.EnketoResult;
import org.ict4h.forms.data.FormEnketoResult;
import org.ict4h.forms.data.ModelEnketoResult;
import org.ict4h.forms.transformer.XmlTransformer;
import org.ict4h.forms.transformer.pipeline.XslTransformPipeline;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Stack;

import static org.ict4h.forms.util.FileUtils.createTempFile;

public class OpenRosaToHtmlFormTransformer implements XmlTransformer {
    private final XslTransformPipeline xslTransformPipeline;
    private SAXTransformerFactory transformerFactory;

    public OpenRosaToHtmlFormTransformer(XslTransformPipeline xslTransformPipeline, TransformerFactory transformerFactory) {
        this.xslTransformPipeline = xslTransformPipeline;
        this.transformerFactory = (SAXTransformerFactory) transformerFactory;
        transformerFactory.setAttribute("http://saxon.sf.net/feature/version-warning", Boolean.FALSE);
    }

    @Override
    public <T extends EnketoResult> T transform(Class<T> clazz, String xFormXml) throws TransformerException, IOException, DocumentException {
        final Stack<File> transformations = xslTransformPipeline.get();
        if(transformations.empty()){
            return (T) createInstance(clazz,"");
        }

        StringWriter writer = new StringWriter();
        Result intermediateResult = new StreamResult(writer);

        while (!transformations.isEmpty()) {
            Templates templates = transformerFactory.newTemplates(new StreamSource(transformations.pop()));
            TransformerHandler transformerHandler = transformerFactory.newTransformerHandler(templates);
            transformerHandler.setResult(intermediateResult);
            intermediateResult = new SAXResult(transformerHandler);
        }

        File inputFile = createTempFile(xFormXml);
        Transformer transformer = transformerFactory.newTransformer();
        try {
            transformer.transform(new StreamSource(inputFile), intermediateResult);
        } finally {
            if(inputFile != null){
                inputFile.deleteOnExit();
            }
        }
        return (T) createInstance(clazz, writer.getBuffer().toString());
    }

    private <T extends EnketoResult> EnketoResult createInstance(Class<T> clazz, String transform) {
        if(clazz.isAssignableFrom(FormEnketoResult.class))
            return  new FormEnketoResult(transform);
        else
        {
            return new ModelEnketoResult(transform);
        }
    }
}
