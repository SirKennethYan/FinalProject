import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/address';
import { AddressService } from 'src/app/services/address.service';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  constructor(private addressService: AddressService) { }

  ngOnInit() {
  }

  createAddress(address:Address){
    this.addressService.create(address).subscribe({
        next:(created)=>{
          console.log(created)
        },
        error: (failure) => {
          console.error('Error create address');
          console.error(failure);
        }


    })
  }



}
