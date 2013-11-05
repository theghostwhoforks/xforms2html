package org.ict4h.forms.service.impl;

import org.dom4j.DocumentException;
import org.ict4h.forms.data.FormDefinitionEnketoResult;
import org.ict4h.forms.data.EnketoResult;
import org.ict4h.forms.data.FormEnketoResult;
import org.ict4h.forms.data.ModelEnketoResult;
import org.ict4h.forms.domain.Form;
import org.ict4h.forms.service.FormService;
import org.ict4h.forms.transformer.XmlTransformer;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class FormServiceImpl implements FormService {

    private final XmlTransformer openRosaToHtmlFormTransformer;
    private final XmlTransformer openRosaToModelXmlTransformer;
    private final XmlTransformer modelToJsonTransformer;

    public FormServiceImpl(XmlTransformer openRosaToHtmlFormTransformer, XmlTransformer openRosaToModelXmlTransformer, XmlTransformer modelToFormDefinitionTransformer) {
        this.openRosaToHtmlFormTransformer = openRosaToHtmlFormTransformer;
        this.openRosaToModelXmlTransformer = openRosaToModelXmlTransformer;
        this.modelToJsonTransformer = modelToFormDefinitionTransformer;
    }

    @Override
    public Form create(String xml) throws TransformerException, IOException, DocumentException {
        final String formXml = openRosaToHtmlFormTransformer.transform(FormEnketoResult.class,xml).getForm();
        final ModelEnketoResult modelResult = openRosaToModelXmlTransformer.transform(ModelEnketoResult.class, xml);
        final String modelXml = modelResult.getModel();
        final FormDefinitionEnketoResult formDefinition = modelToJsonTransformer.transform(FormDefinitionEnketoResult.class, modelResult.getResult());
        return new Form(formXml, modelXml, formDefinition.getModelJson());
    }
}
