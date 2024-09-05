import ChatMessage from '@/components/chat/ChatMessage';
import { ChatMessageType } from '@/types/chat/chat';
import { useEffect, useState } from 'react';

export type Props = {
  chatId?: number;
}

const ChatComponent: React.FC<Props> = (props: Props) => {
  const [messages, setMessages] = useState([] as ChatMessageType[]);

  useEffect(() => {
    setMessages([
      {
        message: "Привет",
        author: "Vasya",
        date: "2020-12-12",
        alignment: "left",
      },
      {
        message: "Как дела??",
        author: "Fedya",
        date: "2020-12-12",
        alignment: "left",
      },
      {
        message: "Да нормально",
        author: "Vasya",
        date: "2020-12-12",
        alignment: "left",
      },
      {
        message: "И у меня",
        author: "Oleg",
        date: "2020-12-12",
        alignment: "right",
      },
    ]);
  }, []);

  return (
    <>
      <div className="w-[100%] mt-4">
        <p>Час с Id {props.chatId}</p>
        <div className="chat-area">
          {messages.map((message) => {
            return (
              <ChatMessage
                authorNickname={message.author}
                message={message.message}
                alignment={message.alignment}
              />
            );
          })}
        </div>
        <div className="send-message-area mt-4">
          <input
            type="text"
            placeholder="Введите ваше сообщение"
            className="w-2/3"
          />
          <button>Отправить</button>
        </div>
      </div>
    </>
  );
};

export default ChatComponent