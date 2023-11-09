import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { Vehicle } from '../models/vehicle';

@Injectable({
  providedIn: 'root',
})
export class VehicleService {
  private url = environment.baseUrl + 'api/vehicles/';

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private auth: AuthService
  ) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TripService.index(): error retrieving trips: ' + err)
        );
      })
    );
  }

  findUsersVehicles(id: number): Observable<Vehicle[]> {
    return this.http
      .get<Vehicle[]>(this.url + 'user/' + id, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error('TripService.index(): error retrieving trips: ' + err)
          );
        })
      );
  }

  create(vehicle: Vehicle) {
    return this.http
      .post<Vehicle>(this.url, vehicle, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('TripService.index(): error create vehicle: ' + err)
          );
        })
      );
  }

  delete(id: number) {
    return this.http.delete<Vehicle>(this.url+ id, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('TripService.index(): error delete vehicle: ' + err)
          );
        })
      );
  }

  getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'VehicleService.index(): error retrieving vehicles: ' + err
            )
        );
      })
    );
  }
}
