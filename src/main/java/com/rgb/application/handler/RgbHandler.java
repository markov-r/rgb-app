package com.rgb.application.handler;

import com.rgb.application.service.RgbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class RgbHandler implements WebSocketHandler {

    @Autowired
    private RgbService service;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(
                session.receive()
                       .map(WebSocketMessage::getPayloadAsText)
                       .map(json -> service.parseJson(json))
                       .map(rgb -> service.save(rgb))
                       .map(rgb -> service.createJson(rgb))
                       .map(session::textMessage));
    }
}
