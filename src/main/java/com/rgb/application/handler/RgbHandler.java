package com.rgb.application.handler;

import com.rgb.application.service.RgbService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RgbHandler implements WebSocketHandler {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private RgbService service;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(
                session.receive()
                       .map(data -> data.getPayloadAsText())
                       .map(json -> service.parseJson(json))
                       .map(rgb -> service.save(rgb))
                       .map(rgb -> service.createJson(rgb))
                       .map(session::textMessage));
    }
}
