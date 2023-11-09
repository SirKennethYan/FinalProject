import { ProfileService } from './../../services/profile.service';
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/app/models/user';
import { ActivatedRoute, Router } from '@angular/router';
import { Trip } from 'src/app/models/trip';
import { AuthService } from 'src/app/services/auth.service';
import { TripService } from 'src/app/services/trip.service';
import { FormControl } from '@angular/forms';
import {
  MapDirectionsService,
  MapGeocoder,
  MapInfoWindow,
  MapMarker,
} from '@angular/google-maps';
import { Address } from 'src/app/models/address';
import { Destination } from 'src/app/models/destination';
import { GeoResultToAddressPipe } from 'src/app/pipes/geo-result-to-address.pipe';
import { DestinationService } from 'src/app/services/destination.service';
import { AddressService } from 'src/app/services/address.service';
import { Vehicle } from 'src/app/models/vehicle';
import { Comment } from 'src/app/models/comment';
import { VehicleService } from 'src/app/services/vehicle.service';
import { Observable, map } from 'rxjs';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-trip',
  templateUrl: './trip.component.html',
  styleUrls: ['./trip.component.css'],
})
export class TripComponent implements OnInit {
  selected: Trip | null = null;
  newTrip: Trip = new Trip();
  vehicles: Vehicle[] = [];
  trips: Trip[] = [];
  currentTrips: Trip[] = [];
  pastTrips: any[] = [];
  map: any;
  lat = 39.6087;
  lng = -104.90271;
  directions: Observable<google.maps.DirectionsResult | undefined> | null =
    null;

  @ViewChild('mapContainer', { static: false }) mapContainer!: ElementRef;
  @ViewChild(MapInfoWindow, { static: false }) infoWindow!: MapInfoWindow;
  @ViewChild(MapMarker, { static: false }) marker!: MapMarker;

  public origin!: google.maps.LatLngLiteral;
  public destination!: google.maps.LatLngLiteral;
  public directionsService = new google.maps.DirectionsService();
  public directionsRenderer!: google.maps.DirectionsRenderer;
  public startDestination: google.maps.LatLngLiteral | null = null;
  public endDestination: google.maps.LatLngLiteral | null = null;
  vehicle: Vehicle | null = new Vehicle();
  @Input() profileTrip: Trip | null = null;

  newComment: Comment = new Comment();
  editComment: Comment | null = null;
  comments: Comment[] = [];
  selectedComment: Comment | null = null;

  constructor(
    private tripService: TripService,

    private route: ActivatedRoute,
    private router: Router,
    private geocoder: MapGeocoder,
    private addrPipe: GeoResultToAddressPipe,
    private destService: DestinationService,
    private addressService: AddressService,
    private vehicleService: VehicleService,
    private directionService: MapDirectionsService,
    private commentService: CommentService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    let tripIdfromTripComponent = Number(
      this.route.snapshot.paramMap.get('id')
    );
    if (tripIdfromTripComponent) {
      this.tripService.getSingleTrip(tripIdfromTripComponent).subscribe({
        next: (trip) => {
          this.selected = trip;
          this.reloadComment(trip.id);
          this.showDirections(trip);
          this.directionsRenderer = new google.maps.DirectionsRenderer();
        },
      });
    } else {
      this.getPastTrips();
      this.getCurrentTrips();
      this.directionsRenderer = new google.maps.DirectionsRenderer();
    }
  }

  loggedIn() {
    return this.authService.checkLogin();
  }

  getSingleTripById(id: number) {
    this.tripService.getSingleTrip(id).subscribe({
      next: (trip) => {
        this.selected = trip;
        this.reloadComment(trip.id);
        this.showDirections(trip);
      },
      error: () => {
        this.router.navigate(['/login']);
      },
    });
  }

  getVehicles(): void {
    this.vehicleService.getVehicles().subscribe({
      next: (vehicleList) => {
        this.vehicles = vehicleList;
      },
      error: () => {
        this.router.navigate(['/login']);
      },
    });
  }

  getCurrentTrips() {
    const now = new Date().getTime();
    this.tripService.getTrips().subscribe({
      next: (trips) => {
        this.currentTrips = trips.filter((trip) => {
          const endDate = new Date(trip.endDate).getTime();
          return endDate >= now;
        });
      },
      error: (err) => console.error(err),
    });
  }

  getPastTrips() {
    const now = new Date().getTime();
    this.tripService.getTrips().subscribe({
      next: (trips) => {
        this.pastTrips = trips.filter((trip) => {
          const endDate = new Date(trip.endDate).getTime();
          return endDate < now;
        });
      },
      error: (err) => console.error(err),
    });
  }

