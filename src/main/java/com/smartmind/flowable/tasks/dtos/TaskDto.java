package com.smartmind.flowable.tasks.dtos;

import java.util.Date;
import java.util.Map;

public class TaskDto {
    private String id;
    private String name;
    private Date dueDate;
    private String formKey;
    private Map<String, Object> processVariables;

    public TaskDto(String id, String name, Date dueDate, String formKey, Map<String, Object> processVariables) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.formKey = formKey;
        this.processVariables = processVariables;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(Map<String, Object> processVariables) {
        this.processVariables = processVariables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }
}
