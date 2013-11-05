package org.ict4h.forms.transformer.impl;

import org.dom4j.DocumentException;
import org.ict4h.forms.data.EnketoResult;
import org.ict4h.forms.data.FormDefinitionEnketoResult;
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

public class ModelToFormDefinitionTransformer implements XmlTransformer {

    private final XslTransformPipeline pipeline;
    private final SAXTransformerFactory transformerFactory;

    public ModelToFormDefinitionTransformer(XslTransformPipeline pipeline, TransformerFactory transformerFactory) {
        this.pipeline = pipeline;
        this.transformerFactory = (SAXTransformerFactory) transformerFactory;
    }

    @Override
    public <T extends EnketoResult> T transform(Class<T> clazz, String xFormXml) throws TransformerException, IOException, DocumentException{
        final Stack<File> transformations = pipeline.get();
        if(transformations.empty()){
            return (T) new FormDefinitionEnketoResult("", "");
        }

        StringWriter writer = new StringWriter();
        Result intermediateResult = new StreamResult(writer);

        while (!transformations.isEmpty()) {
            Templates templates = transformerFactory.newTemplates(new StreamSource(transformations.pop()));
            TransformerHandler transformerHandler = transformerFactory.newTransformerHandler(templates);
            transformerHandler.setResult(intermediateResult);
            intermediateResult = new SAXResult(transformerHandler);
        }

        File inputFile = createTempFile(new ModelEnketoResult(xFormXml).getModel());
        Transformer transformer = transformerFactory.newTransformer();
        try {
            transformer.transform(new StreamSource(inputFile), intermediateResult);
        } finally {
            inputFile.delete();
        }
        return (T) new FormDefinitionEnketoResult(xFormXml, writer.getBuffer().toString());
    }
}