# ecommerce service

This project is a Spring Boot application that implements a product pricing API using **Hexagonal Architecture (Ports & Adapters)**. It exposes a REST endpoint for querying the price of a product at a specific date and brand context.

---

## 1. Architecture: Hexagonal (Ports & Adapters)

Most relevant packages include:
- **domain:** Core business logic (Model, UseCases, Ports)
- **application:** UseCase Implementations (e.g. PriceQueryUseCaseImpl)
- **adapter:**
  - **in/rest:** REST Controllers (e.g. PriceController)
  - **out/persistence:** JPA Repositories & DB Adapters
- **test:**
  - **adapter/in/rest:** unit test for **PriceController** class
  - **adapter/out/persistence:** unit test for **PriceRepositoryAdapter** class
  - **application/service:** unit test for **PriceQueryUseCaseImpl** class
  - **integration:** integration test to validate the 5 tests cases from the challenge

The project also includes:
- **resources:** _application.yml_ "base" Spring profile configuration
- **resources/db:**
  - **data:** _real_data_prices.csv_ and _test_data_prices.csv_ for Flyway initial CSV load
  - **migration:** Flyway migrations (schema creation and load)
- **test/resources:** _application-test.yml_ "test" Spring profile configuration

## 2. Features

- Query the price of a product based on:
  - **application date**
  - **product ID**
  - **brand ID**
- Follows **Hexagonal Architecture** using Ports & Adapters
- Uses **Flyway** and **CSV data** for test + dev environments
- **Integration tested** using `@SpringBootTest` and `MockMvc`

## 3. Key Classes

### `PriceController` (adapter-in)
### `PriceQueryUseCase` & `PriceQueryUseCaseImpl` (domain port & application service)
### `PriceRepositoryPort` & `PriceRepositoryAdapter` (adapter-out)

## 4. Database and Test Data

- Uses H2 (in-memory) both "test" and "default" profiles.
- Database tables and seed data loaded using Flyway:

resources/db/
- migration/V1__init_schema.sql
- data/test_data_prices.csv
- data/load_prices.sql

## 5. Testing

- All tests are under **src/test**:
  - **adapter/in/rest:** unit test for **PriceController** class
  - **adapter/out/persistence:** unit test for **PriceRepositoryAdapter** class
  - **application/service:** unit test for **PriceQueryUseCaseImpl** class
  - **integration:** integration test to validate the 5 tests cases from the challenge

## 6. Run the App

`./gradlew bootRun --args='--spring.profiles.active=default'`
