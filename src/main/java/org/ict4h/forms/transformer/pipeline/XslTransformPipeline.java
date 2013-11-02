package org.ict4h.forms.transformer.pipeline;

import java.io.File;
import java.util.Stack;

public interface XslTransformPipeline {
    XslTransformPipeline push(File transformation);
    Stack<File> get();
}
