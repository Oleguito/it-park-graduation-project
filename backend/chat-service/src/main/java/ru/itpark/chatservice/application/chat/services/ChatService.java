package ru.itpark.chatservice.application.chat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itpark.chatservice.domain.chat.Chat;
import ru.itpark.chatservice.infrastructure.mappers.ChatMapper;
import ru.itpark.chatservice.infrastructure.repo.ChatRepo;
import ru.itpark.chatservice.presentation.chat.dto.commands.CreateChatCommand;
import ru.itpark.chatservice.presentation.chat.dto.responses.ChatResponse;

@Component
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepo chatRepo;
    private final ChatMapper chatMapper;

    public void createChat(CreateChatCommand chat) {
        Chat newChat = chatMapper.toEntity(chat);
        chatRepo.save(newChat);
    }

    public ChatResponse getChat(Long projectId) {
        Chat findedChat = chatRepo.findByProjectId(projectId);
        return findedChat == null ? ChatResponse.builder().build() : chatMapper.toResponse(findedChat);
    }

    public Chat getChatEntity(Long chatId) {
        return chatRepo.findById(chatId).orElse(null);
    }

}
