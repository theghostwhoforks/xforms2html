package org.ict4h.forms.transformer;

import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.junit.Ignore;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class ModelXmlToJsonTransformerTest {

    @Ignore
    public void shouldTransformXmlToHtml5() throws TransformerException, IOException, DocumentException {
    }

    private String getXForm() throws IOException {
        return  IOUtils.toString(this.getClass().getResourceAsStream("/test_xhtml.xml"));
    }
}
