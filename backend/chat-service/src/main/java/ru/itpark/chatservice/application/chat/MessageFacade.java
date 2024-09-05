package ru.itpark.chatservice.application.chat;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.chatservice.application.chat.services.ChatService;
import ru.itpark.chatservice.application.chat.services.MessageService;
import ru.itpark.chatservice.domain.chat.Chat;
import ru.itpark.chatservice.presentation.chat.dto.responses.ChatResponse;
import ru.itpark.chatservice.presentation.message.dto.commands.CreateMessageCommand;

@Service
@RequiredArgsConstructor
public class MessageFacade {

    private final MessageService messageService;
    private final ChatService chatService;

    @Transactional
    public void saveMessage(CreateMessageCommand message) {

        Chat chat = chatService.getChatEntity(message.getChatId());
        if (chat == null) {
            throw new EntityNotFoundException("Чат не найден");
        }

        messageService.saveMessage(message, chat);

    }
}
