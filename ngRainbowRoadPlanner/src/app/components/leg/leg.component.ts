import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MapGeocoder } from '@angular/google-maps';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Destination } from 'src/app/models/destination';
import { Leg } from 'src/app/models/leg';
import { GeoResultToAddressPipe } from 'src/app/pipes/geo-result-to-address.pipe';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { DestinationService } from 'src/app/services/destination.service';
import { LegService } from 'src/app/services/leg.service';
import { TripService } from 'src/app/services/trip.service';

@Component({
  selector: 'app-leg',
  templateUrl: './leg.component.html',
  styleUrls: ['./leg.component.css']
})
export class LegComponent implements OnInit {

  startDestination = new FormControl('');
  endDestination = new FormControl('');

  startAddress: Address | null = null;
  endAddress: Address | null = null;
  selected: Leg | null= null;
  newLeg: Leg = new Leg();
  editLeg: Leg | null = null;


  constructor(private legService: LegService,
    private tripService: TripService,
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private geocoder: MapGeocoder,
    private addrPipe: GeoResultToAddressPipe,
    private destService: DestinationService,
    private addressService: AddressService) { }

  ngOnInit() {
  }

  clearLegDestinations() {
    this.newLeg.startDestination = null;
    this.newLeg.endDestination = null;
  }

  clearLegAddresses() {
    this.startAddress = null;
    this.endAddress = null;
  }


setEditLeg(){
  this.editLeg = Object.assign({}, this.selected)
}

createLeg(leg:Leg){

  this.legService.create(leg).subscribe({
    next:(madeLeg)=>{
      this.selected=madeLeg;
    },
    error: (failure) => {
      console.error('Error getting create leg');
      console.error(failure);
    }
  })
}

updateLeg(leg:Leg){
  this.legService.update(leg).subscribe({
    next:(updated)=>{
      this.selected=updated;
    },
    error: (failure) => {
      console.error('Error update leg');
      console.error(failure);
    }
  })
}


deleteLeg(id:number){
  this.legService.destroy(id).subscribe({

    next: () =>{
      this.selected=null;
    },
    error: (failure) => {
      console.error('Error getting todo list');
      console.error(failure);
    }
})

}









handleMapClick(mapEvent: google.maps.MapMouseEvent) {
  let lat = mapEvent.latLng;
  // console.log(lat);
  this.geocoder.geocode({ location: lat }).subscribe({
    next: (result) => {
      // console.log(result);
      let address = new Address();
      let geoAddress: any = result.results[0].address_components;
      address = this.addrPipe.transform(geoAddress);
      this.addressService.create(address).subscribe({
        next:(newAddress)=>{

          console.log(newAddress)

          let dest = new Destination();
          dest.address = newAddress
          dest.name = newAddress.city;
          console.log(dest);

          if (this.newLeg.startDestination == null) {
            this.destService.create(dest).subscribe({
              next:(madeDest)=>{
                this.newLeg.startDestination=madeDest
                // console.log(madeDest)
              },

            })


          }else if(this.newLeg.endDestination == null) {
            this.destService.create(dest).subscribe({
              next:(madeDest)=>{
                this.newLeg.endDestination=madeDest;
                // console.log(madeDest)
              }

            })
          } else {
            this.clearLegDestinations();
          }

          console.log(this.newLeg);
        },
      });
    }
    })
  }



}
