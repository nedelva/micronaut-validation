## Micronaut Quickstart Validation

Sample Micronaut project demonstrating how to use validation with Jakarta Validation API.

This is a direct translation of Quarkus' [validation-quickstart](https://github.com/quarkusio/quarkus-quickstarts/tree/main/validation-quickstart) project.

### Key differences
- In Micronaut one must use `@io.micronaut.validation.Validated` to be able to activate the validation; merely using `@jakarta.validation.Valid` is not enough. 
  `@Validated` had to be used on both `BookResource` controller and `BookService`.
- The Restassured tests methods must use an argument `RequestSpecification` otherwise the library won't be able to connect to the test server.
- The test method `BookResourceTest#testBookWithoutTitleEndPointValidation` had to be adjusted to account for the error response:
```
{
   "message": "Bad Request",
   "_links": {
        "self": {
          "href": "/books/end-point-method-validation",
          "templated": false
        }
  },
  "_embedded": {
     "errors": [
       {
          "message": "book.title: Title cannot be blank"
       }
    ]
  }
}
```
Contrast this with Quarkus' simpler response:
````
{
    "title": "Constraint Violation",
    "status": 400,
    "violations": [
        {
            "field": "tryMeEndPointMethodValidation.book.title",
            "message": "Title cannot be blank"
        }
    ]
}
````
 
## Micronaut 4.2.3 Documentation

- [User Guide](https://docs.micronaut.io/4.2.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.2.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.2.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Maven Plugin documentation](https://micronaut-projects.github.io/micronaut-maven-plugin/latest/)
## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


## Feature hibernate-validator documentation

- [Micronaut Hibernate Validator documentation](https://micronaut-projects.github.io/micronaut-hibernate-validator/latest/guide/index.html)


## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#nettyHttpClient)


## Feature hamcrest documentation

- [https://hamcrest.org/JavaHamcrest/](https://hamcrest.org/JavaHamcrest/)


## Feature maven-enforcer-plugin documentation

- [https://maven.apache.org/enforcer/maven-enforcer-plugin/](https://maven.apache.org/enforcer/maven-enforcer-plugin/)


