package com.smartmind.flowable.web;

import com.smartmind.flowable.exceptions.GeneralException;
import com.smartmind.flowable.exceptions.ResourceNotFoundException;
import com.smartmind.flowable.tasks.TaskComponent;
import com.smartmind.flowable.tasks.dtos.FormInfoDto;
import com.smartmind.flowable.tasks.dtos.FormInputDto;
import com.smartmind.flowable.tasks.dtos.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskComponent taskComponent;

    @RequestMapping(value="/formInfo", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public FormInfoDto getFormInfo(@RequestParam String taskId) {
        try {
            return taskComponent.getFormInfo(taskId);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "task not found", e);
        }
    }

    @RequestMapping(value="/process-task-list", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getTasks(@RequestParam String processInstanceId){
        return taskComponent.getTasks(processInstanceId);
    }

    @RequestMapping(value="/group-task-list", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getGroupTasks(@RequestParam String candidateGroup){
        return taskComponent.getGroupTasks(candidateGroup);
    }

    @RequestMapping(value="/complete", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> complete(@RequestHeader String taskId){
        try {
            taskComponent.complete(taskId);
            return ResponseEntity.ok("task completed successfully");
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "task not found", e);
        } catch (GeneralException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "General error", e);
        }
    }

    @RequestMapping(value="/completeWithForm", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> completeWithForm(@RequestBody FormInputDto formInputDto){
        try {
            taskComponent.completeWithForm(formInputDto);
            return ResponseEntity.ok("task completed successfully");
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found", e);
        }
    }
}
