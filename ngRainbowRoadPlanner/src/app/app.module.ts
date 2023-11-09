import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DatePipe } from '@angular/common';
import { LogoutComponent } from './components/logout/logout.component';
import { SignupComponent } from './components/signup/signup.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginComponent } from './components/login/login.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { ReactiveFormsModule } from '@angular/forms';
import { TripComponent } from './components/trip/trip.component';
import { ActivityComponent } from './components/activity/activity.component';
import { GeoResultToAddressPipe } from './pipes/geo-result-to-address.pipe';
import { CommentComponent } from './components/comment/comment.component';
import { LegComponent } from './components/leg/leg.component';
import { DestinationComponent } from './components/destination/destination.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LogoutComponent,
    SignupComponent,
    ProfileComponent,
    LoginComponent,
    TripComponent,
    ActivityComponent,
    GeoResultToAddressPipe,
    CommentComponent,
    LegComponent,
    DestinationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    GoogleMapsModule,
  ],
  providers: [DatePipe, NgbActiveModal, GeoResultToAddressPipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
