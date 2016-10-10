package week1.jsf;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private Item item;

	private List<Item> items = new LinkedList<>();

	@PostConstruct
	private void init() {
		System.out.println(">>> @PostConstruct: Cart");
	}

	@PreDestroy
	private void destroy() {
		System.out.println(">>> @PreDestry: Cart");
	}

	public void addItem() {
		addItem(item.createCopy());
	}

	public void addItem(Item i) {
		items.add(i);
	}

	public void setItems(List<Item> l) {
		items = l;
	}
	public List<Item> getItems() {
		return (items);
	}
	
}
