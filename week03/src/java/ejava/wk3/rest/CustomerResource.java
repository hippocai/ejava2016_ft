package ejava.wk3.rest;

import ejava.wk3.business.CustomerBean;
import ejava.wk3.model.Customer;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("/customer")
public class CustomerResource {

	@EJB private CustomerBean customerBean;

	@POST
	@Consumes("application/x-www-form-urlencoded") //?
	public Response post(MultivaluedMap<String, String> formData) {

		//req.getParameter("name")
		System.out.println(">>> name: " + formData.getFirst("name"));
		return (Response.ok().build());
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOne(@QueryParam("cid")Integer cid) {
		return (get(cid));
	}

	@GET
	@Path("{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("cid") Integer cid) {
		Optional<Customer> opt = customerBean.get(cid);

		if (!opt.isPresent())
			return (Response
					.status(Response.Status.NOT_FOUND)
					.entity("Not found: id=" + cid)
					.build());

		Customer c = opt.get();

		return (Response.ok(c.toJSON()).build());
	}


	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAsXML() {
		System.out.println(">>> getting data as XML");
		return (Response.ok().build());
	}

	@GET
	@Produces("application/json")
	@Path("all")
	public Response getAllCustomers(@Context UriInfo ui,
			@DefaultValue("0") @QueryParam("start") Integer start,
			@DefaultValue("100") @QueryParam("size") Integer size) {

		List<Customer> customers = customerBean.getAllCustomers(start, size);

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

		// http://localhost:8080/wk03/api/
		UriBuilder uiBuilder = ui.getBaseUriBuilder();

		// http://localhost:8080/wk03/api/customer
		uiBuilder = uiBuilder.path(CustomerResource.class);

		// http://localhost:8080/wk03/api/customer/{cid}
		try {
			uiBuilder = uiBuilder.path(
					CustomerResource.class.getMethod("get", Integer.class));
		} catch (Throwable t) { //Pokemon exception handler - gotta catchem all
			t.printStackTrace();
		}

		for (Customer c: customers) {
			// http://localhost:8080/wk03/api/customer/1
			URI uri = uiBuilder.clone().build(c.getCustomerId());
			arrBuilder.add(uri.toString());
		}

		return (Response.ok(arrBuilder.build())
				.header("Powered-By", "ejava2016")
				.build());
	}
	
}
