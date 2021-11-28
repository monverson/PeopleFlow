package com.peopleflow.consumer.processor;

import com.peopleflow.consumer.dao.PersistStateMachine;
import com.peopleflow.consumer.dto.EmployeeConsumerDto;
import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.state.EmployeeState;
import com.peopleflow.consumer.util.StateMachineUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;

import java.util.stream.Stream;

@Slf4j
@EnableStateMachineFactory
@AllArgsConstructor
public class StateMachineProcessor {

    private PersistStateMachine persistStateMachine;

    public void processStateMachine(EmployeeConsumerDto employee) throws Exception {
        String employeeId = employee.getId();
        log.info("Start process employee '{}'", employeeId);
        StateMachine<EmployeeState, EmployeeEvent> stateMachine = persistStateMachine.restoreStateMachineByEmployeeID(employeeId);
        StateMachineUtil.setEmployee(stateMachine, employee);
        stateMachine.sendEvent(getEventForState(employee.getEmployeeState()));
        persistStateMachine.saveStateMachine(employeeId, stateMachine);
    }

    private EmployeeEvent getEventForState(EmployeeState state) {
        if (Stream.of(new EmployeeState[]{EmployeeState.NEW, EmployeeState.ADDED, EmployeeState.IN_CHECK}).
                anyMatch(employeeState -> state.equals(employeeState))) {
            return EmployeeEvent.ADD;
        }
        return EmployeeEvent.ACTIVATE;
    }
}
