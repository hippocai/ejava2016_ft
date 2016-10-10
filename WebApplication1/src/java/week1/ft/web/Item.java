package week1.ft.web;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Item {

	private String name;
	private Integer quantity;

	@PostConstruct
	private void init() {
		System.out.println(">>> @PostConstruct: Item");
	}

	@PreDestroy
	private void destroy() {
		System.out.println(">>> @PreDestry: Item");
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item createCopy() {
		Item i = new Item();
		i.name = name;
		i.quantity = quantity;
		return (i);
	}

	@Override
	public String toString() {
		return "Item{" + "name=" + name + ", quantity=" + quantity + '}';
	}

}
