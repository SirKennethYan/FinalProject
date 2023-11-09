import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Leg } from '../models/leg';

@Injectable({
  providedIn: 'root'
})
export class LegService {

  //private baseUrl = 'http://localhost:8090/'; // adjust port to match server
  private url = environment.baseUrl + 'api/legs';

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

update(leg:Leg): Observable<Leg>{
  return this.http.put<Leg>(this.url+"/"+leg.id, leg, this.getHttpOptions()).pipe(
    catchError((err: any)=>{
      console.error(err);
      return throwError(
        ()=> new Error('LegService.create(): error creating Leg')
      )
    })
  );
 }


 destroy(id:number): Observable<void> {
  return this.http.delete<void>(this.url+"/"+ id, this.getHttpOptions()).pipe(
   catchError((err: any)=>{
     console.error(err);
     return throwError(
       ()=> new Error('LegService.create(): error deleting Leg')
     )
   })

  );


}

create(leg: Leg): Observable<Leg>{
  // if(leg.trip != null){
  //   leg.trip.id=1;
  // }
  return this.http.post<Leg>(this.url, leg, this.getHttpOptions()).pipe(
    catchError((err: any)=>{
      console.error(err);
      return throwError(
        ()=> new Error('LegService.create(): error creating Leg')
      )
    })
  );
}


show(id:number): Observable<Leg>{
  return this.http.get<Leg>(this.url+"/"+ id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('LegService.index(): error retrieving Leg: ' + err)
      );
    })
  );
}














}
