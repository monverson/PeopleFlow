package com.peopleflow.producer.service.impl;

import com.peopleflow.producer.dto.EmployeeProducerDto;
import com.peopleflow.producer.exception.EmployeeException;
import com.peopleflow.producer.service.EmployeeService;
import com.peopleflow.producer.state.EmployeeState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private String topic;

    private KafkaTemplate<String, EmployeeProducerDto> employeeTemplate;

    @Override
    public Optional<EmployeeProducerDto> addEmployee(EmployeeProducerDto employee) {
        startEmployeeEvent(employee);
        return Optional.ofNullable(employee);
    }

    @Override
    public Optional<EmployeeState> changeEmployeeStatus(String employeeId, EmployeeState employeeState) {
        EmployeeProducerDto employeeProducerDto = new EmployeeProducerDto();
        employeeProducerDto.setId(employeeId);
        employeeProducerDto.setEmployeeState(employeeState);
        EmployeeProducerDto employee = startEmployeeEvent(employeeProducerDto);
        return Optional.ofNullable(employee.getEmployeeState());
    }


    private EmployeeProducerDto startEmployeeEvent(EmployeeProducerDto employeeProducerDto) {
        try {
             employeeTemplate.send(topic, employeeProducerDto);
        } catch (EmployeeException exception) {
            log.error(exception.getMessage());
        }
        return employeeProducerDto;
    }
}
