package org.ict4h.forms.service;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.ict4h.forms.data.FormDefinitionEnketoResult;
import org.ict4h.forms.domain.Form;
import org.ict4h.forms.service.impl.FormServiceImpl;
import org.ict4h.forms.transformer.XmlTransformer;
import org.ict4h.forms.transformer.impl.ModelToFormDefinitionTransformer;
import org.ict4h.forms.transformer.impl.OpenRosaToHtmlFormTransformer;
import org.ict4h.forms.transformer.pipeline.XslTransformPipeline;
import org.ict4h.forms.transformer.pipeline.factory.XslTransformPipelineFactory;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class FormServiceTest {
    @Test
    public void shouldDoSomething() throws IOException, TransformerException, DocumentException {
        XslTransformPipeline openRosaToHtmlPipeline = XslTransformPipelineFactory.pipelineForOpenRosaToHtml5();
        XmlTransformer openRosaToFormXmlTransformer = new OpenRosaToHtmlFormTransformer(openRosaToHtmlPipeline,new TransformerFactoryImpl());
        XmlTransformer openRosaToModelXmlTransformer = new OpenRosaToHtmlFormTransformer(XslTransformPipelineFactory.pipelineForOpenRosaToModelXml(),new TransformerFactoryImpl());
        final XslTransformPipeline openRosaToFormDefinitionPipeline = XslTransformPipelineFactory.pipelineForOpenRosaToFormDefinitionJson();
        XmlTransformer formDefinitionTransformer = new ModelToFormDefinitionTransformer(openRosaToFormDefinitionPipeline,new TransformerFactoryImpl());
        final FormService service = new FormServiceImpl(openRosaToFormXmlTransformer, openRosaToModelXmlTransformer,formDefinitionTransformer);
        final String xml = getResourceAsStream();
        final Form form = service.create(xml);
        assertNotNull(form.getFormXml());
        assertNotNull(form.getModelXml());
        assertNotNull(form.getFormDefinition());
        assertEquals("pregnancy_registration_form_1nov",form.getName());
    }

    private String getResourceAsStream() throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream("/test_xhtml.xml"));
    }
}
