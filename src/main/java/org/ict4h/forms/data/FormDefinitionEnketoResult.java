package org.ict4h.forms.data;

public class FormDefinitionEnketoResult extends EnketoResult {
    private final String modelJson;

    public FormDefinitionEnketoResult(String transform, String modelJson) {
        super(transform);
        this.modelJson = modelJson;
    }

    public String getModelJson(){
        return this.modelJson;
    }
}
