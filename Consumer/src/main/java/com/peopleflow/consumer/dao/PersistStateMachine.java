package com.peopleflow.consumer.dao;

import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.state.EmployeeState;
import org.springframework.statemachine.StateMachine;


public interface PersistStateMachine {

    StateMachine<EmployeeState, EmployeeEvent> restoreStateMachineByEmployeeID(String employeeId) throws Exception;

    void saveStateMachine(String employeeId, StateMachine<EmployeeState, EmployeeEvent> stateMachine);
}
