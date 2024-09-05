package ru.itpark.chatservice.presentation.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.chatservice.application.chat.services.MessageService;
import ru.itpark.chatservice.presentation.message.dto.responses.MessageResponse;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<MessageResponse> getMessages(@RequestParam Long chatId) {
        return messageService.getMessages(chatId);
    }

}
