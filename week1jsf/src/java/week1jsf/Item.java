package week1jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named()
public class Item {

	@Inject private Cart cart;

	private String name;
	private Integer quantity;

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

	public String addToCart() {
		System.out.println(">> name: " + name);
		System.out.println(">> quantity: " + quantity);

		if (quantity <= 0) {
			FacesMessage msg = new FacesMessage("Quantity must be greater than 0");
			FacesContext.getCurrentInstance()
					.addMessage("orderForm:quantity", msg);
			return (null);
		}

		cart.addItem(createCopy());

		return ("thankyou?faces-redirect=true");
	}

	public Item createCopy() {
		Item i = new Item();
		i.setName(name);
		i.setQuantity(quantity);
		return (i);
	}
}
