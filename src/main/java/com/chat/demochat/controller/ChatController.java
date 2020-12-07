package com.chat.demochat.controller;

import com.chat.demochat.model.ChatMessage;
import com.chat.demochat.model.ChatRoom;
import com.chat.demochat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final SimpMessageSendingOperations messagingTemplate;

//    @PostMapping
//    public ChatRoom createRoom(@RequestParam String name){
//        return chatService.createRoom(name);
//    }
//
//    @GetMapping
//    public List<ChatRoom> findAllRoom(){
//        return chatService.findAllRoom();
//    }

    /**
     * @MessageMapping 을 통해 WebSocket으로 들어오는 메시지 발행을 처리한다.
     * 클라이언트에서 prefix를 붙여서 /pub/chat/message로 발행 요청을 하면 Controller가 해당 메시지를 받아 처리한다.
     * 메시지가 발행되면 /sub/chat/room/{roomId}로 메시지를 send 하는것을 볼 수 있다.
     * 클라이언트에서는 해당 주소를 (/sub/chat/room/{roomId}) 구독하고 있다가 메시지가 전달되면 화면에 출력한다.
     * 여기서 /sub/chat/room/{roomId}는 채팅룸을 구분하는 값이다.
     * pub/sub에서 Topic의 역할이라고 본다.
     *
     * 기존의 WebSockChatHandler가 했던 역할을 대체하므로 WebSockChatHandler는 삭제 ..
     *
     * @param message
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        if(ChatMessage.MessageType.JOIN.equals(message.getType())){
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }
}
