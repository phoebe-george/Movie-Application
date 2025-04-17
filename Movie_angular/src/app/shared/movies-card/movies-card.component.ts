import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-movies-card',
  templateUrl: './movies-card.component.html',
  styleUrls: ['./movies-card.component.scss'],
})
export class MoviesCardComponent {
  @Input() movie: any;
  @Input() isNew: boolean = false;
  @Output() refreshMovies = new EventEmitter<void>();
  // @Input() isAdmin: boolean = false;
  isAdmin: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    private movieService: MovieService
  ) {}

  ngOnInit(): void {
    this.isAdmin = this.authService.getRole() === 'ADMIN';
  }

  addMovie() {
    console.log('Adding movie:', this.movie.imdbId);
    // call add movie service if needed
    this.movie.isAdded = true;
  }

  // addMovie() {
  //   console.log('Adding movie:', this.movie.movieName);
  //   this.movieService.AddMovie(this.movie).subscribe({
  //     next: () => {
  //       console.log('Movie adding successfully.');
  //       this.movie.isAdded = false;
  //     },
  //     error: (err) => {
  //       console.error('Error adding movie:', err);
  //     }
  //   });
  // }

  removeMovie() {
    console.log('Removing movie:', this.movie.imdbId);

    this.movieService.deleteMovie(this.movie.id).subscribe({
      next: () => {
        console.log('Movie removed successfully.');
        this.movie.isAdded = false;
        this.refreshMovies.emit();
      },
      error: (err) => {
        console.error('Error removing movie:', err);
      },
    });
  }

  viewDetails() {
    this.router.navigate(['/movie-details', this.movie.id]);
  }
}
