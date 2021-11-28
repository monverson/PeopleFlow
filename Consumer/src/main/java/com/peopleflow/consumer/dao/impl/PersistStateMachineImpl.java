package com.peopleflow.consumer.dao.impl;

import com.peopleflow.consumer.dao.PersistStateMachine;
import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.state.EmployeeState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class PersistStateMachineImpl implements PersistStateMachine {

    private final StateMachinePersister<EmployeeState, EmployeeEvent, String> stateMachinePersister;

    private final StateMachineFactory<EmployeeState, EmployeeEvent> stateMachineFactory;

    @Override
    public StateMachine<EmployeeState, EmployeeEvent> restoreStateMachineByEmployeeID(String employeeId) {
        StateMachine<EmployeeState, EmployeeEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            return stateMachinePersister.restore(stateMachine, employeeId);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void saveStateMachine(String employeeId, StateMachine<EmployeeState, EmployeeEvent> stateMachine) {
        try {
            stateMachinePersister.persist(stateMachine, employeeId);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
