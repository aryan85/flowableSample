package com.smartmind.flowable.processes;

import com.smartmind.flowable.exceptions.ResourceNotFoundException;
import com.smartmind.flowable.processes.dtos.FinishedProcessInstanceDto;
import com.smartmind.flowable.processes.dtos.ProcessDto;
import com.smartmind.flowable.processes.dtos.ProcessInstanceDto;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessComponent {
    private final static Logger log = LoggerFactory.getLogger(ProcessComponent.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;

    @Transactional
    public String startProcess(String key) throws ResourceNotFoundException {
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
            log.info("process with key '{}' successfully started and the instance id is '{}'", key, processInstance.getId());
            return processInstance.getId();
        }catch (FlowableObjectNotFoundException e){
            log.info("there is no process with key '{}'", key);
            throw new ResourceNotFoundException("there is no process with key "+key);
        }
    }

    @Transactional
    public List<ProcessInstanceDto> processInstanceList(String key) {
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processDefinitionKey(key).orderByStartTime().desc().list();
        List<ProcessInstanceDto> processInstanceDtos = new ArrayList<>();
        processInstances.forEach(processInstance -> processInstanceDtos.add(new ProcessInstanceDto(processInstance.getId(), processInstance.getProcessDefinitionKey(),
                processInstance.getStartTime(), processInstance.getStartUserId())));
        return processInstanceDtos;
    }

    @Transactional
    public List<ProcessDto> list() {
        List<ProcessDto> processDtos = new ArrayList<ProcessDto>();
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionName().asc().list();
        processDefinitions.forEach(process -> processDtos.add(new ProcessDto(process.getKey(), process.getName(), process.getVersion())));
        return processDtos;
    }

    @Transactional
    public FinishedProcessInstanceDto getFinishedProcess(String processInstanceId) throws ResourceNotFoundException {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(historicProcessInstance == null){
            log.info("there is no finished processInstance with id '{}'", processInstanceId);
            throw new ResourceNotFoundException("there is no finished processInstance with id: " + processInstanceId);
        }
        return new FinishedProcessInstanceDto(historicProcessInstance.getId(),historicProcessInstance.getProcessDefinitionKey(),historicProcessInstance.getStartTime(),
                historicProcessInstance.getStartUserId(),historicProcessInstance.getEndTime());
    }
}
