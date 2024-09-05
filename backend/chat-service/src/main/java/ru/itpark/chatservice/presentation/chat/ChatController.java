package ru.itpark.chatservice.presentation.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.chatservice.application.chat.services.ChatService;
import ru.itpark.chatservice.presentation.chat.dto.commands.CreateChatCommand;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin
    public void createChat(@RequestBody CreateChatCommand chat) {
        chatService.createChat(chat);
    }

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public void createChat(@RequestParam Long projectId) {
        chatService.getChat(projectId);
    }

}
