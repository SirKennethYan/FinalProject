import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Destination } from '../models/destination';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {

//private baseUrl = 'http://localhost:8090/'; // adjust port to match server
private url = environment.baseUrl + 'api/destinations';

constructor(private http: HttpClient, private auth: AuthService) { }


getHttpOptions() {
  let options = {
    headers: {
      Authorization: 'Basic ' + this.auth.getCredentials(),
      'X-Requested-With': 'XMLHttpRequest',
    },
  };
  return options;
}

create(dest: Destination):Observable<Destination>{
  return this.http.post<Destination>(this.url, dest, this.getHttpOptions()).pipe(
    catchError((err: any)=>{
      console.error(err);
      return throwError(
        ()=> new Error('DestinationService.get(): error with create ')
      )
    })
)
}


update(dest:Destination): Observable<Destination>{
  return this.http.put<Destination>(this.url+dest.id, dest, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('DestinationService.update(): error update destination: ' + err)
      );
    })

  )

}

showAllOfType(keyword:string):Observable<Destination[]>{
  return this.http.get<Destination[]>(this.url+"/type/"+ keyword,  this.getHttpOptions()).pipe(
    catchError((err: any)=>{
      console.error(err);
      return throwError(
        ()=> new Error('DestinationService.get(): error getting destinations ')
      )
    })
)
}



showAll():Observable<Destination[]>{
  return this.http.get<Destination[]>(this.url,  this.getHttpOptions()).pipe(
    catchError((err: any)=>{
      console.error(err);
      return throwError(
        ()=> new Error('DestinationService.get(): error getting destinations ')
      )
    })
)
}

destroy(id:number): Observable<void> {
  return this.http.delete<void>(this.url+"/"+ id, this.getHttpOptions()).pipe(
   catchError((err: any)=>{
     console.error(err);
     return throwError(
       ()=> new Error('destinationService.delete(): error deleting destination')
     )
   })

  );


}




}
