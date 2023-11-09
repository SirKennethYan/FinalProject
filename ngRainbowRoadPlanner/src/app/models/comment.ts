import { User } from "./user";
import { Trip } from "./trip";

export class Comment {
  id: number;
  photo: string;
  description: string;
  commentDate: string;
  user: User | null;
  trip: Trip | null;
  active: boolean;

  constructor(
    id: number = 0,
  photo: string = "",
  description: string = "",
  commentDate: string = "",
  user: User | null = null,
  trip: Trip | null = null,
  active: boolean = false
  ){
    this.id = id;
    this.photo = photo;
    this.description = description;
    this.commentDate = commentDate;
    this.user = user;
    this.trip = trip;
    this.active = active;
  }
}
