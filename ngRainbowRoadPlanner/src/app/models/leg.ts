import { Destination } from './destination';
import { Trip } from './trip';

export class Leg {
  id: number;
  estimatedMiles: number;
  trip: Trip | null;
  actualMiles: number;
  name: string;
  description: string;
  startDestination: Destination | null;
  endDestination: Destination | null;
  legNumber: number;
  notes: string;

  constructor(
    id: number = 0,
    estimatedMiles: number = 0,
    actualMiles: number = 0,
    name: string = '',
    description: string = '',
    legNumber: number = 0,
    notes: string = '',
    startDestination: Destination | null= null,
    endDestination: Destination | null =null,
    trip: Trip | null = null
  ) {
    this.id = id;
    this.estimatedMiles = estimatedMiles;
    this.trip = trip;
    this.actualMiles = actualMiles;
    this.name = name;
    this.description = description;
    this.startDestination = startDestination;
    this.endDestination = endDestination;
    this.legNumber = legNumber;
    this.notes = notes;
  }
}
