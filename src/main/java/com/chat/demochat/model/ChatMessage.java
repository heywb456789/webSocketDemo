package com.chat.demochat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    /**
     * DTO
     * 상황에 따라 채팅방입장(ENTER), 채팅방에 메시지 보내기(TALK)
     * 나머지 멤버 핑드는 채팅방 구별 id, 메시지를 보낸사람, 메시지로 구성한다.
     */
    public enum MessageType{
        ENTER,
        TALK,
        JOIN
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

}
