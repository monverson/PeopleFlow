package com.peopleflow.consumer.service.impl;

import com.peopleflow.consumer.dao.EmployeeRepository;
import com.peopleflow.consumer.dto.EmployeeConsumerDto;
import com.peopleflow.consumer.entity.Employee;
import com.peopleflow.consumer.mapper.EmployeeMapper;
import com.peopleflow.consumer.state.EmployeeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    private EmployeeServiceImpl employeeService;

    private EmployeeMapper employeeMapper;

    @BeforeEach
    public void init() {
        employeeMapper = Mappers.getMapper(EmployeeMapper.class);
        employeeService = new EmployeeServiceImpl(employeeRepository, employeeMapper);
    }

    @Test
    void saveAddedEmployee() {
        //given
        EmployeeConsumerDto employeeConsumerDto = new EmployeeConsumerDto();
        employeeConsumerDto.setId("id");
        Employee employee = new Employee();
        employee.setId("id");
        employee.setEmployeeState(EmployeeState.NEW);
        //when
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeConsumerDto savedEmployee = employeeService.saveAddedEmployee(employeeConsumerDto);
        //then
        assertEquals("id", savedEmployee.getId());
        assertEquals(EmployeeState.NEW, savedEmployee.getEmployeeState());
    }

    @Test
    void saveUpdatedStatus() {
        //given
        EmployeeState state = EmployeeState.ADDED;
        EmployeeConsumerDto employeeToSave = new EmployeeConsumerDto();
        employeeToSave.setId("id");
        employeeToSave.setEmployeeState(state);
        Employee employeeEntity = new Employee();
        employeeEntity.setId("id");
        //when
        when(employeeRepository.findById(eq("id"))).thenReturn(Optional.of(employeeEntity));
        when(employeeRepository.save(any(Employee.class))).thenAnswer((Answer<Employee>) invocationOnMock -> invocationOnMock.getArgument(0));
        EmployeeConsumerDto savedEmployee = employeeService.saveUpdatedStatus(employeeToSave);
        //then
        assertEquals("id", savedEmployee.getId());
        assertEquals(state, savedEmployee.getEmployeeState());
    }
}