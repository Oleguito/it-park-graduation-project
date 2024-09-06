import { ChatResponse } from "@/types/chat/chat";

export type Props = {
  items: ChatResponse[]
}

const ChatsList: React.FC<Props> = (props: Props) => {


  return (
    <div className="w-1/4">
      <div>ChatsList</div>
      {props.items.map(item => {
        return <div key={item.chatId} className="mt-2 mb-2">{item.projectName}</div>;
      })}
    </div>
  );
}

export default ChatsList