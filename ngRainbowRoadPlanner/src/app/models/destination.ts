import { Accomodation } from './accomodation';
import { Address } from './address';
export class Destination {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  notes: string;
  phone: string;
  fee: number;
  active: boolean;
  accomodations: any[] | undefined;
  activity: any[] | undefined;
  address: Address;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    imageUrl: string = '',
    notes: string = '',
    phone: string = '',
    fee: number = 0,
    active: boolean = true,
    accomodations: any[] = [],
    activity: any[] = [],
    address: Address = new Address()
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.notes = notes;
    this.phone = phone;
    this.fee = fee;
    this.active = active;
    this.accomodations = accomodations;
    this.activity = activity;
    this.address = address;
  }
}
