package com.smartmind.flowable.processes.dtos;

import java.util.Date;

public class ProcessInstanceDto {
    private String id;
    private String processDefinitationKey;
    private Date startTime;
    private String startUserId;

    public ProcessInstanceDto(String id, String processDefinitationKey, Date startTime, String startUserId) {
        this.id = id;
        this.processDefinitationKey = processDefinitationKey;
        this.startTime = startTime;
        this.startUserId = startUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessDefinitationKey() {
        return processDefinitationKey;
    }

    public void setProcessDefinitationKey(String processDefinitationKey) {
        this.processDefinitationKey = processDefinitationKey;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }
}
