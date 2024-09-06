import ChatMessage from '@/components/chat/ChatMessage';
import { ChatMessageType, CreateMessageCommand, MessageResponse } from '@/types/chat/chat';
import { sendMessage, subscribeToChat } from '@/utils/chat-service/chat-service';
import { getMessages } from '@/utils/chat-service/controller/chat-controller';
import { getDecodedToken, JwtPayloadType } from '@/utils/token';
import { useEffect, useRef, useState } from 'react';
import { v4 as uuidv4 } from "uuid";

export type Props = {
  chatId?: number;
}

const ChatComponent: React.FC<Props> = (props: Props) => {
  const [messages, setMessages] = useState([] as MessageResponse[]);
  let decodedToken: JwtPayloadType = getDecodedToken();
  const inputRef = useRef(null);

  useEffect(() => {
    props.chatId &&
    getMessages(props.chatId)
      .then((data: MessageResponse[]) => {
        setMessages(data)
      })
      .catch(error => {
        console.log("Error while fetching messages for chat: ", error)
        return [] as MessageResponse[]
      })

    subscribeToChat(props.chatId, messages, setMessages);

  }, [props.chatId])

  function handleClick(): void {

    let newMsg: CreateMessageCommand = {
      chatId: props.chatId,
      message: inputRef.current.value,
      username: decodedToken.name,
      uuid: uuidv4(),
      userId: 1
    }

    sendMessage(props.chatId, newMsg);

  }

  return (
    <>
      <div className="w-[100%] mt-4">
        <p>Час с Id {props.chatId}</p>
        <div className="chat-area">
          {messages.map((message: MessageResponse) => {
            return (
              <ChatMessage
                key={message.uuid}
                authorNickname={message.username}
                message={message.message}
                alignment={decodedToken.name == message.username ? "right" : 'left'}
              />
            );
          })}
        </div>
        <div className="send-message-area mt-4 flex justify-between">
          <input
            type="text"
            placeholder="Введите ваше сообщение"
            className="w-[100%]"
            ref={inputRef}
          />
          <button className="h-4" onClick={() => handleClick()}>Отправить</button>
        </div>
      </div>
    </>
  );
};

export default ChatComponent