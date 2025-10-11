package top.gwj.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class DeviceMonitorWebSocketHandler implements WebSocketHandler {
    // 存储连接的会话
    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.put(session.getId(), session);
        log.info("新的设备监控连接建立，会话ID: {}, 当前连接数: {}", session.getId(), SESSIONS.size());
        sendMessage(session, "🎉 欢迎连接设备监控服务！将定期推送CPU和内存占用率数据");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        log.info("收到监控客户端消息: {}, 会话ID: {}", payload, session.getId());
        sendMessage(session, "收到消息: " + payload);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("设备监控WebSocket传输错误，会话ID: {}", session.getId(), exception);
        SESSIONS.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        SESSIONS.remove(session.getId());
        log.info("设备监控WebSocket连接关闭，会话ID: {}, 关闭状态: {}, 当前连接数: {}",
                session.getId(), closeStatus, SESSIONS.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 定时任务：每3秒推送一次设备状态数据
     */
    @Scheduled(fixedRate = 3000)
    public void sendDeviceStatus() {
        if (SESSIONS.isEmpty()) {
            log.debug("当前没有活跃的设备监控连接");
            return;
        }

        // 生成模拟的CPU和内存占用率
        int cpuUsage = 30 + random.nextInt(50); // 30%-80%之间
        int memoryUsage = 40 + random.nextInt(40); // 40%-80%之间

        try {
            // 构建JSON数据
            String jsonData = objectMapper.writeValueAsString(Map.of(
                    "cpu", cpuUsage,
                    "memory", memoryUsage
            ));

            // 向所有连接推送数据
            SESSIONS.values().removeIf(session -> {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage(jsonData));
                        return false;
                    } else {
                        log.warn("发现已关闭的会话，将其移除: {}", session.getId());
                        return true;
                    }
                } catch (Exception e) {
                    log.error("发送设备状态失败，移除会话: {}", session.getId(), e);
                    return true;
                }
            });
        } catch (Exception e) {
            log.error("生成或发送设备状态数据失败", e);
        }
    }

    private void sendMessage(WebSocketSession session, String message) {
        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
                log.debug("消息发送成功，会话ID: {}", session.getId());
            }
        } catch (Exception e) {
            log.error("发送消息失败，会话ID: {}", session.getId(), e);
        }
    }
}