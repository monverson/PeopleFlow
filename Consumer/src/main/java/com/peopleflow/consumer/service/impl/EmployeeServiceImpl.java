package com.peopleflow.consumer.service.impl;

import com.peopleflow.consumer.dao.EmployeeRepository;
import com.peopleflow.consumer.dto.EmployeeConsumerDto;
import com.peopleflow.consumer.entity.Employee;
import com.peopleflow.consumer.exception.EmployeeException;
import com.peopleflow.consumer.mapper.EmployeeMapper;
import com.peopleflow.consumer.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @Transactional
    public EmployeeConsumerDto saveAddedEmployee(EmployeeConsumerDto employeeConsumerDtoProducer) throws RuntimeException {
        var employee = employeeMapper.toEmployee(employeeConsumerDtoProducer);
        employee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeConsumerDto saveUpdatedStatus(EmployeeConsumerDto employeeConsumerDto) {
        try {
            Optional<Employee> employee = employeeRepository.findById(employeeConsumerDto.getId());
            if (employee.isEmpty()) {
                throw new EmployeeException(String.format("No employee found id '%s'", employeeConsumerDto.getId()));
            }
            var savedEmployee = employee.get();
            savedEmployee.setEmployeeState(employeeConsumerDto.getEmployeeState());
            savedEmployee = employeeRepository.save(savedEmployee);
            return employeeMapper.toEmployeeDTO(savedEmployee);
        } catch (EmployeeException exception) {
            throw exception;
        }
    }
}
