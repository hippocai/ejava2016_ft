package week1jsf;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@FlowScoped("register")
@Named
public class RegisterFlow implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;

	@PostConstruct
	private void init() {
		System.out.println(">>> creating flow");
	}

	@PreDestroy
	private void destroy() {
		System.out.println(">> exiting flow");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String processFlow() {
		System.out.println("> name: " + name);
		System.out.println("> email: " + email);

		return ("register-return");
	}
	
}
