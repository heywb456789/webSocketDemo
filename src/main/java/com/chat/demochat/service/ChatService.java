package com.chat.demochat.service;

import com.chat.demochat.model.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * 채팅방을 생성, 조회하고 하나의 세션에 메시지 발송을 하는 서비스를 아래와 같이 구현
 * 채팅방 Map은 서버에 생성된 모든 채팅방의 정보를 모아놓은 구조체
 * 채팅방 조회 - 채팅방 Map에 담긴 정보를 조회
 * 채팅방 생성 - random UUID로 구별 ID를 가진 채팅방 객체를 생성하고 채팅방 Map에 추가
 * 메시지 발송 - 지정한 WebSocket 세션에 메시지를 발송
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name){
        String randomId = UUID.randomUUID().toString();
//        ChatRoom chatRoom = ChatRoom.builder()
//                .roomId(randomId)
//                .name(name)
//                .build();
//        chatRooms.put(randomId, chatRoom);
        return null;
    }

    public <T> void sendMessage(WebSocketSession session , T messae){
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(messae)));
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }
}