  createTrip(trip: Trip) {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        trip.user = user;
        this.tripService.create(trip).subscribe({
          next: (madeTrip) => {
            this.selected = madeTrip;
            console.log(madeTrip);
            this.showDirections(madeTrip);
          },
        });
      },
    });
  }

  showDirections(madeTrip: Trip) {
    let directionRequest = {
      origin: {
        lat: madeTrip.startDestination!.address.latitude,
        lng: madeTrip.startDestination!.address.longitude,
      },
      destination: {
        lat: madeTrip.endDestination!.address.latitude,
        lng: madeTrip.endDestination!.address.longitude,
      },
      travelMode: google.maps.TravelMode.DRIVING,
    };
    this.directions = this.directionService
      .route(directionRequest)
      .pipe(map((response) => response.result));
  }

  clearTripDestinations() {
    this.newTrip.startDestination = null;
    this.newTrip.endDestination = null;
  }

  handleMapClick(mapEvent: google.maps.MapMouseEvent) {
    let lat = mapEvent.latLng!;
    // console.log(lat);
    this.geocoder.geocode({ location: lat }).subscribe({
      next: (result) => {
        // console.log(result);
        let address = new Address();
        let geoAddress: any = result.results[0].address_components;
        address = this.addrPipe.transform(geoAddress);
        address.latitude = lat.lat();
        address.longitude = lat.lng();
        console.log(address);

        this.addressService.create(address).subscribe({
          next: (newAddress) => {
            console.log(newAddress);

            let dest = new Destination();
            dest.address = newAddress;
            dest.name = newAddress.city;
            console.log(dest);

            if (this.newTrip.startDestination == null) {
              this.destService.create(dest).subscribe({
                next: (madeDest) => {
                  this.newTrip.startDestination = madeDest;
                  // console.log(madeDest)
                  this.startDestination = { lat: lat.lat(), lng: lat.lng() };
                },
              });
            } else if (this.newTrip.endDestination == null) {
              this.destService.create(dest).subscribe({
                next: (madeDest) => {
                  this.newTrip.endDestination = madeDest;
                  // Get directions here
                  this.endDestination = { lat: lat.lat(), lng: lat.lng() };
                },
              });
            } else {
              this.clearTripDestinations();
            }

            console.log(this.newTrip);
          },
        });
      },
    });
  }

  renderRoute() {
    if (this.startDestination && this.endDestination) {
      this.directionsService.route(
        {
          origin: this.startDestination,
          destination: this.endDestination,
          travelMode: google.maps.TravelMode.DRIVING,
        },
        (response, status) => {
          if (status === 'OK') {
            this.directionsRenderer.setDirections(response);
            this.directionsRenderer.setMap(this.map);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        }
      );
    }
  }

  displayComment(comment: Comment) {
    this.newComment = comment;
  }

  displayTable() {
    this.selected = null;
  }

  createComment(comment: Comment, tripId: number): void {
    this.authService.getLoggedInUser().subscribe((user) => {
      comment.user = user;
      comment.trip = this.selected;
      this.commentService.create(comment, tripId).subscribe({
        next: (madeComment) => {
          this.newComment = new Comment();
          if (this.selected) {
            this.reloadComment(this.selected.id);
          }
        },
        error: (fail) => {
          console.error('Error creating comment');
        },
      });
    });
  }

  setEditComment() {
    this.editComment = Object.assign({}, this.selectedComment);
  }

  updateComment(comment: Comment, tripId: number, goToDetail = true) {
    this.commentService.update(comment, tripId).subscribe({
      next: (updatedComment) => {
        this.editComment = null;

        if (goToDetail) {
          this.selectedComment = updatedComment;
        }
        this.reload();
      },
      error: (fail) => {
        console.error('Error updating comment');
        console.log(fail);
      },
    });
  }

  deleteComment(id: number, tripId: number) {
    console.log(tripId, id);
    this.commentService.destroy(id, tripId).subscribe({
      next: (result) => {
        this.reloadComment(tripId);
      },
      error: (fail) => {
        console.error('Error deleting comment');
        console.error(fail);
      },
    });
  }
  reloadComment(id: number) {
    this.commentService.index(id).subscribe({
      next: (retrievedComments) => {
        this.comments = retrievedComments;
      },
      error: (fail) => {
        console.error('Error getting comment list from service');
        console.error(fail);
      },
    });
  }

  reload() {
    this.getVehicles();
    this.getPastTrips();
    this.getCurrentTrips();
  }
}
