package io.springboot.movieapp.service;


import io.springboot.movieapp.domain.dto.request.MovieRequest;
import io.springboot.movieapp.domain.dto.response.MovieResponse;
import io.springboot.movieapp.domain.entity.Movie;
import io.springboot.movieapp.domain.enums.UserRole;
import io.springboot.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public MovieResponse addMovie(MovieRequest request) throws AccessDeniedException {
        System.out.println(request.getMovieName());
        if (UtilityService.getCurrentUser().getRole() != UserRole.ADMIN) {
            throw new AccessDeniedException("Only admin can add movies.");
        }
        Movie movie = Movie.builder()
                .movieName(request.getMovieName())
                .type(request.getType())
                .rate(request.getRate())
                .releaseYear(request.getYear())
                .poster(request.getPoster())
                .build();
        System.out.println(movie.getMovieName());
        return mapToResponse(movieRepository.save(movie));
    }

    public void deleteMovie(Long id) throws AccessDeniedException {
        if (UtilityService.getCurrentUser().getRole() != UserRole.ADMIN) {
            throw new AccessDeniedException("Only admin can delete movies.");
        }
        movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        movieRepository.deleteById(id);
    }

    public MovieResponse getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    private MovieResponse mapToResponse(Movie movie) {
        return new MovieResponse(
                movie.getMovieName(),
                movie.getId(),
                movie.getType(),
                movie.getReleaseYear(),
                movie.getPoster(),
                movie.getRate() != null ? movie.getRate().toString() : "0"
        );
    }

}
