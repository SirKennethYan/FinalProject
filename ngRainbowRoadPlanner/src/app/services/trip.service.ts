import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { Trip } from '../models/trip';

@Injectable({
  providedIn: 'root',
})
export class TripService {
  //private baseUrl = 'http://localhost:8090/'; // adjust port to match server
  private url = environment.baseUrl + 'api/trips';

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


  getTrips(): Observable<Trip[]> {
    return this.http.get<Trip[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('TripService.getTrips(): error retrieving trips: ' + err)
        );
      })
    );
  }

  getSingleTrip(id: number): Observable<Trip> {
    return this.http.get<Trip>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('TripService.getSingleTrip(): error retrieving trip: ' + err)
        );
      })
    );
  }


  create(trip: Trip): Observable<Trip> {
    console.log(trip);
    return this.http.post<Trip>(this.url, trip, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('tripService.create(): error creating Trip' + trip)
        );
      })
    );
  }

  index(): Observable<Trip[]> {
    return this.http.get<Trip[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TripService.index(): error retrieving trips: ' + err)
        );
      })
    );
  }


  viewAll(): Observable<Trip[]> {
    return this.http.get<Trip[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TripService.index(): error retrieving trips: ' + err)
        );
      })
    );
  }


  update(trip: Trip): Observable<Trip> {
    return this.http
      .put<Trip>(this.url + trip.id, trip, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('TripService.index(): error update trip: ' + err)
          );
        })
      );
  }
  destroy(id: number): Observable<void> {
    return this.http
      .delete<void>(this.url + '/' + id, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('tripService.destroy(): error deleting Trip')
          );
        })
      );
  }
}
