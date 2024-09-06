package ru.itpark.chatservice.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itpark.chatservice.domain.message.Message;
import ru.itpark.chatservice.presentation.message.dto.commands.CreateMessageCommand;
import ru.itpark.chatservice.presentation.message.dto.responses.MessageResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    
    @Mapping(target = "chatId", expression = "java(message.getChat().getId())")
    MessageResponse toResponse(Message message);
    
    List<MessageResponse> toListResponse(List<Message> messages);
    
    @Mapping(target = "uuid",
    expression = "java(java.util.UUID.fromString(messageResponse.getUuid()))")
    Message toEntity(CreateMessageCommand messageResponse);
    
    
}
