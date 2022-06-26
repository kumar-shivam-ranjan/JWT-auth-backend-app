# JWT-auth-backend-app
This codebase was created to demonstrate a fully fledged backend driven application built with Spring boot following the best industry standard practices
to build secure REST APIs using JWT authentication , has proper exception handling and follows OOPs design principles. The usecase is blogging application.


# Requirements
For building and running the application you need:
- JDK 1.8
- Maven 3

## ApiResponse

```java
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ApiResponse<E> implements Serializable {
  private E data;
  private List<Error> errors;
}

```


# Help
Please fork and PR to improve the project.
