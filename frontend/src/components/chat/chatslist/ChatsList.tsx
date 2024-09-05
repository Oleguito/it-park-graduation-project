import { ChatsListItem } from "@/types/chat/chat";

export type Props = {
  items: ChatsListItem[]
}

const ChatsList: React.FC<Props> = (props: Props) => {


  return (
    <div className="w-1/4">
      <div>ChatsList</div>
      {props.items.map(item => {
        return <div key={item.id} className="mt-2 mb-2">{item.title}</div>;
      })}
    </div>
  );
}

export default ChatsList