package org.ict4h.forms.data;

public class CompositeEnketoResult extends EnketoResult {
    private final String modelJson;

    public CompositeEnketoResult(String transform, String modelJson) {
        super(transform);
        this.modelJson = modelJson;
    }

    public String getModelJson(){
        return this.modelJson;
    }

}
