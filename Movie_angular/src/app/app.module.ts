// import { NgModule } from '@angular/core';
// import { BrowserModule } from '@angular/platform-browser';
// import { FormsModule } from '@angular/forms';

// import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
// import { LoginComponent } from './pages/login/login.component';
// import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
// import { MoviesListComponent } from './pages/movies-list/movies-list.component';
// import { RegisterComponent } from './pages/register/register.component';
// import { MoviesCardComponent } from './shared/movies-card/movies-card.component';
// import { HttpClientModule } from '@angular/common/http';

// @NgModule({
//   declarations: [
//     AppComponent,
//     LoginComponent,
//     MovieDetailsComponent,
//     MoviesListComponent,
//     RegisterComponent,
//     MoviesCardComponent
//   ],
//   imports: [
//     FormsModule,
//     BrowserModule,
//     AppRoutingModule
//   ],
//   providers: [],
//   bootstrap: [AppComponent]
// })
// export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
import { MoviesListComponent } from './pages/movies-list/movies-list.component';
import { RegisterComponent } from './pages/register/register.component';
import { MoviesCardComponent } from './shared/movies-card/movies-card.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MovieDetailsComponent,
    MoviesListComponent,
    RegisterComponent,
    MoviesCardComponent,
  ],
  imports: [FormsModule, BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}