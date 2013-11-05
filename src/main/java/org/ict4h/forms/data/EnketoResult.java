package org.ict4h.forms.data;

public abstract class EnketoResult {
    protected String transform;

    public EnketoResult(String transform) {
        this.transform = transform;
    }

    public String getResult(){
        return transform;
    }

    protected boolean hasResult() {
        return transform != null && !transform.isEmpty();
    }
}