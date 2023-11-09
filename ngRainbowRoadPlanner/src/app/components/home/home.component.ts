import { TripService } from './../../services/trip.service';
import { Trip } from 'src/app/models/trip';
import { Component } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  viewRoadtrips: boolean | null = false;
  selectedTrip: Trip | null = null;
  selected: Trip | null = null;
  activeSlide = 0;
  user: User = new User();
  selectedUser: User | null = null;
  trips: Trip[] = [];
  allTrips: Trip[] = [];

  constructor(
    private tripServ: TripService,
    private router: Router,
    private authService: AuthService
  ) {}
  ngOnInit() {

      if (this.authService.checkLogin()) {
        this.findAllTrips();
      }

  }

  prevSlide() {
    this.activeSlide = (this.activeSlide - 1 + 2) % 2;
  }

  nextSlide() {
    this.activeSlide = (this.activeSlide + 1 + 2) % 2;
  }

  displayTripImages(tripImage: Trip) {
    this.selected = tripImage;
  }

  displayMyRoadtrips() {
    this.tripServ.index().subscribe({
      next: (mytrips) => {
        console.log('Hello');
        this.trips = mytrips;
      },
    });
  }

  navigateToTrip() {
    this.router.navigate(['trip']);
  }

  findAllTrips() {
    this.tripServ.index().subscribe({
      next: (totalTrips) => {
        this.allTrips = totalTrips;
        console.log(this.allTrips);
      },
    });
  }
}
