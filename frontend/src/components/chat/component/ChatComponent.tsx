"use client";

import { Button } from "@/components/ui/button";
import { Settings } from "@/constants/settings";
import { CreateMessageCommand, MessageResponse } from "@/types/chat/chat";
import { getMessages } from "@/utils/chat-service/controller/chat-controller";
import { getDecodedToken, JwtPayloadType } from "@/utils/token";
import { Client } from "@stomp/stompjs"; // Используем современный клиент
import { useEffect, useRef, useState } from "react";
import SockJS from "sockjs-client";
import { v4 as uuidv4 } from "uuid";
import ChatMessage from "../ChatMessage";

export type Props = {
  chatId?: number;
};

const ChatComponent: React.FC<Props> = (props: Props) => {
  const [messages, setMessages] = useState([] as MessageResponse[]);
  const [stompClient, setStompClient] = useState<Client | null>(null);
  let decodedToken: JwtPayloadType = getDecodedToken();
  const inputRef = useRef(null);

  useEffect(() => {
    const socket = new SockJS(Settings.websocket.connectionURL);
    const stompClientLocal = new Client({
      webSocketFactory: () => socket, // Используем SockJS для создания WebSocket
      reconnectDelay: 5000, // Автоматическая попытка переподключения
      onConnect: (frame) => {
        console.log("Connected: " + frame);

        // Подписка на сообщения для данного чата
        stompClientLocal.subscribe(
          `${Settings.websocket.subscribeURL}/${props.chatId}`,
          (message) => {
            let msg: CreateMessageCommand = JSON.parse(
              message.body,
            ) as CreateMessageCommand;
            setMessages((prevMessages) => [...prevMessages, msg]);
            console.log("Новое сообщение получено: ", msg);
          },
        );
      },
      onStompError: (frame) => {
        console.error("Broker reported error: " + frame.headers["message"]);
        console.error("Additional details: " + frame.body);
      },
    });

    stompClientLocal.activate(); // Активируем клиент
    setStompClient(stompClientLocal);

    props.chatId &&
      getMessages(props.chatId)
        .then((data: MessageResponse[]) => setMessages(data))
        .catch((error) =>
          console.log("Error while fetching messages for chat: ", error),
        );

    return () => {
      if (stompClientLocal) {
        stompClientLocal.deactivate(); // Корректное отключение клиента
        console.log("Disconnected from chat");
      }
    };
  }, [props.chatId]);

  const sendMessage = (chatId: number, message: CreateMessageCommand) => {
    if (stompClient && stompClient.connected) {
      stompClient.publish({
        destination: `${Settings.websocket.sendMessageURL}/${chatId}`,
        body: JSON.stringify(message),
      });
      console.log(`Сообщение отправлено, chatId: ${chatId}`);
      inputRef.current.value = "";
    }
  };

  const handleClick = (): void => {
    let newMsg: CreateMessageCommand = {
      chatId: props.chatId,
      message: inputRef.current.value,
      username: decodedToken.name,
      uuid: uuidv4(),
      userId: 1,
    };
    sendMessage(props.chatId, newMsg);
  };

  return (
    <>
      <div className="w-[100%] mt-4 border-t-violet-500">
        <div className="chat-area bg-gradient-to-br from-violet-500 to-violet-800 rounded">
          {messages.length>0 ? (
            messages.map((message: MessageResponse) => (
              <ChatMessage
                key={message.uuid}
                authorNickname={message.username}
                message={message.message}
                alignment={
                  decodedToken.name == message.username ? "right" : "left"
                }
              />
            ))
          ) : (
            <div>Пока нет сообщений!</div>
          )}
        </div>
        <div className="send-message-area mt-4 flex justify-between">
          <input
            type="text"
            placeholder="Введите ваше сообщение"
            className="w-[100%] h-[36px] rounded"
            ref={inputRef}
          />
          <Button
            className="text-center hover:bg-slate-500"
            onClick={handleClick}
          >
            Отправить
          </Button>
        </div>
      </div>
    </>
  );
};

export default ChatComponent;
