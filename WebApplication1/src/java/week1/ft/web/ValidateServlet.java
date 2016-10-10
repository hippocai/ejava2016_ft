package week1.ft.web;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/validate")
public class ValidateServlet extends HttpServlet {

	//Injection point
	@Inject private Item item;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		System.out.println(String.format(">>> validate item: %s", item.toString()));

		String name = req.getParameter("name");
		Integer qty = Integer.parseInt(req.getParameter("qty"));

		if (qty <= 0) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		item.setName(name);
		item.setQuantity(qty);

		req.getRequestDispatcher("cart")
				.forward(req, resp);

	}
	
}
