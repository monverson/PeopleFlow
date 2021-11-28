package com.peopleflow.consumer.entity;

import com.peopleflow.consumer.state.EmployeeState;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "phoneNumber")
    private Long phoneNumber;
    @Column(name = "employeeState")
    EmployeeState employeeState;

}

