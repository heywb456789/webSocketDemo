package com.chat.demochat.model;

import com.chat.demochat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * DTO
 * 채팅방은 입장한 클라이언트들의 정보를 가지고 있어야 하므로 WebsocketSession 정보 리스트를 멤버 필드로 갖는다.
 * 나머지 멤버 필드로 채팅방 id, 채팅방 이름을 추가한다.
 * 채팅방에서는 입장, 대화하기 기능이 있어서
 * handleAction을 통해 분기 처리한다.
 * 입장시에는 채팅룸의 session정보에 클라이언트의 session 리스트에 추가해 놓았다가 채팅룸에 메시지가 도착할 경우
 * 채팅룸의 모든 session에 메시지를 발송하면 완성
 */

/**
 * Sessions -> stomp 적용되면서 필요없어진다.
 */

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
//    private Set<WebSocketSession> sessions = new HashSet<>();

//    @Builder
//    public ChatRoom(String roomId, String name){
//        this.roomId = roomId;
//        this.name = name;
//    }

//    public void handleAction(WebSocketSession session , ChatMessage chatMessage, ChatService chatService){
//        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
//            sessions.add(session);
//            chatMessage.setMessage(chatMessage.getSender() + "님 이 입장햇습니다.");
//        }
//        sendMessage(chatMessage, chatService);
//    }
//
//    private <T> void sendMessage(T message, ChatService chatService) {
//        sessions.parallelStream().forEach(session -> chatService.sendMessage(session,message));
//    }


    //stomp 추가후 변경
    public static ChatRoom create(String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }

}
