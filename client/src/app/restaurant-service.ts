import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { firstValueFrom } from 'rxjs'
import { Restaurant, Comment } from './models'

@Injectable()
export class RestaurantService {

  constructor(private http: HttpClient) { }

	// TODO Task 2
	// Use the following method to get a list of cuisines
	// You can add any parameters (if any) and the return type
	// DO NOT CHNAGE THE METHOD'S NAME
	public getCuisineList(): Promise<string[]> {
      return firstValueFrom(
        this.http.get<string[]>('/api/cuisines')
      )
    }


	// TODO Task 3
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type
	// DO NOT CHNAGE THE METHOD'S NAME
	public getRestaurantsByCuisine(cuisine: string): Promise<Restaurant[]> {
      return firstValueFrom(
        this.http.get<Restaurant[]>(
          `/api/${cuisine}/restaurants`
          )
      );
	}


	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any)
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public getRestaurant(name: string): Promise<Restaurant> {
		return firstValueFrom(
      this.http.get<Restaurant> (
        `/api/restaurants/${name}`
      )
    );

	}

	// TODO Task 5
	// Use this method to submit a comment
	// DO NOT CHANGE THE METHOD'S NAME OR SIGNATURE
	public postComment(comment: Comment): Promise<any> {
		// Implememntation in here
    const body = {
      comment: comment,
    };
    return firstValueFrom(
      this.http.post(
        `/api/comments/`,
        body
      )
    );

	}

}
