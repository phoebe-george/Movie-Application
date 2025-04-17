import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  private apiUrl = environment.omdbApiUrl;

  constructor(
    private readonly http: HttpClient,
    private readonly authService: AuthService
  ) {}

  private readonly DB_URL = 'http://localhost:8083/movies';

  private getAuthHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  GetAllMovies() {
    return this.http.get(`${this.DB_URL}/all`, {
      headers: this.getAuthHeaders(),
    });
  }

  GetMovieByID(id: number) {
    return this.http.get<any[]>(`${this.DB_URL}/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }

  deleteMovie(id: number) {
    return this.http.delete<any[]>(`${this.DB_URL}/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }

  AddMovie(movie: any) {
    return this.http.post<any>(`${this.DB_URL}/add`, movie, {
      headers: this.getAuthHeaders(),
    });
  }

  searchMovie(title: string): Observable<any> {
    const url = `${this.apiUrl}?apikey=${environment.omdbApiKey}&t=${title}`;
    return this.http.get<any>(url).pipe(
      map((response) => {
        return {
          movieName: response.Title,
          type: response.Type,
          releaseYear: response.Year,
          poster: response.Poster,
          rate: response.imdbRating,
        };
      })
    );
  }
}
