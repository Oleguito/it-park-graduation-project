// Import the required libraries
import { Settings } from "@/constants/settings";
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";



// Create a WebSocket connection
const socket = new SockJS(Settings.websocket.connectionURL);
export const stompClient = Stomp.over(socket);

// Connect to the WebSocket and subscribe to a specific chat
stompClient.connect({}, (frame: any) => {
  console.log(`Connected: ${frame}`);

  const chatId = "12345"; // Unique chat identifier

  // Subscribe to the topic corresponding to the given chat
  stompClient.subscribe(`${Settings.websocket.subscribeURL}/${chatId}`, (message: any) => {
    showMessage(JSON.parse(message.body));
  });
});

// Send a message to the chat with the specified identifier
function sendMessage() {
  const chatId = "12345"; // Unique chat identifier
  stompClient.send(
    `${Settings.websocket.sendMessageURL}/${chatId}`,
    {},
    JSON.stringify({ content: "Hello!" }),
  );
}

// Function to display the received message
function showMessage(message: any) {
  // Implement the logic to display the message
  console.log(message);
}
