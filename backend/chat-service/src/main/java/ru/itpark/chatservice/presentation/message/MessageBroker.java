package ru.itpark.chatservice.presentation.message;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.itpark.chatservice.application.chat.MessageFacade;
import ru.itpark.chatservice.application.chat.services.ChatService;
import ru.itpark.chatservice.domain.message.Message;
import ru.itpark.chatservice.presentation.message.dto.commands.CreateMessageCommand;

@Component
@RequiredArgsConstructor
@Controller
public class MessageBroker {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageFacade messageFacade;

    @MessageMapping("/chat/{chatId}")
    public void sendMessage(CreateMessageCommand message, @DestinationVariable String chatId) {
        messageFacade.saveMessage(message);
        // Отправляем сообщение в соответствующий топик по идентификатору чата
        messagingTemplate.convertAndSend("/chats/" + chatId, message);
    }

}
