package org.ict4h.forms.transformer;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.ict4h.forms.data.EnketoResult;
import org.ict4h.forms.transformer.impl.XmlToHtml5TransformerImpl;
import org.ict4h.forms.transformer.pipeline.XslTransformPipeline;
import org.ict4h.forms.transformer.pipeline.factory.XslTransformPipelineFactory;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static junit.framework.Assert.assertNotNull;

public class XmlToHtml5TransformerTest {

    @Test
    public void shouldTransformXmlToHtml5() throws TransformerException, IOException, DocumentException {
        final XslTransformPipeline pipeline = XslTransformPipelineFactory.pipelineForXFormToHtml5();
        final XmlToHtml5TransformerImpl transformer = new XmlToHtml5TransformerImpl(pipeline, new TransformerFactoryImpl());
        final String xForm = getXForm();
        final EnketoResult enketoResult = transformer.transform(xForm);
        assertNotNull(enketoResult.getForm());
    }

    private String getXForm() throws IOException {
        return  IOUtils.toString(this.getClass().getResourceAsStream("/test_xform.xml"));
    }
}
