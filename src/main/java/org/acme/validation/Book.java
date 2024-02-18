package org.acme.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

public class Book {

    @NotBlank(message="Title cannot be blank")
    public String title;

    @NotBlank(message="Author cannot be blank")
    public String author;

    @Min(message="Author has been very lazy", value=1)
    public double pages;
}