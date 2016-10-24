package ejava.wk3.rest;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {

	@Resource(lookup = "concurrent/myThreadPool")
	private ManagedScheduledExecutorService service;

	public static final String[] CITIES = { "Singapore", "Kuala Lumpur", "Bangkok", "Jakarta", "Manila" };

	private Client client = ClientBuilder.newClient();

	private JsonArray getWeather(String city) {

		WebTarget target = client.target("http://api.openweathermap.org/data/2.5/weather")
				.queryParam("q", city)
				.queryParam("appid", "be93736b8adfdad5094ce0b9f35d0ea3");

		Invocation.Builder inv = target.request(MediaType.APPLICATION_JSON);

		JsonObject result = inv.get(JsonObject.class);

		return (result.getJsonArray("weather"));
	}

	@GET
	public void get(@Suspended AsyncResponse asyncResponse) {

		WeatherTask task = new WeatherTask(asyncResponse);

		System.out.println("----> submitting weather task");
		service.schedule(task, 3, TimeUnit.SECONDS);

		System.out.println("----> suspending and returning request thread to pool");
	}

	@GET
	@Path("{city}")
	public Response get(@PathParam("city")String city) {


		return (Response.ok(getWeather(city)).build());
	}
	
}
