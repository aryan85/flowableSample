package com.smartmind.flowable.tasks.dtos;

import java.util.Map;

public class FormInputDto {
    private String taskId;
    private String formDefinitionId;
    private String outcome;
    private Map<String,Object> variables;

    public FormInputDto(){}

    public FormInputDto(String taskId, String formDefinitionId, String outcome, Map<String, Object> variables) {
        this.taskId = taskId;
        this.formDefinitionId = formDefinitionId;
        this.outcome = outcome;
        this.variables = variables;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
