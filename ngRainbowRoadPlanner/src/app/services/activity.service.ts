import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Trip } from '../models/trip';
import { Activity } from '../models/activity';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

   //private baseUrl = 'http://localhost:8090/'; // adjust port to match server
private url = environment.baseUrl + 'api/activities';

constructor(private http: HttpClient, private datePipe: DatePipe, private auth: AuthService) { }

getHttpOptions() {
  let options = {
    headers: {
      Authorization: 'Basic ' + this.auth.getCredentials(),
      'X-Requested-With': 'XMLHttpRequest',
    },
  };
  return options;
}


create(activity:Activity):Observable <Activity>{
console.log(activity);
  return this.http.post<Activity>(this.url, activity, this.getHttpOptions()).pipe(
    catchError((err: any)=>{
      console.error(err);
      return throwError(
        ()=> new Error('tripService.create(): error creating Activity'+ activity)
      )
    })
)

}

index(): Observable<Activity[]>{
  return this.http.get<Activity[]>(this.url, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('TripService.index(): error retrieving activites: ' + err)
      );
    })
  )
}

update(activity: Activity): Observable<Activity>{
  return this.http.put<Activity>(this.url+activity.id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('TripService.index(): error update activity: ' + err)
      );
    })

  )

}

destroy(id: number): Observable<void> {
  return this.http
    .delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('activityService.destroy(): error deleting Activity: ' + err)
        );
      })
    );
}


}
