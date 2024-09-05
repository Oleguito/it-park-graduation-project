import { ChatMessageAlignment } from "@/types/chat/chat";

export type Props = {
  authorNickname: string;
  message: string;
  alignment: ChatMessageAlignment;
};

const ChatMessage = (props: Props) => {
  
  const alignment: string =
    props.alignment == "left" ? "text-left" : "text-right";
  
  return (
    <div className="rounded m-[3px] mt-4 mr-2 ml-2">
      <div className={`${alignment}`}>{props.authorNickname}</div>
      <div className={`${alignment}`}>{props.message}</div>
    </div>
  );
};

export default ChatMessage;
