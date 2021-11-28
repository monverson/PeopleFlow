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

@Slf4j
@AllArgsConstructor
public class UpdateEmployeeAction implements Action<EmployeeState, EmployeeEvent> {

    private EmployeeService employeeService;

    @Override
    public void execute(StateContext<EmployeeState, EmployeeEvent> stateContext) {
        String employeeId = StateMachineUtil.getEmployeeId(stateContext.getStateMachine());
        EmployeeState state = stateContext.getTarget().getId();
        EmployeeConsumerDto employee = new EmployeeConsumerDto();
        employee.setEmployeeState(state);
        employee.setId(employeeId);
        log.info("Update state of employee '{}' to '{}'", employeeId, state);
        employeeService.saveUpdatedStatus(employee);
    }
}
