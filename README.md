# FinancialApp

Basic financial app using Spring Boot that allows users to track all their bank accounts in a single place.

## Overview

FinancialApp is a backend API built with Spring Boot to centralize and manage user financial accounts and related data. It uses Spring Data JPA for persistence, PostgreSQL as the database, environment-based configuration via `.env`, and includes testing support and cryptographic utilities.

## Quick Facts

| Item            | Value                                  |
|-----------------|----------------------------------------|
| Language        | Java (100%)                            |
| Java            | 21                                     |
| Framework       | Spring Boot 3.5.x                      |
| Persistence     | Spring Data JPA (Hibernate)            |
| Database        | PostgreSQL                             |
| Config          | spring-dotenv (.env file)              |
| Build Tool      | Maven (with Maven Wrapper)             |
| Utilities       | Spring Security Crypto, Lombok         |

## Features

| Feature                                              | Description                                                                 | Status     |
|------------------------------------------------------|-----------------------------------------------------------------------------|------------|
| Account aggregation                                  | Track user bank accounts and related financial records in one place         | Available  |
| RESTful API                                          | CRUD endpoints for core domain entities                                     | Available  |
| JPA persistence                                      | Spring Data JPA repositories and Hibernate ORM                              | Available  |
| PostgreSQL support                                   | JDBC driver and configuration for PostgreSQL                                | Available  |
| Environment-driven config                            | `.env` variables loaded at runtime via spring-dotenv                        | Available  |
| Crypto utilities                                     | Password/secret hashing with Spring Security Crypto                         | Available  |
| Testing support                                      | Spring Boot testing starter for unit/integration tests                      | Available  |
| Reduced boilerplate                                  | Lombok annotations (getters/setters/builders, etc.)                         | Available  |

> For concrete endpoints, request/response models, and entity relationships, see the source under `src/main/java` (controllers, services, repositories, entities).

## Tech Stack

| Area          | Component                         |
|---------------|-----------------------------------|
| Runtime       | Java 21                           |
| Framework     | Spring Boot 3.5.x                 |
| Web           | spring-boot-starter-web           |
| Data          | spring-boot-starter-data-jpa      |
| Testing       | spring-boot-starter-test          |
| Security/Crypto | spring-security-crypto          |
| Config        | spring-dotenv                     |
| DB Driver     | PostgreSQL JDBC                   |
| Codegen       | Lombok                            |
| Build         | Maven + Maven Wrapper (`mvnw`)    |

## Requirements

| Requirement  | Version/Notes         |
|--------------|------------------------|
| Java         | 21 (JDK)               |
| Maven        | Optional (wrapper included) |
| Database     | PostgreSQL instance    |

## Configuration

Place a `.env` file at the project root. Variables are injected into the Spring environment.

| Variable                         | Example Value                                      | Notes                                  |
|----------------------------------|----------------------------------------------------|----------------------------------------|
| `SPRING_DATASOURCE_URL`          | `jdbc:postgresql://localhost:5432/financialapp`   | JDBC connection URL                    |
| `SPRING_DATASOURCE_USERNAME`     | `your_db_user`                                     | Database user                          |
| `SPRING_DATASOURCE_PASSWORD`     | `your_db_password`                                 | Database password                      |
| `SPRING_JPA_HIBERNATE_DDL_AUTO`  | `update`                                           | Schema strategy (`validate`, `update`, etc.) |
| `SERVER_PORT`                    | `8080`                                             | Server port override (optional)        |

Example `.env`:

```bash
# .env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/financialapp
SPRING_DATASOURCE_USERNAME=your_db_user
SPRING_DATASOURCE_PASSWORD=your_db_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SERVER_PORT=8080
```

## Getting Started

| OS        | Command                                 |
|-----------|------------------------------------------|
| Linux/macOS | `./mvnw spring-boot:run`               |
| Windows   | `mvnw.cmd spring-boot:run`               |

Or build the JAR and run:

| Step     | Command                      |
|----------|------------------------------|
| Build    | `./mvnw clean package`       |
| Run JAR  | `java -jar target/*.jar`     |

The app starts on the configured port (default `8080` unless overridden).

## Build and Test

| Task          | Command                |
|---------------|------------------------|
| Build         | `./mvnw clean package` |
| Run tests     | `./mvnw test`          |
| Clean         | `./mvnw clean`         |

Artifacts are produced under `target/`.

## Project Structure

```
FinancialApp/
├─ .mvn/                       # Maven wrapper internals
├─ src/
│  ├─ main/
│  │  ├─ java/                 # Controllers, services, repositories, entities
│  │  └─ resources/            # application properties, resources
│  ├─ test/                    # Tests
├─ mvnw                         # Maven wrapper (Unix)
├─ mvnw.cmd                     # Maven wrapper (Windows)
├─ pom.xml                      # Maven config (dependencies, plugins)
├─ .gitignore
└─ .gitattributes
```

Top-level files reference

| Path         | Purpose                                       |
|--------------|-----------------------------------------------|
| `pom.xml`    | Dependency and build configuration            |
| `mvnw*`      | Maven wrapper scripts                         |
| `.mvn/`      | Maven wrapper internals                       |
| `src/main`   | Application source and resources              |
| `src/test`   | Test sources                                  |

## API Overview

| Area          | Notes                                                                 |
|---------------|-----------------------------------------------------------------------|
| Base Path     | Standard Spring Boot MVC endpoints (see controllers under `src/main/java`) |
| Media Types   | `application/json`                                                    |
| Status Codes  | Conventional REST semantics (2xx success, 4xx client errors, 5xx server) |

Add/consult controller classes to discover exact routes, DTOs, and request/response formats.

## Database

| Topic          | Notes                                              |
|----------------|----------------------------------------------------|
| Engine         | PostgreSQL                                         |
| Migrations     | Managed via JPA/Hibernate DDL strategy             |
| Connection     | Driven by `.env` variables (see Configuration)     |

## Contributing

Contributions, bug reports, and feature requests are welcome.

1. Fork the repository
2. Create a feature branch
3. Commit and push your changes
4. Open a pull request describing the change

## Acknowledgements

- Spring Boot and the broader Spring ecosystem
- PostgreSQL
- Libraries and frameworks declared in `pom.xml`i