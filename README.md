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