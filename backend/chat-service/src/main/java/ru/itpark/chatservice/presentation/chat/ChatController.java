package ru.itpark.chatservice.presentation.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.chatservice.application.chat.services.ChatService;
import ru.itpark.chatservice.presentation.chat.dto.commands.CreateChatCommand;
import ru.itpark.chatservice.presentation.chat.dto.responses.ChatResponse;
import ru.itpark.chatservice.presentation.message.dto.queries.ChatSearchQuery;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.POST, path = "/find")
    @CrossOrigin
    @ResponseBody
    public List<ChatResponse> getChats(@RequestBody ChatSearchQuery query) {
        return chatService.getChat(query);
    }

}
