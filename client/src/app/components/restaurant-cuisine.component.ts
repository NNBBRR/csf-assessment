import { RestaurantService } from './../restaurant-service';
import { Restaurant } from './../models';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent implements OnInit {


	// TODO Task 3
	// For View 2
  restaurants: Restaurant [] = []
  cuisine = "";

  constructor(
    private restaurantSvc: RestaurantService,
    private activatedRoute: ActivatedRoute) { }


  ngOnInit(): void {
    this.cuisine = this.activatedRoute.snapshot.params['cuisine']
		console.info('cuisine: ', this.cuisine)

		this.restaurantSvc.getRestaurantsByCuisine(this.cuisine)
			.then(result => {
				this.restaurants = result
			})
			.catch(err => {
				console.error(">>> err: ", err)
			})
  }

}
