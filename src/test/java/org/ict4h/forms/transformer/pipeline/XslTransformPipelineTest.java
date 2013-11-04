package org.ict4h.forms.transformer.pipeline;

import org.ict4h.forms.Constants;
import org.ict4h.forms.transformer.pipeline.factory.XslTransformPipelineFactory;
import org.junit.Test;

import java.io.File;
import java.util.Stack;

import static junit.framework.Assert.assertEquals;

public class XslTransformPipelineTest {

    @Test
    public void shouldEnsureXFormToJavaRosaXSLIsTheFirstElementInThePipelineStack(){
        final Stack<File> stack = XslTransformPipelineFactory.pipelineForXFormToHtml5().get();
        assertEquals(2, stack.size());
        assertEquals(Constants.JAVA_ROSA_TO_HTML5_XSL,stack.pop().getName());
        assertEquals(Constants.XFORM_TO_JAVA_ROSA_XSL,stack.pop().getName());
    }

    @Test
    public void shouldEnsureThatEnketoResultToModelXMLIsTheFirstElementInThePipelineStack(){
        final Stack<File> stack = XslTransformPipelineFactory.pipelineForXFormToModelXml().get();
        assertEquals(1, stack.size());
        assertEquals(Constants.OPEN_ROSA_TO_MODEL_XSL,stack.pop().getName());
    }
}
