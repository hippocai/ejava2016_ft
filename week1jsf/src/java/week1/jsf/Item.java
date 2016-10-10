package week1.jsf;



import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class Item {

	@Inject private Cart cart;

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

	public String addToCart() {
		System.out.println(">>> name: " + name);
		System.out.println(">>> quantity: " + quantity);

		cart.addItem(this.createCopy());

		name = "";
		quantity = null;

		return (null);

		//return ("thankyou?faces-redirect=true");
		//return ("thankyou");
	}

	public String goBack() {
		return ("index");
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
