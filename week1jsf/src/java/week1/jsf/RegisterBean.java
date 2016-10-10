package week1.jsf;

import java.io.Serializable;
import javax.annotation.PreDestroy;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@FlowScoped("register")
@Named
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;

	@PreDestroy
	private void destroy() {
		System.out.println(">>> destryoing flow scope bean");
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

	public String registerMe() {
		System.out.println(">> name: " + name);
		System.out.println(">> email: " + email);

		return ("register-return");
	}

	
}
