package org.ict4h.forms.service;

import org.ict4h.forms.data.CompositeEnketoResult;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface FormService {
    CompositeEnketoResult create(String xml) throws TransformerException, IOException;
}
