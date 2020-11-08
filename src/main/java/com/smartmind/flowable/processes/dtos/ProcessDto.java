package com.smartmind.flowable.processes.dtos;

public class ProcessDto {
    private String key;
    private String name;
    private int version;

    public ProcessDto(String key, String name, int version) {
        this.key = key;
        this.name = name;
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
