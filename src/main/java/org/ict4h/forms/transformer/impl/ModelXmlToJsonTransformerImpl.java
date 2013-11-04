package org.ict4h.forms.transformer.impl;

import org.dom4j.DocumentException;
import org.ict4h.forms.data.CompositeEnketoResult;
import org.ict4h.forms.data.EnketoResult;
import org.ict4h.forms.transformer.XmlToHtml5Transformer;
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

public class ModelXmlToJsonTransformerImpl implements XmlToHtml5Transformer {

    private final XslTransformPipeline pipeline;
    private final SAXTransformerFactory transformerFactory;

    public ModelXmlToJsonTransformerImpl(XslTransformPipeline pipeline, TransformerFactory transformerFactory) {
        this.pipeline = pipeline;
        this.transformerFactory = (SAXTransformerFactory) transformerFactory;
    }

    @Override
    public EnketoResult transform(String xFormXml) throws TransformerException, IOException, DocumentException {
        final Stack<File> transformations = pipeline.get();
        if(transformations.empty()){
            return new EnketoResult("");
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
            inputFile.delete();
        }
        return new CompositeEnketoResult(xFormXml, writer.getBuffer().toString());
    }
}