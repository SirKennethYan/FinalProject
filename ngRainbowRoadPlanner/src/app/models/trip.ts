import { Destination } from './destination';
import { User } from './user';
import { Vehicle } from './vehicle';

export class Trip {
  id: number;
  startDate: string;
  endDate: string;
  roundTrip: boolean | null | '';
  miles: number;
  createDate: string;
  updateDate: string;
  title: string;
  description: string;
  imageUrl: string;
  active: boolean;
  legs: any[] | null;
  vehicle: Vehicle | null;
  comments: any[] | null;
  user: User | null;
  startDestination: Destination | null;
  endDestination: Destination | null;

  constructor(
    id: number = 0,
    startDate: string = '',
    endDate: string = '',
    roundTrip: boolean = false,
    miles: number = 0,
    createDate: string = '',
    updateDate: string = '',
    title: string = '',
    description: string = '',
    imageUrl: string = '',
    active: boolean = false,
    legs: any[] = [],
    vehicle: Vehicle | null = null,
    comments: any[] = [],
    user: User | null = null,
    startDestination: Destination | null = null,
    endDestination: Destination | null = null
  ) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.roundTrip = roundTrip;
    this.miles = miles;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
    this.active = active;
    this.legs = legs;
    this.vehicle = vehicle;
    this.comments = comments;
    this.user = user;
    this.startDestination = startDestination;
    this.endDestination = endDestination;
  }
}
