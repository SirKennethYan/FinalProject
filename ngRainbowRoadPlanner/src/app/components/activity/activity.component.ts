import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Activity } from 'src/app/models/activity';
import { ActivityService } from 'src/app/services/activity.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent {


selected: Activity | null = null;
newTrip: Activity = new Activity();



constructor(private activityService: ActivityService,
  private auth: AuthService,
  private route: ActivatedRoute,
  private router: Router,){

}


createActivity(activity: Activity){
  this.activityService.create(activity).subscribe({
    next:(madeActivity)=>{
      this.selected=madeActivity;
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
    this.selected=updatedActivity;
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

}
