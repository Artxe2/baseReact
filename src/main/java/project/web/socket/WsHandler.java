package project.web.socket;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WsHandler extends TextWebSocketHandler {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(WsHandler.class);
	List<WebSocketSession> sessionList = new ArrayList<>();
	Map<String, String> names = new LinkedHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("afterConnectionEstablished({})", session.getId());
	}

	private void broadCast(List<WebSocketSession> list, TextMessage message) {
		logger.info("broadCast({}, {})", list.size(), message);
		try {
			for (WebSocketSession ws : list) {
				ws.sendMessage(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("handleTextMessage({}, {})", session.getId(), message.getPayload());
		StringTokenizer st = new StringTokenizer(message.getPayload(), Pvo.Sharp);
		if (st.hasMoreTokens()) {
			String protocol = st.nextToken();
			if (protocol.compareTo(Pvo.A) == 0) {
				methodA(session, st);
			} else if (protocol.compareTo(Pvo.B) == 0) {
				methodB(session, st);
			} else if (protocol.compareTo(Pvo.LOG_IN) == 0) {
				logIn(session, st);
			} else if (protocol.compareTo(Pvo.LOG_OUT) == 0) {
				session.close();
			} else {
				System.out.println("Undefined Protocol...\"" + protocol + "\"");
			}
		} else {
			System.out.println("Not Included  Protocol...\"" + message.getPayload() + "\"");
		}
	}

	private void logIn(WebSocketSession session, StringTokenizer st) {
		String userName = st.nextToken();
		names.put(session.getId(), userName);
		try {
			for (WebSocketSession ws : sessionList) {
				StringBuilder msg = new StringBuilder();
				msg.append(Pvo.LOG_IN);
				msg.append(Pvo.Sharp);
				msg.append(ws.getId());
				msg.append(Pvo.Sharp);
				msg.append(names.get(ws.getId()));
				msg.append(Pvo.Sharp);
				msg.append(" - Welcome");
				session.sendMessage(new TextMessage(msg.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuilder msg = new StringBuilder();
		msg.append(Pvo.LOG_IN);
		msg.append(Pvo.Sharp);
		msg.append(session.getId());
		msg.append(Pvo.Sharp);
		msg.append(userName);
		msg.append(Pvo.Sharp);
		msg.append(" - Hi");
		broadCast(sessionList, new TextMessage(msg.toString()));
		sessionList.add(session);
		logger.info("sessionList.size(): {}", sessionList.size());
	}

	private void methodA(WebSocketSession session, StringTokenizer st) {
		StringBuilder msg = new StringBuilder();
		msg.append(Pvo.MESSAGE);
		while (st.hasMoreTokens()) {
			msg.append(Pvo.Sharp);
			msg.append(st.nextElement());
		}
		try {
			session.sendMessage(new TextMessage(msg.toString()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void methodB(WebSocketSession session, StringTokenizer st) {
		StringBuilder msg = new StringBuilder();
		msg.append(Pvo.MESSAGE);
		while (st.hasMoreTokens()) {
			msg.append(Pvo.Sharp);
			msg.append(st.nextElement());
		}
		broadCast(sessionList, new TextMessage(msg.toString()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("afterConnectionClosed({}, {})", session.getId(), status);
		sessionList.remove(session);
		StringBuilder msg = new StringBuilder();
		msg.append(Pvo.LOG_OUT);
		msg.append(Pvo.Sharp);
		msg.append(session.getId());
		msg.append(Pvo.Sharp);
		msg.append(names.get(session.getId()));
		msg.append(Pvo.Sharp);
		msg.append(" - Bye");
		broadCast(sessionList, new TextMessage(msg.toString()));
	}
}
