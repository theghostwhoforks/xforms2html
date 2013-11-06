package org.ict4h.forms.domain;

public class Form {
    private final String formXml;
    private final String modelXml;
    private final String formDefinition;
    private final String name;

    public Form(String formXml, String modelXml, String formDefinition, String name) {
        this.formXml = formXml;
        this.modelXml = modelXml;
        this.formDefinition = formDefinition;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
