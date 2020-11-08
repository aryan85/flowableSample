package com.smartmind.flowable.tasks;

import com.smartmind.flowable.exceptions.GeneralException;
import com.smartmind.flowable.exceptions.ResourceNotFoundException;
import com.smartmind.flowable.tasks.dtos.FormInfoDto;
import com.smartmind.flowable.tasks.dtos.FormInputDto;
import com.smartmind.flowable.tasks.dtos.TaskDto;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.TaskService;
import org.flowable.form.api.FormInfo;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskComponent {

    private final static Logger log = LoggerFactory.getLogger(TaskComponent.class);

    @Autowired
    private TaskService taskService;

    @Transactional
    public FormInfoDto getFormInfo(String taskId) throws ResourceNotFoundException {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            log.info("there is no task and form were found with taskId '{}'", taskId);
            throw new ResourceNotFoundException("there is no such a task with taskId: " + taskId);
        }
        try {
            FormInfo formInfo = taskService.getTaskFormModel(task.getId());
            return new FormInfoDto(formInfo.getId(), formInfo.getName(), formInfo.getDescription(), formInfo.getKey(), formInfo.getVersion(),
                    formInfo.getFormModel());
        }catch (FlowableObjectNotFoundException e){
            log.info("there is no task and form were found with taskId '{}'", taskId);
            throw new ResourceNotFoundException("there is no form with taskId: " + taskId, e);
        }
    }

    @Transactional
    public List<TaskDto> getTasks(String processInstanceId){
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).orderByTaskName().asc().list();
        List<TaskDto> taskDtos = new ArrayList<>();
        tasks.forEach(task -> taskDtos.add(new TaskDto(task.getId(),task.getName(),task.getDueDate(),task.getFormKey(),task.getProcessVariables())));
        return taskDtos;
    }

    @Transactional
    public List<TaskDto> getGroupTasks(String candidateGroup){
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).orderByTaskName().asc().list();
        List<TaskDto> taskDtos = new ArrayList<>();
        tasks.forEach(task -> taskDtos.add(new TaskDto(task.getId(),task.getName(),task.getDueDate(),task.getFormKey(),task.getProcessVariables())));
        return taskDtos;
    }

    @Transactional
    public void complete(String taskId) throws ResourceNotFoundException, GeneralException {
        try {
            taskService.complete(taskId);
            log.info("the task with id '{}' successfully completed",taskId);
        }catch (FlowableObjectNotFoundException e){
            log.info("there is no task and form were found with taskId '{}'", taskId);
            throw new ResourceNotFoundException("there is no such a task with taskId: " + taskId);
        }catch (FlowableException e){
            log.info("exception occured in completing task",e);
            throw new GeneralException("exception occured in completing task", e);
        }
    }

    @Transactional
    public void completeWithForm(FormInputDto formInput) throws ResourceNotFoundException {
        try {
            taskService.completeTaskWithForm(formInput.getTaskId(), formInput.getFormDefinitionId(), formInput.getOutcome(), formInput.getVariables());
        }catch(FlowableObjectNotFoundException e){
            log.info("there is no task and form were found with taskId '{}'", formInput.getTaskId());
            throw new ResourceNotFoundException("there is no such a task with taskId: " + formInput.getTaskId());
        }
    }
}
