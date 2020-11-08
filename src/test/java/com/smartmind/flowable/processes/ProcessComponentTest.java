package com.smartmind.flowable.processes;

import com.smartmind.flowable.exceptions.ResourceNotFoundException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.persistence.entity.AbstractEntity;
import org.flowable.common.engine.impl.persistence.entity.Entity;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.engine.impl.persistence.entity.HistoricProcessInstanceEntityImpl;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessComponentTest {

    private ProcessComponent processComponent;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private RuntimeService runtimeService;

    @MockBean
    private RepositoryService repositoryService;

    @MockBean
    private HistoryService historyService;

    @Before
    public void init(){
        processComponent = applicationContext.getBean(ProcessComponent.class);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void startProcessWithExceptionTest() throws ResourceNotFoundException {
        Mockito.when(runtimeService.startProcessInstanceByKey("key"))
                .thenThrow(FlowableObjectNotFoundException.class);
        processComponent.startProcess("key");
    }


    @Test
    public void startProcessTest() throws ResourceNotFoundException {
        AbstractEntity processInstanceEntity = new HistoricProcessInstanceEntityImpl();
        processInstanceEntity.setId("123456");
        ExecutionEntityImpl executionEntity = (ExecutionEntityImpl) processInstanceEntity;
        ProcessInstance processInstance = executionEntity;
        Mockito.when(runtimeService.startProcessInstanceByKey("key")).thenReturn(processInstance);
        String id = processComponent.startProcess("key");
        Assert.assertEquals(id,"123456");
    }
}
