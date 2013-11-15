package org.ict4h.forms.transformer;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.ict4h.forms.data.FormDefinitionEnketoResult;
import org.ict4h.forms.transformer.impl.ModelToFormDefinitionTransformer;
import org.ict4h.forms.transformer.pipeline.XslTransformPipeline;
import org.ict4h.forms.transformer.pipeline.factory.XslTransformPipelineFactory;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ModelToFormDefinitionTransformerTest {

    //TODO:- Deserialise and test?
    @Test
    public void shouldTransformXmlToHtml5() throws TransformerException, IOException, DocumentException {
        XslTransformPipeline pipeline = XslTransformPipelineFactory.pipelineForOpenRosaToFormDefinitionJson();
        final XmlTransformer transformer = new ModelToFormDefinitionTransformer(pipeline, new TransformerFactoryImpl());
        final FormDefinitionEnketoResult result = transformer.transform(FormDefinitionEnketoResult.class,getXForm());
        assertNotNull(result.getModelJson());
    }

    private String getXForm() throws IOException {
        return  IOUtils.toString(this.getClass().getResourceAsStream("/test_open_rosa_model.xml"));
    }
}
