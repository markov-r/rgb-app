package com.rgb.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TaskApplicationTests {

    private static final String RGB_WS_URL = "ws://localhost:8080/rgb/app";

    @Test
    public void testNotificationsOnUpdates() throws InterruptedException {
        int count = 1;
        AtomicLong counter = new AtomicLong();
        URI uri = URI.create(RGB_WS_URL);
        WebSocketClient webClient = new ReactorNettyWebSocketClient();
        webClient.execute(uri, (WebSocketSession session) -> {
            Mono<WebSocketMessage> outgoing = Mono.just(session.textMessage(
                    "{\"red\":\"131\",\"green\":\"0\",\"blue\":\"0\"}"));
            Flux<String> incoming = session
                    .receive()
                    .map(WebSocketMessage::getPayloadAsText);

            return session
                    .send(outgoing)
                    .thenMany(incoming)
                    .doOnNext(x -> counter.incrementAndGet())
                    .then();

        }).subscribe();

        Thread.sleep(1000);

        Assertions.assertEquals(counter.get(), count);
    }
}
