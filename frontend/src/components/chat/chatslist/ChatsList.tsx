"use client";

import { ChatResponse } from "@/types/chat/chat";

export type Props = {
  items: ChatResponse[];
  callback: (chatId: number) => void;
};

const ChatsList: React.FC<Props> = (props: Props) => {
  const handleClick = (chatId: number) => {
    props.callback(chatId);
  };

  return (
    <div className="w-1/4">
      <div className="font-bold">Список чатов:</div>
      {props.items.map((item) => {
        return (
          <div
            key={item.chatId}
            className={`mt-2 mb-2 hover:bg-slate-500 h-10 text-center text-justify-center border-2 rounded border-t-violet-500`}
            onClick={() => handleClick(item.chatId)}
          >
            {item.projectName}
          </div>
        );
      })}
    </div>
  );
};

export default ChatsList;
