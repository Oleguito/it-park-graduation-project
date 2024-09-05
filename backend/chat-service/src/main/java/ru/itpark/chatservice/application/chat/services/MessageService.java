package ru.itpark.chatservice.application.chat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itpark.chatservice.domain.chat.Chat;
import ru.itpark.chatservice.domain.message.Message;
import ru.itpark.chatservice.infrastructure.mappers.MessageMapper;
import ru.itpark.chatservice.infrastructure.repo.MessageRepo;
import ru.itpark.chatservice.presentation.message.dto.commands.CreateMessageCommand;
import ru.itpark.chatservice.presentation.message.dto.responses.MessageResponse;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepo messageRepo;
    private final MessageMapper messageMapper;

    public List<MessageResponse> getMessages(Long chatId) {
        List<Message> findedMessages = messageRepo.findByChat_id(chatId);
        return messageMapper.toListResponse(findedMessages);
    }

    public void saveMessage(CreateMessageCommand message, Chat chat) {

        Message newMessage = messageMapper.toEntity(message);
        newMessage.setChat(chat);
        messageRepo.save(newMessage);

    }

}
