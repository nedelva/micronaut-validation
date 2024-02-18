package org.acme.validation;

//import jakarta.enterprise.context.ApplicationScoped;
import io.micronaut.validation.Validated;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;

@Validated
@Singleton
public class BookService {

    public void validateBook(@Valid Book book) {
        // your business logic here
    }
}
