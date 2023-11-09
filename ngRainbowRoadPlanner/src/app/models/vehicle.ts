import { User } from "./user";

export class Vehicle {
  id: number;
  make: string;
  model: string;
  estimatedMPG: number;
  estimatedRange: number;
  capacity: number;
  isElectric: boolean;
  trips: any[] | undefined;
  user: User | null;

  constructor(
    id: number = 0,
    make: string = "",
    model: string = "",
    estimatedMPG: number = 0,
    estimatedRange: number = 0,
    capacity: number = 0,
    isElectric: boolean = false,
    trips: any[] = [],
    user: User | null = null
  ){
    this.id = id;
    this.make = make;
    this.model = model;
    this.estimatedMPG = estimatedMPG;
    this.estimatedRange = estimatedRange;
    this.capacity = capacity;
    this.isElectric = isElectric;
    this.trips = trips;
    this.user = user;

  }

}
