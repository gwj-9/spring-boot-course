package top.gwj.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 天气信息推送WebSocket处理器
 */
@Slf4j
@Component
public class SimpleTimeWebSocketHandler implements WebSocketHandler {
    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 天气数据配置
    private final String[] weatherConditions = {
            "晴朗", "多云", "阴天", "小雨", "中雨", "大雨",
            "雷阵雨", "小雪", "中雪", "大雪", "雾天", "霾"
    };
    private final String[] windDirections = {
            "东风", "南风", "西风", "北风", "东北风", "东南风", "西北风", "西南风"
    };
    private final Random random = new Random();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.put(session.getId(), session);
        log.info("新的WebSocket连接建立，会话ID: {}, 当前连接数: {}", session.getId(), SESSIONS.size());
        // 发送天气服务欢迎消息
        String welcomeMessage = "🎉 欢迎连接天气推送服务！\n每5秒将收到实时天气信息";
        sendMsg(session, welcomeMessage);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        log.info("收到客户端消息: {}, 会话ID: {}", payload, session.getId());
        if ("ping".equalsIgnoreCase(payload.trim())) {
            sendMsg(session, "pong");
        } else {
            String response = "收到消息: " + payload + "\n发送 'ping' 测试连接";
            sendMsg(session, response);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket传输错误，会话ID: {}", session.getId(), exception);
        SESSIONS.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        SESSIONS.remove(session.getId());
        log.info("WebSocket连接关闭，会话ID: {}, 关闭状态: {}, 当前连接数: {}", session.getId(), closeStatus, SESSIONS.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 定时任务：每5秒推送天气信息
     */
    @Scheduled(fixedRate = 5000)
    public void sendPeriodicWeather() {  // 方法名修改为天气相关
        if (SESSIONS.isEmpty()) {
            log.debug("当前没有活跃的WebSocket连接");
            return;
        }
        log.info("开始执行定时天气推送任务，当前连接数: {}", SESSIONS.size());

        // 生成模拟天气数据
        String time = LocalDateTime.now().format(timeFormatter);
        String condition = weatherConditions[random.nextInt(weatherConditions.length)];
        int temperature = 10 + random.nextInt(25); // 10-35℃
        String wind = windDirections[random.nextInt(windDirections.length)];
        int windForce = 1 + random.nextInt(8); // 1-8级
        int humidity = 30 + random.nextInt(60); // 30-90%

        // 构建天气信息字符串
        String weatherInfo = String.format(
                "📅 %s 天气信息\n" +
                        "🌤️ 天气状况: %s\n" +
                        "🌡️ 温度: %d℃\n" +
                        "💨 风向: %s %d级\n" +
                        "💧 湿度: %d%%",
                time, condition, temperature, wind, windForce, humidity
        );

        // 推送天气信息给所有连接
        SESSIONS.values().removeIf(session -> {
            try {
                if (session.isOpen()) {
                    sendMsg(session, weatherInfo);
                    return false;
                } else {
                    log.warn("发现已关闭的会话，将其移除: {}", session.getId());
                    return true;
                }
            } catch (Exception e) {
                log.error("发送消息失败，移除会话: {}", session.getId(), e);
                return true;
            }
        });
    }

    private void sendMsg(WebSocketSession session, String message) {
        try {
            if (session.isOpen()) {
                TextMessage textMessage = new TextMessage(message);
                session.sendMessage(textMessage);
                log.debug("消息发送成功，会话ID: {}", session.getId());
            }
        } catch (Exception e) {
            log.error("发送消息失败，会话ID: {}", session.getId(), e);
        }
    }
}



















//package top.gwj.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.*;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * 简单时间推送WebSocket处理器
// *
// * @author moqi
// */
//@Slf4j
//@Component
//public class SimpleTimeWebSocketHandler implements WebSocketHandler {
//    // 使用线程安全的 ConcurrentHashMap 存储 WebSocket 会话
//    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();
//
//    // 日期时间格式化器
//    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        SESSIONS.put(session.getId(), session);
//        log.info("新的WebSocket连接建立，会话ID: {}, 当前连接数: {}", session.getId(), SESSIONS.size());
//        // 连接建立后立即发送一条欢迎消息
//        String welcomeMessage = "🎉 欢迎连接时间推送服务！\n";
//        sendMsg(session, welcomeMessage);
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        // 处理客户端发送的消息
//        String payload = message.getPayload().toString();
//        log.info("收到客户端消息: {}, 会话ID: {}", payload, session.getId());
//        if ("ping".equalsIgnoreCase(payload.trim())) {
//            sendMsg(session, "pong");
//        } else {
//            String response = "收到消息: " + payload + "\n发送 'ping' 测试连接";
//            sendMsg(session, response);
//        }
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        log.error("WebSocket传输错误，会话ID: {}", session.getId(), exception);
//        SESSIONS.remove(session.getId());
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        SESSIONS.remove(session.getId());
//        log.info("WebSocket连接关闭，会话ID: {}, 关闭状态: {}, 当前连接数: {}", session.getId(), closeStatus, SESSIONS.size());
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//
//    /**
//     * 定时任务：每5秒钟推送当前时间
//     */
//    @Scheduled(fixedRate = 5000)
//    public void sendPeriodicMood() {
//        if (SESSIONS.isEmpty()) {
//            log.debug("当前没有活跃的WebSocket连接");
//            return;
//        }
//        log.info("开始执行定时时间推送任务，当前连接数: {}", SESSIONS.size());
//        String timeInfo = String.format("⏰ %s", LocalDateTime.now().format(timeFormatter));
//        // 向所有连接的客户端推送消息
//        SESSIONS.values().removeIf(session -> {
//            try {
//                if (session.isOpen()) {
//                    // 调用下面封装的私有方法，向指定的会话发送消息
//                    sendMsg(session, timeInfo);
//                    // 保留会话
//                    return false;
//                } else {
//                    log.warn("发现已关闭的会话，将其移除: {}", session.getId());
//                    // 移除会话
//                    return true;
//                }
//            } catch (Exception e) {
//                log.error("发送消息失败，移除会话: {}", session.getId(), e);
//                // 移除有问题的会话
//                return true;
//            }
//        });
//    }
//
//    /**
//     * 发送消息到指定的WebSocket会话
//     *
//     * @param session WebSocket会话
//     * @param message 要发送的消息
//     */
//    private void sendMsg(WebSocketSession session, String message) {
//        try {
//            if (session.isOpen()) {
//                TextMessage textMessage = new TextMessage(message);
//                session.sendMessage(textMessage);
//                log.debug("消息发送成功，会话ID: {}", session.getId());
//            }
//        } catch (Exception e) {
//            log.error("发送消息失败，会话ID: {}", session.getId(), e);
//        }
//    }
//}