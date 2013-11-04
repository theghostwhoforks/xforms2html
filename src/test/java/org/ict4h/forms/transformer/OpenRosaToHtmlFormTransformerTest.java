package org.ict4h.forms.transformer;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.ict4h.forms.data.EnketoResult;
import org.ict4h.forms.transformer.impl.OpenRosaToHtmlFormTransformer;
import org.ict4h.forms.transformer.pipeline.XslTransformPipeline;
import org.ict4h.forms.transformer.pipeline.factory.XslTransformPipelineFactory;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static junit.framework.Assert.assertNotNull;

public class OpenRosaToHtmlFormTransformerTest {

    @Test
    public void shouldTransformXmlToHtml5() throws TransformerException, IOException, DocumentException {
        final XslTransformPipeline pipeline = XslTransformPipelineFactory.pipelineForOpenRosaToHtml5();
        final XmlTransformer transformer = new OpenRosaToHtmlFormTransformer(pipeline, new TransformerFactoryImpl());
        final String xForm = getXForm();
        final EnketoResult enketoResult = transformer.transform(xForm);
        assertNotNull(enketoResult.getForm());
    }

    @Test
    public void shouldTransformXmlFormToModelXml() throws DocumentException, TransformerException, IOException {
        final XslTransformPipeline pipeline = XslTransformPipelineFactory.pipelineForOpenRosaToModelXml();
        final XmlTransformer transformer = new OpenRosaToHtmlFormTransformer(pipeline, new TransformerFactoryImpl());
        final String xForm = getXForm();
        final EnketoResult enketoResult = transformer.transform(xForm);
        assertNotNull(enketoResult.getModel());
    }

    private String getXForm() throws IOException {
        return  IOUtils.toString(this.getClass().getResourceAsStream("/test_xhtml.xml"));
    }
}
