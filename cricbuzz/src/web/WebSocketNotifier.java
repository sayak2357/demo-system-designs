package web;

/**
 * Stub for websocket notifier - in an actual system you'd have sockets per client.
 */
public class WebSocketNotifier {
    public void pushToClients(String matchId, String message) {
        // Send to connected clients. Implementation depends on framework (Spring WebSocket/STOMP, socket.io, etc.)
        System.out.println("[WS] [match=" + matchId + "] " + message);
    }
}