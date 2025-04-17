import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/service/movie.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-movies-list',
  templateUrl: './movies-list.component.html',
  styleUrls: ['./movies-list.component.scss'],
})
export class MoviesListComponent implements OnInit {
  movies: any[] = [];
  isAdmin: boolean = true;
  searchTerm: string = '';

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private MService: MovieService
  ) {}

  ngOnInit(): void {
    this.loadAllMovies();
    // this.isAdmin = this.authService.getRole() === 'ADMIN';
  }

  loadAllMovies(): void {
    this.MService.GetAllMovies().subscribe({
      next: (data: any) => {
        this.movies = data.map((movie: any) => ({
          ...movie,
          isNew: false,
        }));
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  searchMovies(): void {
    if (this.searchTerm.trim() === '') {
      this.loadAllMovies();
      return;
    }

    this.MService.searchMovie(this.searchTerm).subscribe({
      next: (data) => {
        this.movies = data.map((movie: any) => ({
          ...movie,
          isNew: true,
        }));
      },
      error: (err) => {
        console.error(err);
      },
    });
  }
}
