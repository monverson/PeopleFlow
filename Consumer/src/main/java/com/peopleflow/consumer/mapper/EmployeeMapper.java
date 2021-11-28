package com.peopleflow.consumer.mapper;

import com.peopleflow.consumer.dto.EmployeeConsumerDto;
import com.peopleflow.consumer.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeConsumerDto employeeConsumerDTOProducer);

    EmployeeConsumerDto toEmployeeDTO(Employee employee);
}
