package vttp2022.csf.assessment.server.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;

public class RestaurantRepository {

	public static final String RESTAURANT = "restaurants";
	private static final String COMMENT_COLLECTION = "comments";

	@Autowired
    private MongoTemplate mongoTemplate;

	// TODO Task 2
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method

	// db.restaurants.distinct('cuisine')
	public List<String> getCuisines() {
		return mongoTemplate.findDistinct(new Query(), "cuisine", RESTAURANT, String.class);
    }


	// TODO Task 3
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	// db.restaurants.find( { "cuisine": "some_cuisine" } )
	
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		
			Criteria c = Criteria.where("cuisine").is(cuisine);
			Query q = Query.query(c);
   
			return mongoTemplate.find(q, Document.class, RESTAURANT)
			   .stream()
			   .map(d -> Restaurant.create(d))
			   .toList();
		}
	

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  db.restaurants.find({ name: "restaurant_name" })
	
	public Optional<Restaurant> getRestaurant(String name) {
		Criteria c = Criteria.where("name").is(name);

		Query q = Query.query(c);
	  
		Document result = mongoTemplate.findOne(q, Document.class, "restaurants");
		if (null == result)
			return Optional.empty();

		return Optional.of(Restaurant.create(result));
	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	public void addComment(Comment comment) {
		// Implmementation in here
		Document doc = new Document();
		doc.put("name", name);
		doc.put("rating", rating);
		doc.put("text", text());

		Document docInserted = mongoTemplate.insert(doc, COMMENT_COLLECTION);
		System.out.println("Comment Posted" + docInserted);


	}
	



	// You may add other methods to this class

}

/*

{
  "address": {
		"building": "1007",
		"coord": [	
		-73.856077,
		40.848447
		],
		"street": "Morris Park Ave",
		"zipcode": "10462"
		},
  "borough": "Bronx",
  "cuisine": "Bakery",
  "grades": [
		{
		"date": "2014-03-03T00:00:00.000Z",
		"grade": "A",
		"score": 2
		},
		{
		"date": "2013-09-11T00:00:00.000Z",
		"grade": "A",
		"score": 6
		},
		{
		"date": "2013-01-24T00:00:00.000Z",
		"grade": "A",
		"score": 10
		},
		{
		"date": "2011-11-23T00:00:00.000Z",
		"grade": "A",
		"score": 9
		},
		{
		"date": "2011-03-10T00:00:00.000Z",
		"grade": "B",
		"score": 14
		}
  ],
  "name": "Morris Park Bake Shop",
  "restaurant_id": "30075445"
}
 */