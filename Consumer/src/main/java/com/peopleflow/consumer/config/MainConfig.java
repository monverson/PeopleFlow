package com.peopleflow.consumer.config;


import com.peopleflow.consumer.dao.EmployeeRepository;
import com.peopleflow.consumer.dao.MemoryPersister;
import com.peopleflow.consumer.dao.PersistStateMachine;
import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.listener.EmployeeListener;
import com.peopleflow.consumer.mapper.EmployeeMapper;
import com.peopleflow.consumer.processor.StateMachineProcessor;
import com.peopleflow.consumer.service.EmployeeService;
import com.peopleflow.consumer.service.impl.EmployeeServiceImpl;
import com.peopleflow.consumer.state.EmployeeState;
import com.peopleflow.consumer.util.StateMachineUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class MainConfig {

    @Bean
    public StateMachineProcessor stateProcessor(PersistStateMachine stateMachineStorage) {
        return new StateMachineProcessor(stateMachineStorage);
    }

    @Bean
    public EmployeeService employeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        return new EmployeeServiceImpl(employeeRepository, employeeMapper);
    }

    @Bean
    public EmployeeListener employeeEventListener(StateMachineProcessor stateProcessor) {
        return new EmployeeListener(stateProcessor);
    }

    @Bean
    public RecordMessageConverter messageConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public StateMachineUtil stateMachineUtils() {
        return new StateMachineUtil();
    }


    @Bean
    public StateMachinePersister<EmployeeState, EmployeeEvent, String> persister() {
        return new DefaultStateMachinePersister(new MemoryPersister());
    }


}

