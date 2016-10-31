package ejava.week04.web;

import java.io.IOException;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@RequestScoped
@ServerEndpoint("/chat")
public class ChatSocket {

	@Resource(lookup = "concurrent/myThreadPool")
	private ManagedScheduledExecutorService executor;

	@OnOpen
	public void open(Session session) {
		System.out.println(">>> new session: " + session.getId());
	}

	@OnMessage
	public void message(final Session session, final String msg) {
		System.out.println(">>> message: " + msg);

		executor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println(">>> in thread");
				final JsonObject message = Json.createObjectBuilder()
						.add("message", msg)
						.add("timestamp", (new Date()).toString())
						.build();

				for (Session s: session.getOpenSessions())
					try {
						s.getBasicRemote().sendText(message.toString());
					} catch(IOException ex) {
						try { s.close(); } catch (IOException e) { }
					}
			}
		});
		System.out.println(">>> exiting message");
	}
	
}
