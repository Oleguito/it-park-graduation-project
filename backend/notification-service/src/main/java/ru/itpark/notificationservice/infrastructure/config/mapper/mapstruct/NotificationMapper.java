package ru.itpark.notificationservice.infrastructure.config.mapper.mapstruct;


import org.mapstruct.Mapper;
import ru.itpark.notificationservice.domain.notification.Notification;
import ru.itpark.notificationservice.presentation.notification.dto.command.NotificationCreateCommand;
import ru.itpark.notificationservice.presentation.notification.dto.command.NotificationUpdateCommand;
import ru.itpark.notificationservice.presentation.notification.dto.query.NotificationQuery;

import java.util.List;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationQuery toQuery(Notification notification);

    List<NotificationQuery> toQueryList(List<Notification> notificationList);

    Notification toEntity(NotificationQuery notificationQuery);

    Notification toEntity(NotificationCreateCommand notificationCreateCommand);

    Notification toEntity(NotificationUpdateCommand notificationUpdateCommand);
}


