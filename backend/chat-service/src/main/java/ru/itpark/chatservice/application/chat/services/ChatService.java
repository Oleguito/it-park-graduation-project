package ru.itpark.chatservice.application.chat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itpark.chatservice.domain.chat.Chat;
import ru.itpark.chatservice.infrastructure.mappers.ChatMapper;
import ru.itpark.chatservice.infrastructure.repo.ChatRepo;
import ru.itpark.chatservice.presentation.chat.dto.commands.CreateChatCommand;
import ru.itpark.chatservice.presentation.chat.dto.responses.ChatResponse;
import ru.itpark.chatservice.presentation.message.dto.queries.ChatSearchQuery;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepo chatRepo;
    private final ChatMapper chatMapper;

    public void createChat(CreateChatCommand chat) {
        Chat newChat = chatMapper.toEntity(chat);
        chatRepo.save(newChat);
    }

    public List<ChatResponse> getChat(ChatSearchQuery projectId) {
        List<Chat> findedChats = chatRepo.findAll(projectId);
        return chatMapper.toListResponse(findedChats);
    }

    public Chat getChatEntity(Long chatId) {
        return chatRepo.findById(chatId).orElse(null);
    }

}
