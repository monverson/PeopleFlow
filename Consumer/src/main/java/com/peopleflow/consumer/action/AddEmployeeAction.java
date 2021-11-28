package com.peopleflow.consumer.action;

import com.peopleflow.consumer.dto.EmployeeConsumerDto;
import com.peopleflow.consumer.event.EmployeeEvent;
import com.peopleflow.consumer.service.EmployeeService;
import com.peopleflow.consumer.state.EmployeeState;
import com.peopleflow.consumer.util.StateMachineUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@AllArgsConstructor
@Slf4j
public class AddEmployeeAction implements Action<EmployeeState, EmployeeEvent> {

    private EmployeeService employeeService;

    @Override
    public void execute(StateContext<EmployeeState, EmployeeEvent> stateContext) {
        EmployeeConsumerDto employee = StateMachineUtil.getEmployee(stateContext.getStateMachine());
        employee.setEmployeeState(EmployeeState.ADDED);
        log.info("Create employee '{}'", employee.getId());
        employeeService.saveAddedEmployee(employee);
    }
}
