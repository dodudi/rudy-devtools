package kr.it.rudy.server.hash.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.it.rudy.server.hash.domain.model.HashAlgorithm;

public record HashVerifyRequest(
        @NotEmpty(message = "text must not be empty or null") String text,
        @NotNull(message = "algorithm must not be null") HashAlgorithm algorithm,
        @NotEmpty(message = "expectedHash must not be empty or null") String expectedHash
) {
}
