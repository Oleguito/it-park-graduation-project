import { ChatMessageAlignment } from "@/types/chat/chat";
import { v4 as uuidv4 } from "uuid";

export type Props = {
  authorNickname: string;
  message: string;
  alignment: ChatMessageAlignment;
};

const ChatMessage = (props: Props) => {
  
  const alignment: string =
    props.alignment == "left" ? "text-left" : "text-right";
  
  return (
    <div className="rounded m-[3px] mt-4 mr-2 ml-2" key={uuidv4()}>
      <div className={`${alignment}`} key={uuidv4()}>{props.authorNickname}</div>
      <div className={`${alignment}`} key={uuidv4()}>{props.message}</div>
    </div>
  );
};

export default ChatMessage;
