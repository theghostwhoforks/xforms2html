package org.ict4h.forms.transformer.pipeline;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class XslTransformPipelineImpl implements XslTransformPipeline{
    private Queue<File> transformations;

    public XslTransformPipelineImpl() {
        this.transformations = new LinkedList<File>();
    }

    @Override
    public XslTransformPipeline push(File transformation) {
        transformations.add(transformation);
        return this;
    }

    @Override
    public Stack<File> get() {
        Queue<File> transformationDup = new LinkedList<File>();
        Stack<File> stackOfTransformations = new Stack<File>();
        while (!this.transformations.isEmpty()){
            final File transformation = transformations.poll();
            stackOfTransformations.add(transformation);
            transformationDup.add(transformation);
        }
        //Because once consumed, the head pointer moves to the next cons cell
        this.transformations = transformationDup;
        return stackOfTransformations;
    }
}
