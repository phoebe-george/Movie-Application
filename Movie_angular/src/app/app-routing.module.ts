import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
import { MoviesListComponent } from './pages/movies-list/movies-list.component';
import { RegisterComponent } from './pages/register/register.component';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'movies', component: MoviesListComponent,  },
  { path: 'movie-details/:id', component: MovieDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
