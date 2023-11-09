import { Destination } from "./destination";

export class Accomodation {

id:number;
name:string;
description:string;
iconUrl:string;
destination: Destination | null;

constructor(

  id:number=0,
  name:string='',
  description:string='',
  iconUrl:string='',
  destination: Destination| null = null
  )
  {

    this.id=id;
    this.name=name;
    this.description= description;
    this.iconUrl=iconUrl;
    this.destination= destination;
  }





}
