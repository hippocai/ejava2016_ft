package week1jsf;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Item> items = new LinkedList<>();

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item i) {
		items.add(i);
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String back() {
		return ("index");
	}
	
}
