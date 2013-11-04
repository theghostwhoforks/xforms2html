package org.ict4h.forms.domain;

public class Form {
    private final String formXml;
    private final String modelXml;
    private final String formDefinition;

    public Form(String formXml, String modelXml, String formDefinition) {
        this.formXml = formXml;
        this.modelXml = modelXml;
        this.formDefinition = formDefinition;
    }

    public String getFormXml() {
        return formXml;
    }

    public String getModelXml() {
        return modelXml;
    }

    public String getFormDefinition() {
        return formDefinition;
    }
}
