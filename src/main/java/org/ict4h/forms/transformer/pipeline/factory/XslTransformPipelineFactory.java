package org.ict4h.forms.transformer.pipeline.factory;

import org.apache.commons.io.FileUtils;

import org.ict4h.forms.transformer.pipeline.XslTransformPipeline;
import org.ict4h.forms.transformer.pipeline.XslTransformPipelineImpl;

import java.io.File;
import static org.ict4h.forms.Constants.*;

public class XslTransformPipelineFactory {
    private final String DIRECTORY_PREFIX = "/xsl";

    private XslTransformPipelineFactory(){}

    public static XslTransformPipeline pipelineForOpenRosaToHtml5(){
        return new XslTransformPipelineFactory().getOpenRosaToHtml5();
    }

    public static XslTransformPipeline pipelineForXFormToHtml5(){
        return new XslTransformPipelineFactory().getXFormToHtml5Pipeline();
    }

    public static XslTransformPipeline pipelineForXFormToModelXml(){
        return new XslTransformPipelineFactory().getXFormToModelXmlPipeline();
    }

    private XslTransformPipeline getOpenRosaToHtml5() {
        return new XslTransformPipelineImpl().push(getFile(OPEN_ROSA_TO_HTML5_FORM_XSL));
    }

    private XslTransformPipeline getXFormToModelXmlPipeline() {
        return new XslTransformPipelineImpl().push(getFile(OPEN_ROSA_TO_MODEL_XSL));
    }

    private XslTransformPipeline getXFormToHtml5Pipeline(){
        return new XslTransformPipelineImpl().push(getFile(XFORM_TO_JAVA_ROSA_XSL))
                                             .push(getFile(JAVA_ROSA_TO_HTML5_XSL));
    }

    private File getFile(String fileName){
        return FileUtils.toFile(this.getClass().getResource(DIRECTORY_PREFIX + "/" + fileName));
    }
}
