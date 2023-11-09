import { Destination } from './destination';

export class Address {
  id: number;
  street: string;
  street2: string;
  city: string;
  state: string;
  zip: string;
  active: boolean;
  longitude: number;
  latitude: number;

  constructor(
    id: number = 0,
    street: string = '',
    street2: string = '',
    city: string = '',
    state: string = '',
    zip: string = '',
    active: boolean = true,
    longitude: number=0,
  latitude: number=0

  ) {
    this.id = id;
    this.street = street;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.active = active;
    this.longitude = longitude;
  this.latitude = latitude;
  }
}
