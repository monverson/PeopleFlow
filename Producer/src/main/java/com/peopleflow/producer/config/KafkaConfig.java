package com.peopleflow.producer.config;

import com.peopleflow.producer.dto.EmployeeProducerDto;
import com.peopleflow.producer.service.EmployeeService;
import com.peopleflow.producer.service.impl.EmployeeServiceImpl;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {

    @Value(value = "${kafka.peopleflow.topic}")
    private String topic;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topic)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public EmployeeService employeeService(KafkaTemplate<String, EmployeeProducerDto> employeeTemplate) {
        return new EmployeeServiceImpl(topic, employeeTemplate);
    }
}
