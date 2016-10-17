package ejava.week02.view;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

@ViewScoped
@Named
public class ShoppingCart implements Serializable {

	@Resource(lookup = "jms/warehouseQueueFactory")
	private ConnectionFactory factory;

	@Resource(lookup = "jms/warehouseQueue")
	private Queue warehouseQueue;

	private static final long serialVersionUID = 1L;

	private List<String> cart = new LinkedList<>();
	private String newItem = "";

	public List<String> getCart() {
		return cart;
	}
	public void setCart(List<String> cart) {
		this.cart = cart;
	}

	public String getNewItem() {
		return newItem;
	}
	public void setNewItem(String newItem) {
		this.newItem = newItem;
	}

	public void addToCart() {
		cart.add(newItem);
		newItem = "";
	}

	public String deliver() {
		try (JMSContext ctx = factory.createContext()) {
			JMSProducer producer = ctx.createProducer();
			TextMessage msg = ctx.createTextMessage();
			try {
				msg.setText(">> " + new Date() + ": " + cart.toString());
				producer.send(warehouseQueue, msg);
			} catch (JMSException ex) {
				ex.printStackTrace();
			}
		}
		return ("index");

	}

}
