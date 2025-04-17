package io.springboot.movieapp.domain.dto.request;


import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MovieRequest {
    private String movieName ;
    private String type ;
    private String year ;
    private String poster ;
    private Double rate;
}
