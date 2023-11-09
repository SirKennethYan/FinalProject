import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'ngRainbowRoadPlanner';

  // @ViewChild('mapContainer', { static: false }) mapContainer!: ElementRef;

  // public origin!: google.maps.LatLngLiteral;
  // public destination!: google.maps.LatLngLiteral;
  // public directionsService = new google.maps.DirectionsService();
  // public directionsRenderer!: google.maps.DirectionsRenderer;

  constructor(private auth: AuthService) {}

  ngOnInit() {
    // this.directionsRenderer = new google.maps.DirectionsRenderer();
  }
}
