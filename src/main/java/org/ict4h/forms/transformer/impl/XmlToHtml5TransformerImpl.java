package org.ict4h.forms.transformer.impl;

import org.apache.commons.io.FileUtils;
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

public class XmlToHtml5TransformerImpl implements XmlToHtml5Transformer{
    private final XslTransformPipeline xslTransformPipeline;
    private SAXTransformerFactory transformerFactory;

    public XmlToHtml5TransformerImpl(XslTransformPipeline xslTransformPipeline, TransformerFactory transformerFactory) {
        this.xslTransformPipeline = xslTransformPipeline;
        this.transformerFactory = (SAXTransformerFactory) transformerFactory;
        transformerFactory.setAttribute("http://saxon.sf.net/feature/version-warning", Boolean.FALSE);
    }

    @Override
    public EnketoResult transform(String xFormXml) throws TransformerException, IOException {
        final Stack<File> transformations = xslTransformPipeline.get();
        if(transformations.empty()){
            return new EnketoResult("");
        }

        //Hush. JAXP black magic ensues.Templates, handlers and transformers galore.
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
        return new EnketoResult(writer.getBuffer().toString());
    }

    private File createTempFile(String xml) throws IOException {
        final File tempFile = File.createTempFile("enketo", ".xml");
        FileUtils.writeStringToFile(tempFile,xml);
        return tempFile;
    }
}
