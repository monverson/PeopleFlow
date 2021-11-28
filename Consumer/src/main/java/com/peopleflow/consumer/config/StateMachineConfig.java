package com.peopleflow.consumer.config;

import com.peopleflow.consumer.action.AddEmployeeAction;
import com.peopleflow.consumer.action.EmployeeGuardAction;
import com.peopleflow.consumer.action.UpdateEmployeeAction;
import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.service.EmployeeService;
import com.peopleflow.consumer.state.EmployeeState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;


@Configuration
@AllArgsConstructor
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<EmployeeState, EmployeeEvent> {

    private EmployeeService employeeService;

    @Override
    public void configure(StateMachineConfigurationConfigurer<EmployeeState, EmployeeEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<EmployeeState, EmployeeEvent> states)
            throws Exception {

        states.withStates()
                .initial(EmployeeState.NEW)
                .state(EmployeeState.ADDED)
                .state(EmployeeState.IN_CHECK)
                .end(EmployeeState.ACTIVE);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EmployeeState, EmployeeEvent> transitions) throws Exception {
        transitions

                .withExternal()
                .source(EmployeeState.NEW)
                .target(EmployeeState.ADDED)
                .event(EmployeeEvent.ADD)
                .action(addEmployeeAction())
                .and()

                .withExternal()
                .source(EmployeeState.IN_CHECK)
                .target(EmployeeState.ACTIVE)
                .event(EmployeeEvent.ACTIVATE)
                .guard(employeeGuardAction())
                .action(updateEmployeeAction());

    }

    @Bean
    public Action<EmployeeState, EmployeeEvent> addEmployeeAction() {
        return new AddEmployeeAction(employeeService);
    }

    @Bean
    public Action<EmployeeState, EmployeeEvent> updateEmployeeAction() {
        return new UpdateEmployeeAction(employeeService);
    }

    @Bean
    public Guard<EmployeeState, EmployeeEvent> employeeGuardAction() {
        return new EmployeeGuardAction();
    }




}
