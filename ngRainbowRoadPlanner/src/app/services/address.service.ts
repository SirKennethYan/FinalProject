import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Address } from '../models/address';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddressService {


//private baseUrl = 'http://localhost:8090/'; // adjust port to match server
private url = environment.baseUrl + 'api/addresses';


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


create(address: Address): Observable<Address>{
  return this.http.post<Address>(this.url, address, this.getHttpOptions()).pipe(
    catchError((err: any)=>{
      console.error(err);
      return throwError(
        ()=> new Error('DestinationService.get(): error with create ')
      )
    })

  )

}


}
