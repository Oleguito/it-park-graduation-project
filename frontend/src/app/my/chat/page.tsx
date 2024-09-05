"use client";

import ChatsList from "@/components/chat/chatslist/ChatsList";
import ChatComponent from "@/components/chat/component/ChatComponent";
import React from "react";


const ChatPage: React.FC = () => {
  

  return (
    <div>
      <div>ChatPage</div>
      <div>
        ▬ Сервис Чата и Коммуникаций Функционал: Встроенные чаты для общения
        команды, уведомления о новых сообщениях и событиях. Технологии:
        WebSockets для реального времени, Kafka для хранения истории чатов.
      </div>
      <div className="flex">
        <ChatsList items={[
          {id: 1, title: "Chat 1", lastMessage: "Hello", lastMessageDate: "2020-12-12", lastMessageAuthor: "Vasya"},
          {id: 2, title: "Chat 2", lastMessage: "Hello", lastMessageDate: "2020-12-12", lastMessageAuthor: "Vasya"},
          {id: 3, title: "Chat 3", lastMessage: "Hello", lastMessageDate: "2020-12-12", lastMessageAuthor: "Vasya"},
        ]} />
        <ChatComponent chatId={1234234} />
      </div>
    </div>
  );
};

export default ChatPage;
