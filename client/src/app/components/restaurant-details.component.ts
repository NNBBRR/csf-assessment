import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {

	// TODO Task 4 and Task 5
	// For View 3

  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private restaurantSvc: RestaurantService
  ) {}

  ngOnInit(): void {
    this.form = this.createForm();
  }

  createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('', [Validators.required]),
      rating: this.fb.control('', )
      comment: this.fb.control('', [Validators.required]),
    });
  }

  submitForm(): void {
    const comment = this.form.get('comment')?.value;

    // post comment to server
    this.restaurantSvc
      .postComment(this.comment)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });

    this.clearForm();
  }

  clearForm(): void {
    this.form = this.createForm();
  }

}
