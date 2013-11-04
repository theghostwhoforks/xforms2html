package org.ict4h.forms.transformer;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.ict4h.forms.data.CompositeEnketoResult;
import org.ict4h.forms.transformer.impl.ModelXmlToJsonTransformerImpl;
import org.ict4h.forms.transformer.pipeline.XslTransformPipeline;
import org.ict4h.forms.transformer.pipeline.factory.XslTransformPipelineFactory;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ModelXmlToJsonTransformerTest {

    @Test
    public void shouldTransformXmlToHtml5() throws TransformerException, IOException, DocumentException {
        XslTransformPipeline pipeline = XslTransformPipelineFactory.pipelineForOpenRosaToFormDefinitionJson();
        final ModelXmlToJsonTransformerImpl transformer = new ModelXmlToJsonTransformerImpl(pipeline, new TransformerFactoryImpl());
        final CompositeEnketoResult result = (CompositeEnketoResult) transformer.transform(getXForm());
        assertNotNull(result.getModelJson());
    }

    private String getXForm() throws IOException {
        return  IOUtils.toString(this.getClass().getResourceAsStream("/test_open_rosa_model.xml"));
    }
}
