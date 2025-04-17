package io.springboot.movieapp.controller;

import io.springboot.movieapp.domain.dto.request.MovieRequest;
import io.springboot.movieapp.domain.dto.response.MovieResponse;
import io.springboot.movieapp.domain.dto.response.SuccessResponse;
import io.springboot.movieapp.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    final private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public List<MovieResponse> getAll() {
        return movieService.getAllMovies();
    }


    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody MovieRequest movie) {
        try {
            return ResponseEntity.ok(movieService.addMovie(movie));
        }
        catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin can add movies.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteMovie(@PathVariable Long id) throws AccessDeniedException {
        movieService.deleteMovie(id);
        SuccessResponse response = SuccessResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message("The movie delete Successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
//    @GetMapping("/search")
//    public ResponseEntity<MovieResponse> searchMovie(@RequestParam String title) {
//        String url = "https://www.omdbapi.com/?apikey=373b73e1&t=" + title;
//        ResponseEntity<Map<String, Object>> response = restTemplate.getForEntity(url, new ParameterizedTypeReference<>() {});
//
//        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
//            Map<String, Object> body = response.getBody();
//
//            MovieResponse movie = MovieResponse.builder()
//                    .movieName((String) body.get("Title"))
//                    .type((String) body.get("Type"))
//                    .year((String) body.get("Year"))
//                    .poster((String) body.get("Poster"))
//                    .rate((String) body.get("imdbRating"))
//                    .build();
//
//            return ResponseEntity.ok(movie);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

}
