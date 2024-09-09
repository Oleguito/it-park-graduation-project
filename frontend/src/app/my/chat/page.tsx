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
  const [currentChatId, setCurrentChatId] = useState(undefined as number);

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
    <div className="w-full flex">
      <div className="mr-4"></div>
      <div className="flex mt-4 w-full">
        <ChatsList
          items={chats}
          callback={(chatId: number) => {
            setCurrentChatId(chatId);
          }}
        />
        <div className="w-4"></div>
        <div className="w-full flex content-center justify-center text-center text-justify-center">
          {currentChatId ? (
            <ChatComponent chatId={currentChatId} />
          ) : (
            <div className="w-full">Нажмите на чат слева, чтобы начать</div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ChatPage;
