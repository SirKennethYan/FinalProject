import { Leg } from "./leg";

export class Activity {
  id: number;
  legId: number;
  destinationId: number;
  didStop: boolean;
  description: string;
  priorityLevel: number;
  timeToSpend: string;
  leg: Leg | null;

  constructor(
  id: number = 0,
  legId: number = 0,
  destinationId: number = 0,
  didStop: boolean = false,
  description: string = '',
  priorityLevel: number = 0,
  timeToSpend: string = '',
  leg: Leg | null = null
  ){
    this.id = id;
    this.legId = legId;
    this.destinationId = destinationId;
    this.didStop = didStop;
    this.description = description;
    this.priorityLevel = priorityLevel;
    this.timeToSpend = timeToSpend;
    this.leg = leg;
  }

}
