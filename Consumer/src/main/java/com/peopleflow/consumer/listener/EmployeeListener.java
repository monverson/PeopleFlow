package com.peopleflow.consumer.listener;

import com.peopleflow.consumer.dto.EmployeeConsumerDto;
import com.peopleflow.consumer.processor.StateMachineProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@AllArgsConstructor
public class EmployeeListener {

    private StateMachineProcessor stateMachineProcessor;

    @KafkaListener(topics = "${kafka.peopleflow.topic}")
    public void listenEmployeeEvent(EmployeeConsumerDto employee) {
        log.info("Start event for employee '{}'", employee.getId());
        try {
            stateMachineProcessor.processStateMachine(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
