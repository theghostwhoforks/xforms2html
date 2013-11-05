package org.ict4h.forms.service;

import org.dom4j.DocumentException;
import org.ict4h.forms.data.FormDefinitionEnketoResult;
import org.ict4h.forms.domain.Form;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface FormService {
    Form create(String xml);
}
