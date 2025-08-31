package com.backend.webserver;

import jakarta.websocket.server.ServerEndpointConfig.Configurator;

public class MyConfig extends Configurator {
    @Override
    public boolean checkOrigin(String originHeaderValue) {
        return true;
    }

}
