package ru.itpark.projectservice.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itpark.projectservice.application.service.KafkaService;

@RestController
@RequestMapping("/kafka")
@CrossOrigin
public class TestController {

    @Autowired
    KafkaService kafkaService;


    @PostMapping
    public void kafkaWorks(@RequestParam(name = "message") String message) {

        kafkaService.sendMessage("notification-topic", message);
    }
}
