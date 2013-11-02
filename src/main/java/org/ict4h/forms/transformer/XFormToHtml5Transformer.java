package org.ict4h.forms.transformer;

import org.ict4h.forms.data.EnketoResult;

public interface XFormToHtml5Transformer {
    EnketoResult transform(String xFormXml);
}
