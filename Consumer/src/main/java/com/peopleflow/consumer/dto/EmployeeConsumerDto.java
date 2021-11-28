package com.peopleflow.consumer.dto;

import com.peopleflow.consumer.state.EmployeeState;
import lombok.Data;

@Data
public class EmployeeConsumerDto {
    private String id;
    private String name;
    private String lastName;
    private Integer age;
    private Long phoneNumber;
    private EmployeeState employeeState;
}
