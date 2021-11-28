package com.peopleflow.producer.dto;

import com.peopleflow.producer.state.EmployeeState;
import lombok.Data;

@Data
public class EmployeeProducerDto {
    private String id;
    private String name;
    private String lastName;
    private Integer age;
    private Long phoneNumber;
    private EmployeeState employeeState;
}
