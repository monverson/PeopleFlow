package com.peopleflow.consumer.util;


import com.peopleflow.consumer.dto.EmployeeConsumerDto;
import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.state.EmployeeState;
import org.springframework.statemachine.StateMachine;

public class StateMachineUtil {
    private static final String EMPLOYEE = "employee";

    public static void setEmployee(StateMachine<EmployeeState, EmployeeEvent> stateMachine, EmployeeConsumerDto employee) {
        stateMachine.getExtendedState().getVariables().put(EMPLOYEE, employee);
    }

    public static EmployeeConsumerDto getEmployee(StateMachine<EmployeeState, EmployeeEvent> stateMachine) {
        EmployeeConsumerDto employee = (EmployeeConsumerDto) stateMachine.getExtendedState().getVariables().get(EMPLOYEE);
        return employee;
    }

    public static String getEmployeeId(StateMachine<EmployeeState, EmployeeEvent> stateMachine) {
        EmployeeConsumerDto employee = (EmployeeConsumerDto) stateMachine.getExtendedState().getVariables().get(EMPLOYEE);
        return employee.getId();

    }
}
