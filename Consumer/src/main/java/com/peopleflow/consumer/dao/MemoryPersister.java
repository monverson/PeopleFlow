package com.peopleflow.consumer.dao;

import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.state.EmployeeState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;


import java.util.HashMap;


public class MemoryPersister implements StateMachinePersist<EmployeeState, EmployeeEvent, String> {

    private final HashMap<String, StateMachineContext<EmployeeState, EmployeeEvent>> contexts = new HashMap<>();

    @Override
    public void write(final StateMachineContext<EmployeeState, EmployeeEvent> context, final String contextObj) {
        contexts.put(contextObj, context);
    }

    @Override
    public StateMachineContext<EmployeeState, EmployeeEvent> read(final String contextObj) {
        return contexts.containsKey(contextObj) ?
                contexts.get(contextObj) :
                new DefaultStateMachineContext<>(EmployeeState.NEW, null, null, null, null, "employeeStateMachine");

    }
}
