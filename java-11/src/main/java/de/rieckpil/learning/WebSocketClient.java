package de.rieckpil.learning;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class WebSocketClient {

    public static void main(String[] args) throws InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        var webSocketBuilder = httpClient.newWebSocketBuilder();

        WebSocket webSocket = webSocketBuilder.buildAsync(URI.create("ws://localhost:8080/java-ee-websockets/echo"), new WebSocket.Listener() {

            @Override
            public void onOpen(WebSocket webSocket) {
                webSocket.sendBinary(ByteBuffer.wrap("This is a message".getBytes()), true);
                CompletableFuture<WebSocket> sendPong = webSocket.sendPong(ByteBuffer.wrap("PONG".getBytes()));
                WebSocket join = sendPong.join();
                System.out.println(join.getSubprotocol());
                Listener.super.onOpen(webSocket);
            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                if (!webSocket.isOutputClosed()) {
                    webSocket.sendText("This is a message", true);
                }
                System.out.println(data.toString());
                return Listener.super.onText(webSocket, data, last);
            }

            @Override
            public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
                return Listener.super.onClose(webSocket, statusCode, reason);
            }
        }).join();

        Thread.sleep(1000L);

        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok").thenRun(() -> System.out.println("sent close"));
    }
}
