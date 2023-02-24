import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { RestaurantService } from '../restaurant-service'

@Component({
  selector: 'app-cuisine-list',
  templateUrl: './cuisine-list.component.html',
  styleUrls: ['./cuisine-list.component.css']
})
export class CuisineListComponent implements OnInit {

  cuisines: string[] = []

  constructor(
    private activateRoute: ActivatedRoute,
    private restaurantSvc: RestaurantService
  ) {}

  ngOnInit(): void {

    this.restaurantSvc.getCuisineList()
			.then(result => {
				this.cuisines = result
			})
			.catch(err => {
				console.error('>>> error: ', err)
			})
    }

}
