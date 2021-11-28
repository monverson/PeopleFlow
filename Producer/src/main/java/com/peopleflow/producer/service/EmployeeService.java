package com.peopleflow.producer.service;

import com.peopleflow.producer.dto.EmployeeProducerDto;
import com.peopleflow.producer.state.EmployeeState;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface EmployeeService {

    Optional<EmployeeProducerDto> addEmployee(EmployeeProducerDto employeeConsumerDtoProducer);

    Optional<EmployeeState> changeEmployeeStatus(String employeeId, EmployeeState employeeState);
}
