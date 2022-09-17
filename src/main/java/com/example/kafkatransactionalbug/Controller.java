package com.example.kafkatransactionalbug;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller
{
    private final AccountRepository accountRepository;
    private final KafkaTemplate<String, String> kafkaTemplateTransactional;
    private final String topicName;

    public Controller(AccountRepository accountRepository,
                      KafkaTemplate<String, String> kafkaTemplateTransactional,
                      @Value("${kafka.topicName}") String topicName)
    {
        this.accountRepository = accountRepository;
        this.kafkaTemplateTransactional = kafkaTemplateTransactional;
        this.topicName = topicName;
    }

    @GetMapping("/test")
    @Transactional
    public void asd()
    {
        accountRepository.insert();

        kafkaTemplateTransactional.send(topicName, "example message");

        throw new RuntimeException("Ops");
    }
}
