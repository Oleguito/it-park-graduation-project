package ru.itpark.projectservice.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itpark.projectservice.application.service.KafkaService;
import ru.itpark.sharedlib.InvitationMessage;


@RestController
@RequestMapping("/kafka")
@CrossOrigin
public class TestController {

    @Autowired
    KafkaService kafkaService;


    @PostMapping
    public void kafkaWorks(@RequestBody String message) {

        System.out.println("\n\n\nmessage = " + message);
        kafkaService.sendMessage("notification-topic", message);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @CrossOrigin
    public String helloWorld() {
        return "Hello, World!";
    }

    @RequestMapping(value = "/message", method=RequestMethod.POST)
    public void requestMethodName(@RequestBody InvitationMessage invitationMessage) {
        kafkaService.sendNotificationMessage("notification-topic", invitationMessage);
    }
    
}
