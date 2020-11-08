package com.smartmind.flowable.web;

import com.smartmind.flowable.exceptions.ResourceNotFoundException;
import com.smartmind.flowable.processes.ProcessComponent;
import com.smartmind.flowable.processes.dtos.FinishedProcessInstanceDto;
import com.smartmind.flowable.processes.dtos.ProcessDto;
import com.smartmind.flowable.processes.dtos.ProcessInstanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    private ProcessComponent processComponent;

    @RequestMapping(value="/start", method= RequestMethod.POST)
    public ResponseEntity<String> startProcessInstance(@RequestHeader String key) {
        try {
            return ResponseEntity.ok("processInstanceId : "+processComponent.startProcess(key));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "process not found", e);
        }
    }

    @RequestMapping(value = "/list", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ProcessDto> list(){
        return processComponent.list();
    }

    @RequestMapping(value = "/processInstance/list", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ProcessInstanceDto> processInstanceList(@RequestParam String key){
        return processComponent.processInstanceList(key);
    }

    @RequestMapping(value = "/finishedProcessInstance", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public FinishedProcessInstanceDto getFinishedProcess(@RequestParam String processInstanceId){
        try {
            return processComponent.getFinishedProcess(processInstanceId);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "processInstance not found", e);
        }
    }
}
