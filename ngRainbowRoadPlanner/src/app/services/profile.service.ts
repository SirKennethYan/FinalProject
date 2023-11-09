

import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { User } from '../models/user';
import { Observable, catchError, throwError } from 'rxjs';
import { Trip } from '../models/trip';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

//private baseUrl = 'http://localhost:8090/'; // adjust port to match server
private url = environment.baseUrl + 'api/users';

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

userById(id: number): Observable<User> {
  return this.http.get<User>(this.url+"/id/"+id, this.getHttpOptions() ).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('ProfileSrvc(): error retrieving User: ' + err)
      );
    })
  );





}

show(username:string):Observable <User>{
return this.http.get<User>(this.url+"/"+username, this.getHttpOptions() ).pipe(
  catchError((err: any) => {
    console.log(err);
    return throwError(
      () => new Error('ProfileSrvc(): error retrieving User: ' + err)
    );
  })
);


}

getSingleUserTrips(id:number):Observable <Trip[]> {
  return this.http.get<Trip[]>(this.url+"/trips/id/" + id, this.getHttpOptions() ).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('ProfileSrvc(): error retrieving Trips: ' + err)
      );
    })



  );


}

getUserTrips():Observable<Trip[]>{
  return this.http.get<Trip[]>(this.url+"/trips",this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('ProfileSrvc(): error retrieving Trips: ' + err)
      );
    })
  )




}

update(updatedUser:User): Observable<User>{
  return this.http.put<User>(this.url+"/"+updatedUser.id, updatedUser, this.getHttpOptions()).pipe(
    catchError((err: any)=>{
      console.error(err);
      return throwError(
        ()=> new Error('todoService.create(): error creating Todo')
      )
    })
  );

}













}
