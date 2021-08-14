package com.example.demo.shipment.web.rest.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Utility class for ResponseEntity creation.
 */
public interface ResponseUtil {
    /**
     * Wrap the optional into a {@link ResponseEntity} with an {@link
     * HttpStatus#OK} status, or if it's empty, it returns a {@link
     * ResponseEntity} with {@link
     * HttpStatus#NOT_FOUND}.
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @return response containing {@code maybeResponse} if present or {@link
     * HttpStatus#NOT_FOUND}
     */
    static <X> ResponseEntity<X> wrapNotFound(Optional<X> maybeResponse) {
        return wrapNotFound(maybeResponse, null);
    }

    /**
     * Wrap the optional into a {@link ResponseEntity} with an {@link
     * HttpStatus#OK} status with the headers, or if it's empty, throws a
     * {@link ResponseStatusException} with status {@link
     * HttpStatus#NOT_FOUND}.
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @param headers       headers to be added to the response
     * @return response containing {@code maybeResponse} if present
     */
    static <X> ResponseEntity<X> wrapNotFound(Optional<X> maybeResponse, HttpHeaders headers) {
        return maybeResponse
                .map(res -> ResponseEntity.ok().headers(headers).body(res))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
