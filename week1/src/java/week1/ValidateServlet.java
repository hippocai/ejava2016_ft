package week1;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/validate")
public class ValidateServlet extends HttpServlet {

	@Inject private Item item;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String i = req.getParameter("item");

		if (i.startsWith("rotten")) {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}

		System.out.println(">>> Item class: " + item.getClass().getName());

		//req.setAttribute("item", i);
		item.setItem(i);

		req.getRequestDispatcher("/cart").forward(req, resp);
	}

	
	
}
