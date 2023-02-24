package vttp2022.csf.assessment.server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.repositories.RestaurantRepository;
import vttp2022.csf.assessment.server.services.RestaurantService;


@RestController

@RequestMapping
public class CuisineController {

	@Autowired
	private RestaurantService restaurantSrv;

	@GetMapping (path="/api/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCuisines() {

		List<String> cuisines = restaurantSrv.getCuisines();

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (String c: cuisines)
			arrBuilder.add(c);

		return ResponseEntity.ok(arrBuilder.build().toString());
	}


    @GetMapping (path="/api/{cuisine}/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRestaurantsByCuisine(@PathVariable String cuisine) {

        List<Restaurant> result = restaurantSrv.getRestaurantsByCuisine(cuisine);

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (Restaurant r : result)
			arrBuilder.add(r.toJson());

		return ResponseEntity.ok(arrBuilder.build().toString());
	}

	@GetMapping (path="/api/restaurants/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRestaurant(@PathVariable String name) {

			Restaurant restaurant = restaurantSrv.getRestaurant(name);
			JsonObject payload = toJson(restaurant);
			
		return ResponseEntity.ok(payload.toString());
	}

 }


