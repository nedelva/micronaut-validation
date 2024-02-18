package org.acme.validation;

import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;

import java.util.Set;
import java.util.stream.Collectors;

//@Path("/books")
@Validated
@Controller("/books")
public class BookResource {

    @Inject
    Validator validator;

    @Get
    @Produces("text/plain")
    public String index() {
        return "hello";
    }

    //@Path("/manual-validation")
    //@POST
    @Post("/manual-validation")
    public Result tryMeManualValidation(@Body Book book) {
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if (violations.isEmpty()) {
            return new Result("Book is valid! It was validated by manual validation.");
        } else {
            return new Result(violations);
        }
    }

    //@Path("/end-point-method-validation")
    //@POST
    //@Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    @Post("/end-point-method-validation")
    public Result tryMeEndPointMethodValidation(@Valid @Body Book book) {
        return new Result("Book is valid! It was validated by end point method validation.");
    }

    @Inject BookService bookService;

    @Post("/service-method-validation")
    //@POST
    public Result tryMeServiceMethodValidation(@Body Book book) {
        try {
            bookService.validateBook(book);
            return new Result("Book is valid! It was validated by service method validation.");
        } catch (ConstraintViolationException e) {
            return new Result(e.getConstraintViolations());
        }
    }

    public static class Result {

        Result(String message) {
            this.success = true;
            this.message = message;
        }

        Result(Set<? extends ConstraintViolation<?>> violations) {
            this.success = false;
            this.message = violations.stream()
                    .map(cv -> cv.getMessage())
                    .collect(Collectors.joining(", "));
        }

        private String message;
        private boolean success;

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

    }
}