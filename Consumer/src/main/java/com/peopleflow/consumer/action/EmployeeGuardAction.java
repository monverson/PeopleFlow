package com.peopleflow.consumer.action;

import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.state.EmployeeState;
import com.peopleflow.consumer.util.StateMachineUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

@Slf4j
public class EmployeeGuardAction implements Guard<EmployeeState, EmployeeEvent> {

    @Override
    public boolean evaluate(StateContext<EmployeeState, EmployeeEvent> stateContext) {
        return notEmpty(StateMachineUtil.getEmployeeId(stateContext.getStateMachine()));
    }

    public static boolean notEmpty(final String s) {
        return s != null || !s.trim().isEmpty();
    }
}
