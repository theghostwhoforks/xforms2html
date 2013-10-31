package org.ict4h.forms.service;

import org.ict4h.forms.data.CompositeEnketoResult;

public interface FormService {
    CompositeEnketoResult create(String xml);
}
