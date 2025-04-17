package io.springboot.movieapp.domain.dto.response;

import lombok.Builder;

@Builder
public record SuccessResponse(
        String message,
        int statusCode
) {
}
