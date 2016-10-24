package ejava.wk3.rest;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class WeatherTask implements Runnable {

	public static final String[] CITIES = { "Singapore", "Kuala Lumpur", "Bangkok", "Jakarta", "Manila" };

	private Client client = ClientBuilder.newClient();
	private AsyncResponse asyncResponse;

	public WeatherTask(AsyncResponse resp) {
		asyncResponse = resp;
	}

	private JsonArray getWeather(String city) {

		WebTarget target = client.target("http://api.openweathermap.org/data/2.5/weather")
				.queryParam("q", city)
				.queryParam("appid", "be93736b8adfdad5094ce0b9f35d0ea3");

		Invocation.Builder inv = target.request(MediaType.APPLICATION_JSON);

		JsonObject result = inv.get(JsonObject.class);

		return (result.getJsonArray("weather"));
	}

	@Override
	public void run() {

		System.out.println("====> executing in thread pool");

		JsonObjectBuilder objBuilder = Json.createObjectBuilder();
		for (String c: CITIES) {
			System.out.println(">> processing: " + c);
			objBuilder.add(c, getWeather(c));
		}

		asyncResponse.resume(Response.ok(objBuilder.build()).build());
	}
	
}
