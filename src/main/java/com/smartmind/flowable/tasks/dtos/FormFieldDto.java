package com.smartmind.flowable.tasks.dtos;

import java.util.Map;

public class FormFieldDto {
    private String id;
    private String name;
    private String type;
    private Object value;
    private boolean required;
    private boolean readOnly;
    private boolean overrideId;
    private String placeholder;
    private Map<String, Object> params;

    public FormFieldDto(String id, String name, String type, Object value, boolean required, boolean readOnly,
                        boolean overrideId, String placeholder, Map<String, Object> params) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.required = required;
        this.readOnly = readOnly;
        this.overrideId = overrideId;
        this.placeholder = placeholder;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isOverrideId() {
        return overrideId;
    }

    public void setOverrideId(boolean overrideId) {
        this.overrideId = overrideId;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
