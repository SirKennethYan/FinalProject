import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Activity } from 'src/app/models/activity';
import { Destination } from 'src/app/models/destination';
import { ActivityService } from 'src/app/services/activity.service';
import { AuthService } from 'src/app/services/auth.service';
import { DestinationService } from 'src/app/services/destination.service';

@Component({
  selector: 'app-destination',
  templateUrl: './destination.component.html',
  styleUrls: ['./destination.component.css']
})
export class DestinationComponent implements OnInit {


  destination: Destination = new Destination();
  editDestination: Destination | null = null;
  selected: Destination | null = null;
  destinations: Destination[] | null = null;
  selectedActivity: Activity | null = null;
  newActivity: Activity = new Activity();

  constructor(
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private destService: DestinationService,
    private activityService: ActivityService
  ) { }

  ngOnInit() {


  }

  createActivity(activity: Activity){
    this.newActivity = activity;
    this.newActivity.destinationId = 3;
    console.log(this.newActivity);
    this.newActivity.legId = 3;
    this.activityService.create(this.newActivity).subscribe({
      next:(madeActivity)=>{
        this.selectedActivity = madeActivity;
        console.log(madeActivity)
        this.reload();
      },
      error: (fail) => {
        console.error('Error creating activity', fail);
        console.error(fail);
      },

    })
  }

  updateActivity(activity: Activity){
    this.activityService.update(activity).subscribe({
      next:(updatedActivity)=>{
      this.selectedActivity = updatedActivity;
      this.reload();
      },
      error: (fail) => {
        console.error('Error updating activity', fail);
        console.error(fail);
      },
    })
  }

  deleteActivity(id: number) {
    console.log(id);
    this.activityService.destroy(id).subscribe({
      next: (result) => {
        this.reload();
      },
      error: (fail) => {
        console.error('Error deleting activity', fail);
        console.error(fail);
      },
    });
  }

  reload() {

  }

 findByType(type: string){
  this.destService.showAllOfType(type).subscribe({
    next:(found)=>{
      this.destinations = found;
      console.log(this.destinations);

    },
    error: (failure) => {
      console.error('Error getting destination list');
      console.error(failure);
    }

  })





 }
showAll(){
  this.destService.showAll().subscribe({
    next:(destinationList)=>{
      this.destinations=destinationList;
    },
    error: (failure) => {
      console.error('Error getting destination list');
      console.error(failure);
    }
  })
}

updateDestination(dest: Destination){
  this.destService.update(dest).subscribe({
    next:(updated)=>{
    this.selected=updated;
    }
  })
}

createDestination(dest: Destination){
  this.destService.create(dest).subscribe({
    next:(madeDest)=>{
      this.selected=madeDest;
      // console.log(madeDest)
    }

  })
}

deleteDestination(id: number){
  this.destService.destroy(id).subscribe({

    next: () =>{

    },
    error: (failure) => {
      console.error('Error getting todo list');
      console.error(failure);
    }
  })


}
















}
