package ru.itpark.chatservice.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itpark.chatservice.domain.chat.Chat;
import ru.itpark.chatservice.presentation.chat.dto.commands.CreateChatCommand;
import ru.itpark.chatservice.presentation.chat.dto.responses.ChatResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    @Mapping(target = "chatId", source = "id")
    ChatResponse toResponse(Chat chat);
    Chat toEntity(CreateChatCommand createChatCommand);
    List<ChatResponse> toListResponse(List<Chat> chats);

}
