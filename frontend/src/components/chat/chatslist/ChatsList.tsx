'use client'

import { ChatResponse } from "@/types/chat/chat";
import { useRef } from "react";

export type Props = {
  items: ChatResponse[],
  callback: (chatId: number) => void
}

const ChatsList: React.FC<Props> = (props: Props) => {

  const handleClick = (chatId: number) => {
    props.callback(chatId)
  }

  return (
    <div className="w-1/4">
      <div>ChatsList</div>
      {props.items.map(item => {
        return <div key={item.chatId} className="mt-2 mb-2" onClick={() => handleClick(item.chatId)}>{item.projectName}</div>;
      })}
    </div>
  );
}

export default ChatsList