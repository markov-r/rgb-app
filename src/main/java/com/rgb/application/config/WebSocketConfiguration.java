package com.rgb.application.config;

import com.rgb.application.handler.RgbHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSocketConfiguration {
    public static final String RGB_URL = "/rgb/app";

    @Autowired
    RgbHandler rgbHandler;

    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/rgb/app", rgbHandler);
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(map);
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter(WebSocketService service) {
        return new WebSocketHandlerAdapter(service);
    }

    @Bean
    public WebSocketService webSocketService(){
        ReactorNettyRequestUpgradeStrategy strategy = new ReactorNettyRequestUpgradeStrategy();
        return new HandshakeWebSocketService(strategy);
    }
}
