package com.smartmind.flowable.processes.dtos;

import java.util.Date;

public class FinishedProcessInstanceDto {
    private String id;
    private String processDefinitationKey;
    private Date startTime;
    private String startUserId;
    private Date endTime;

    public FinishedProcessInstanceDto(String id, String processDefinitationKey, Date startTime, String startUserId, Date endTime) {
        this.id = id;
        this.processDefinitationKey = processDefinitationKey;
        this.startTime = startTime;
        this.startUserId = startUserId;
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
