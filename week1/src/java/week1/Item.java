package week1;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Item {

	private String item;

	@PostConstruct
	private void init() {
		System.out.println("@PostConstruct item");
	}

	@PreDestroy
	private void destroy() {
		System.out.println("@PreDestroy item: " + item);
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public Item createCopy() {
		Item i = new Item();
		i.setItem(item);
		return(i);
	}
	
}
