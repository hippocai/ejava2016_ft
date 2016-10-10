package week1.ft.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@Inject private Item item;
	@Inject private Cart cart;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		System.out.println(String.format(">>> item: %s", item.toString()));

		System.out.println(">>> class: " + item.getClass().getName());

		cart.addItem();

		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		resp.setContentType("text/plain");
		try (PrintWriter pw = resp.getWriter()) {
			pw.println("Items in the cart: " + cart.getItems().size());
			for (Item i: cart.getItems())
				pw.println(i.toString());
		}
	}
}
