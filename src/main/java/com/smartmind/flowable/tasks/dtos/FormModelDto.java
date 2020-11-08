package com.smartmind.flowable.tasks.dtos;

import java.util.List;

public class FormModelDto {
    private String name;
    private String key;
    private int version;
    private String description;
    private List<FormFieldDto> fields;
    private List<FormOutcomeDto> outcomes;
    private String outcomeVariableName;

    public FormModelDto(){}

    public FormModelDto(String name, String key, int version, String description, List<FormFieldDto> fields,
                        List<FormOutcomeDto> outcomes, String outcomeVariableName) {
        this.name = name;
        this.key = key;
        this.version = version;
        this.description = description;
        this.fields = fields;
        this.outcomes = outcomes;
        this.outcomeVariableName = outcomeVariableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FormFieldDto> getFields() {
        return fields;
    }

    public void setFields(List<FormFieldDto> fields) {
        this.fields = fields;
    }

    public List<FormOutcomeDto> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<FormOutcomeDto> outcomes) {
        this.outcomes = outcomes;
    }

    public String getOutcomeVariableName() {
        return outcomeVariableName;
    }

    public void setOutcomeVariableName(String outcomeVariableName) {
        this.outcomeVariableName = outcomeVariableName;
    }
}
