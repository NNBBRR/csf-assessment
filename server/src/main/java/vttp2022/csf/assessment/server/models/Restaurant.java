package vttp2022.csf.assessment.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// Do not modify this class
public class Restaurant {
	
	private String restaurantId;
	private String name;
	private String cuisine;
	private String address;
	private LatLng coordinates;
	private String mapUrl;

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantId() {
		return this.restaurantId;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getCuisine() {
		return this.cuisine;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}

	public void setCoordinates(LatLng coordinates) {
		this.coordinates = coordinates;
	}
	public LatLng getCoordinates() {
		return this.coordinates;
	}

	public void setMapURL(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	public String getMapURL() {
		return this.mapUrl;
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("restaurantId", restaurantId)
			.add("name", name)
			.add("cuisine", cuisine)
			.add("address", address)
			.build();
	}

	public Document toDocument() {
		Document document = new Document();
		document.put("restaurantId", restaurantId);
		document.put("name", name);
		document.put("cuisine", cuisine);
		document.put("address", address);
		return document;
	}

	public static Restaurant create(Document doc) {
		final Restaurant restu = new Restaurant();
		restu.setRestaurantId(doc.getString("restaurantId"));
		restu.setName(doc.getString("name"));
		restu.setCuisine(doc.getString("cuisine"));
		restu.setAddress(doc.getString("address"));

		Document d = doc.get("address", Document.class);
			Object r = d.get("coordinates");
			restu.setCoordinates(((LatLng)r));

		return restu;
	}
}
