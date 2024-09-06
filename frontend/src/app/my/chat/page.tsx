"use client";

import ChatsList from "@/components/chat/chatslist/ChatsList";
import ChatComponent from "@/components/chat/component/ChatComponent";
import { ChatResponse } from "@/types/chat/chat";
import { ProjectResponse } from "@/types/project/project";
import { getChats } from "@/utils/chat-service/controller/chat-controller";
import { getByEmail } from "@/utils/project-service/project-controller";
import { getDecodedToken } from "@/utils/token";
import React, { useEffect, useState } from "react";

const ChatPage: React.FC = () => {
  const [chats, setChats] = useState([] as ChatResponse[]);
  const [projectIds, setProjectIds] = useState([] as number[]);

  useEffect(() => {
    const token = getDecodedToken();

    const projectIdentifiersList: Promise<void> = getByEmail(token.email)
      .then((response: ProjectResponse[]) => {
        console.log("Получили результат getByEmail: ", response);

        const projectIdentifiers: number[] = response.map((project) => {
          return project.id;
        });

        setProjectIds(projectIdentifiers);

        // return projectIdentifiers;
      })
      .catch((error) => {
        console.log("Произошла ошибка getProjectsFromBackend: ", error);
        // return [] as number[];
      });

    Promise.all([projectIdentifiersList]).then(() => {
      getChats({
        projectIds: projectIds,
      })
        .then((data: ChatResponse[]) => {
          console.log("Получили результат getChats: ", data);
          setChats(data);
        })
        .catch((error) => {
          console.log("Произошла ошибка getChats: ", error);
        });
    });
  }, []);

  return (
    <div>
      <div>ChatPage</div>
      <div>
        ▬ Сервис Чата и Коммуникаций Функционал: Встроенные чаты для общения
        команды, уведомления о новых сообщениях и событиях. Технологии:
        WebSockets для реального времени, Kafka для хранения истории чатов.
      </div>
      <div className="flex">
        <ChatsList
          items={chats}
        />
        <ChatComponent chatId={1234234} />
      </div>
    </div>
  );
};

export default ChatPage;
