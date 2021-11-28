package com.peopleflow.producer.controller;

import com.peopleflow.producer.dto.EmployeeProducerDto;
import com.peopleflow.producer.service.EmployeeService;
import com.peopleflow.producer.state.EmployeeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeProducerDto> addEmployee(@RequestBody EmployeeProducerDto employee) {
        EmployeeProducerDto employeeProducerDto = new EmployeeProducerDto();
        employeeProducerDto.setId(employee.getId());
        employeeProducerDto.setName(employee.getName());
        employeeProducerDto.setLastName(employee.getLastName());
        employeeProducerDto.setAge(employee.getAge());
        employeeProducerDto.setPhoneNumber(employee.getPhoneNumber());
        //default state
        employeeProducerDto.setEmployeeState(EmployeeState.NEW);
        Optional<EmployeeProducerDto> addedEmployee = employeeService.addEmployee(employeeProducerDto);
        return Optional.ofNullable(addedEmployee)
                .map(result -> new ResponseEntity<>(addedEmployee.get(), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @PostMapping("/updateStatus")
    public ResponseEntity<EmployeeState> changeStatus(@RequestParam("employeeId") String employeeId,
                                                      @RequestParam("status") String status) {
        var state = EmployeeState.valueOf(status.trim().toUpperCase());
        Optional<EmployeeState> employeeState = employeeService.changeEmployeeStatus(employeeId, state);
        return Optional.ofNullable(employeeState)
                .map(result -> new ResponseEntity<>(employeeState.get(), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }


}
