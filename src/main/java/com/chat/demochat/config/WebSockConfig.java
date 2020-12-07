package com.chat.demochat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

/**
 * handler를 이용하여 websocket을 활성화하기 위한 Config 생성
 * @EnableWebsocket 을 선언 websocket을 활성화합니다.
 * WebSocket에 접속하기 위한 Endpoint 는 /ws/chat 으로 설정
 * 도메인이 다른 서버에서도 접속이 되도록
 * CORS : setAllowdOrigins("*") 을 설정한다
 * 이제 클라이언트가 ws://localhost:8080/ws/chat 으로
 * 커넥션을 연결하고 메시지 통신을 할 수 있는 기본적인 준비가 끝났다.
 */
/*
@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSockConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "ws/chat").setAllowedOrigins("*");
    }
}
*/

/**
 * Stomp를 사용하기 위해 @EnableWebSocketMessageBroker 선언
 * WebsocketMessageBrokerConfigurer를 상속받아 ConfigureMessageBroker를 구현한다
 * pub.sub 메시징을 구현하기 위해 메시지를 방행하는 요청의 prefix/pub로 시작하도록 설정하고
 * 메시지를 구독하는 요청의 prefix는 /sub로 시작하도록 설정한다.
 * 그리고 stomp websocket의 연결 endpoint는 /ws-stomp로 설정한다.
 * 따라서 개발 서버 ws://localhost:8080/ws-stomp이다.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSockConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/ws-stomp").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }
}
