package week1;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@Inject private Item item;

	@Inject private Cart cart;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String i = item.getItem();

		System.out.println("item: "+ i);

		if ("exit".equals(i)) {
			HttpSession sess = req.getSession();
			sess.invalidate();
		} else {
			cart.add();
			for (Item x: cart.get())
				System.out.println(">>> " + x.getItem());
		}

		resp.setStatus(HttpServletResponse.SC_CREATED);
	}

	
    
}
