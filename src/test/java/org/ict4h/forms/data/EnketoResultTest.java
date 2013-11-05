package org.ict4h.forms.data;

import org.dom4j.DocumentException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

public class EnketoResultTest {
    @Test(expected = NullPointerException.class)
    public void getForm_shouldEmpty() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException, DocumentException {
        ModelEnketoResult enketoResult = new ModelEnketoResult("<root><form><ul><li/><li/></ul></form></root>");
        assertThat(enketoResult.getModel(), isEmptyOrNullString());

        FormEnketoResult result = new FormEnketoResult("<root><model><x/><y/></model></root>");
        assertThat(result.getForm(), isEmptyOrNullString());
    }

    @Test
    public void getForm_shouldGetForm() throws DocumentException {
        String result = "<root><model><x/><y/></model><form><ul><li/><li/></ul></form></root>";
        FormEnketoResult enketoResult = new FormEnketoResult(result);
        assertThat(enketoResult.getForm(), is("<form><ul><li/><li/></ul></form>"));
    }

    @Test
    public void getForm_shouldGetModel() throws DocumentException {
        String result = "<root><model><x/><y/></model><form><ul><li/><li/></ul></form></root>";
        FormEnketoResult enketoResult = new FormEnketoResult(result);
        assertThat(enketoResult.getForm(), is("<form><ul><li/><li/></ul></form>"));
    }
}
