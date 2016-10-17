package ejava.week02.business;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
		mappedName = "jms/warehouseQueue",
		activationConfig = {
			@ActivationConfigProperty(
					propertyName = "destinationType",
					propertyValue = "javax.jms.Queue"
			)
		}
)
public class WarehouseProcessor implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage)message;
		try {
			String msg = text.getText();
			System.out.println(">>> processing message: " + msg);
		} catch (JMSException ex) {
			ex.printStackTrace();
		}

	}
	
}
