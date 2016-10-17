package week1;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private Item item;

	private List<Item> cart = new LinkedList<>();

	@PostConstruct
	private void init() {
		System.out.println(">>> in post construct");
	}

	@PreDestroy
	private void destroy() {
		System.out.println(">>> in pre destroy");
	}

	//Save the content instead of the container
	public void add() {
		cart.add(item.createCopy());
	}

	public List<Item> get() {
		return (cart);
	}
	
}
