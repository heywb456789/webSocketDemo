package com.chat.demochat.handler;

import com.chat.demochat.model.ChatMessage;
import com.chat.demochat.model.ChatRoom;
import com.chat.demochat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/**
 * socket 통신은 서버와 클라이언트가 1:N 관계를 맺는다.
 * 따라서 한 서버에 여러 클라이언트가 접속 가능,
 * 서버에는 여러 클라이언트가 발송한 메시지를 받아 처리해줄 Handler가 필요
 * TextWebSocketHandler를 상속받아
 * -> Client로 부터 받은 메시지를 Console Log에 출력하고 client에 환영메시지를 보내는 역활을 한다.
 */
@Slf4j
//@Component
@RequiredArgsConstructor
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        super.handleTextMessage(session, message);
        String payload = message.getPayload();
        log.info("payload : {}", payload);
        //1차 TEST 코드
//        TextMessage textMessage  = new TextMessage("Welcome chatting Server");
//        session.sendMessage(textMessage);
        //2 차 Controller 기능 반영
        // 웹 소켓 클라이언트로부터 채팅 메시지를 전달받아 채팅메시지 객체로 변환
        // 전달받은 메시지에 담긴 채팅방 id로 발송 대상 채팅정보를 조회함
        // 해당 채팅방에 입장해있는 모든 클라이언트들 (Websocket session)에게 타입에 따른 메시지 발송
        ChatMessage chatMessage =objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
//        room.handleAction(session, chatMessage, chatService);
    }

}
