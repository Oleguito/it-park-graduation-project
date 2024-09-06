package ru.itpark.chatservice.presentation.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.chatservice.application.chat.services.MessageService;
import ru.itpark.chatservice.presentation.message.dto.responses.MessageResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    
    private final MessageService messageService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<MessageResponse> getMessages(@RequestParam Long chatId) {
        
        
        UUID.fromString("sdf");
        return messageService.getMessages(chatId);
    }
    
}
