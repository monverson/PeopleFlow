package com.peopleflow.consumer.service;

import com.peopleflow.consumer.dto.EmployeeConsumerDto;


public interface EmployeeService {

    EmployeeConsumerDto saveAddedEmployee(EmployeeConsumerDto employeeConsumerDtoProducer);

    EmployeeConsumerDto saveUpdatedStatus(EmployeeConsumerDto employeeConsumerDtoProducer);

}
