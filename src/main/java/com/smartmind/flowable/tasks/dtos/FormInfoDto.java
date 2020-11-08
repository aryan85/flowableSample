package com.smartmind.flowable.tasks.dtos;

import org.flowable.form.api.FormModel;
import org.flowable.form.model.FormField;
import org.flowable.form.model.FormOutcome;
import org.flowable.form.model.SimpleFormModel;

import java.util.ArrayList;
import java.util.List;

public class FormInfoDto {
    private String id;
    private String name;
    private String description;
    private String key;
    private int version;
    private FormModelDto formModelDto;
    private FormModel formModel;

    public FormInfoDto(String id, String name, String description, String key, int version, FormModel formModel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.key = key;
        this.version = version;
        this.formModel = formModel;
        this.formModelDto = convertFormModel(formModel);
    }

    public FormModelDto convertFormModel(FormModel formModel){
        if(formModel == null)
            return new FormModelDto();
        SimpleFormModel simpleFormModel = (SimpleFormModel) getFormModel();

        List<FormField> fields = simpleFormModel.getFields();
        List<FormFieldDto> formFieldDtos = new ArrayList<>();
        if(fields != null) {
            fields.forEach(field -> formFieldDtos.add(new FormFieldDto(field.getId(), field.getName(), field.getType(), field.getValue(),
                    field.isRequired(), field.isReadOnly(), field.isOverrideId(), field.getPlaceholder(), field.getParams())));
        }

        List<FormOutcome> outcomes = simpleFormModel.getOutcomes();
        List<FormOutcomeDto> formOutcomeDtos = new ArrayList<>();
        if (outcomes != null){
            outcomes.forEach(formOutcome -> formOutcomeDtos.add(new FormOutcomeDto(formOutcome.getId(),formOutcome.getName())));
        }

        return new FormModelDto(simpleFormModel.getName(),simpleFormModel.getKey(),simpleFormModel.getVersion(),simpleFormModel.getDescription(),
                formFieldDtos,formOutcomeDtos,simpleFormModel.getOutcomeVariableName());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public FormModelDto getFormModelDto() {
        return formModelDto;
    }

    public void setFormModelDto(FormModelDto formModel) {
        this.formModelDto = formModel;
    }

    public FormModel getFormModel() {
        return formModel;
    }

    public void setFormModel(FormModel formModel) {
        this.formModel = formModel;
    }
}
