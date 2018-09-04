import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserViewComponent } from './user-view/user-view.component';
import { LoginComponent } from './login/login.component';
import { RoomViewComponent } from  './room-view/room-view.component';
import { RoomListComponent } from  './room-list/room-list.component';
import { MeetingViewComponent } from  './meeting-view/meeting-view.component';
import { MeetingListComponent } from  './meeting-list/meeting-list.component';
import { GroupViewComponent } from  './group-view/group-view.component';
import {GroupListComponent } from  './group-list/group-list.component';
import { PlacesListComponent } from './places-list/places-list.component';
import { PlacesViewComponent } from './places-view/places-view.component';

const appRoutes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'user', component: UserListComponent },
  { path: 'user/create/=id', component: UserViewComponent },
  { path: 'meeting', component: MeetingListComponent },
  { path: 'meeting/create/=id', component: MeetingViewComponent },
  { path: 'group/create/=id', component: GroupViewComponent },
  { path: 'group', component: GroupListComponent },
  { path: 'place/create/=id', component: PlacesViewComponent },
  { path: 'place', component: PlacesListComponent },
  { path: 'room/create/=id', component: RoomViewComponent },
  { path: 'room', component: RoomListComponent }


];
@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    UserListComponent,
    UserViewComponent,
    LoginComponent,
    RoomViewComponent,
    RoomListComponent,
    MeetingViewComponent,
    MeetingListComponent,
    GroupViewComponent,
    GroupListComponent,
    PlacesListComponent,
    PlacesViewComponent
  ],

  imports: [
    BrowserModule,
   RouterModule.forRoot(appRoutes)
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
