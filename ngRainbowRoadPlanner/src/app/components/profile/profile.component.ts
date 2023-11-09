import { ProfileService } from './../../services/profile.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Trip } from 'src/app/models/trip';
import { User } from 'src/app/models/user';
import { Vehicle } from 'src/app/models/vehicle';
import { AuthService } from 'src/app/services/auth.service';
import { TripService } from 'src/app/services/trip.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user: User = new User();
  selected: User | null = null;
  username: string = '';
  editUser: User | null = null;
  userTrip: Trip | null = null;
  vehicle: Vehicle | null = null;
  addVehicle: boolean = false;
  checkedUser: boolean = false;


  constructor(
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private profileService: ProfileService,
    private tripService: TripService,
    private vehicleService: VehicleService
  ) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    let userIdfromTripComponent = Number(
      this.route.snapshot.paramMap.get('id')
    );
    if (userIdfromTripComponent) {
      this.profileService.userById(userIdfromTripComponent).subscribe({
        next: (user: User) => {
          console.log(user);
          this.selected = user;
          this.checkedUser = false;
          this.profileService.getSingleUserTrips(this.selected.id).subscribe({
            next: (trips) => {
              if (this.selected != null) {
                this.selected.trips = trips;
                this.vehicleService
                  .findUsersVehicles(this.selected.id)
                  .subscribe({
                    next: (vehicles) => {
                      if (this.selected != null)
                        this.selected.vehicles = vehicles;
                    },
                  });
              }
            },
          });

          error: (fail: string) => {
            console.log('ohh no' + fail);
          };
        },
      });
    } else {
      this.getLoggedInUserInfo();
      this.showUserTrips();
    }
  }

  setEditUserVehicle() {
    this.addVehicle = true;
    this.vehicle = new Vehicle();
  }

  reload() {
    this.getLoggedInUserInfo();
    this.editUser = null;
    this.addVehicle = false;
  }

  showUserTrips() {
    this.profileService.getUserTrips().subscribe({
      next: (trips) => {
        if (this.selected != null) {
          this.selected.trips = trips;
        }
      },
    });
  }

  getSingleTripById(id: number) {
    this.tripService.getSingleTrip(id).subscribe((trip) => {});
  }

  getLoggedInUserInfo(): Observable<User> | null {
    this.auth.getLoggedInUser().subscribe({
      next: (foundUser) => {
        this.username = foundUser.username;

        this.profileService.show(this.username).subscribe({
          next: (foundUser) => {
            this.selected = foundUser;
            this.checkedUser = true;
            this.profileService.getUserTrips().subscribe({
              next: (trips) => {
                if (this.selected != null) {
                  this.selected.trips = trips;
                  this.vehicleService.getVehicles().subscribe({
                    next: (vehicles) => {
                      if (this.selected != null)
                        this.selected.vehicles = vehicles;
                      return foundUser;
                    },
                  });
                }
              },
            });
          },
          error: (fail) => {
            console.log('ohh no');
          },
        });
      },
      error: (fail) => {
        console.log('ohh no');
      },
    });

    return null;
  }

  deleteVehicle(id: number) {
    this.vehicleService.delete(id).subscribe({
      next: () => {
        this.reload();
      },
    });
  }

  addNewVehicle(newVehicle: Vehicle) {
    newVehicle.user = this.selected;

    this.vehicleService.create(newVehicle).subscribe({
      next: (vehicle) => {
        // console.log(user)

        this.reload();
      },
      error: (failure) => {
        console.error('Error getting edit prifile');
        console.error(failure);
      },
    });
  }

  setEditUser() {
    this.editUser = Object.assign({}, this.selected);
  }
  cancelEdit() {
    this.editUser = null;
    this.addVehicle = false;
  }

  editProfile(user: User) {
    this.profileService.update(user).subscribe({
      next: (user) => {
        // console.log(user)

        this.reload();
      },
      error: (failure) => {
        console.error('Error getting edit prifile');
        console.error(failure);
      },
    });
  }
}
