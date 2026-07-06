# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**SIGEPS v10** — Sistema Integrado de Gestión de Personal de Seguridad, version 1.0. Spring Boot 3.2.3 REST API backed by MySQL, secured with JWT.

- Java 17, Maven, Spring Boot 3.2.3
- Database: MySQL (`bdsigepsv10`, port 3306, timezone `America/Bogota`)
- Runs on port 8080

## Commands

```bash
# Build (skip tests)
mvn clean package -DskipTests

# Run the application
mvn spring-boot:run

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=NombreDeClaseTest

# Build JAR
mvn clean install -DskipTests
```

Swagger UI is available at `http://localhost:8080/swagger-ui/index.html` once running.

## Architecture

The project follows a strict four-layer pattern applied identically to every domain entity:

```
web/controller/     → REST controllers (@RestController), one per entity
dominio/service/    → Service interfaces
dominio/serviceImpl/→ Service implementations (@Service), hold all business logic
dominio/dto/        → DTOs for request/response (Lombok @Data)
dominio/Constantes/ → MensajesConstantes.java — all response message strings
persistencia/entity/→ JPA entities (@Entity)
persistencia/repository/ → Spring Data JPA interfaces extending JpaRepository
persistencia/dao/   → DAO components (@Component) responsible for Entity↔DTO mapping
security/           → JWT auth/authz filters and Spring Security config
```

### DAO Role (Critical Pattern)

The `persistencia/dao/` classes are **not** the data access layer — they handle **entity-to-DTO and DTO-to-entity conversion**, including FK resolution via repositories. Each DAO has two methods: one converting Entity→DTO and one converting DTO→Entity. The repository is the actual data access layer.

### Service Layer Pattern

Every `ServiceImpl` follows this flow for mutations:
1. Validate uniqueness constraints manually (using repository lookup + `long` flag variables)
2. Manage the primary key manually: `findMaxId() + 1` (no auto-increment delegation)
3. Call `repository.save(dao.entity(dto))` on success
4. Return a `RespuestaDTO` carrying `mensaje` (String) + `banderaexito` (boolean) + optionally the DTO

### RespuestaDTO

`RespuestaDTO` is a single unified response wrapper used by all mutation endpoints. It contains fields for every entity type (both singular and list), `mensaje`, and `banderaexito`. Fields are `@JsonInclude(NON_NULL)` so only populated fields appear in the response.

### Repository Queries

Repositories use native SQL `@Query` with:
- `(:param IS NULL OR ...)` pattern for optional filters
- `CASE WHEN :orderBy = 'field' AND :orderMode = 'ASC' THEN ...` pattern for dynamic sorting
- Both a non-paginated list variant (`List<Entity>`) and a paginated variant (`Slice<Entity>`) for each entity

### Security

- `POST /login` — public, handled by `JWTAuthenticationFilter` (reads `nicknameUsuario`/`passwordUsuario` from JSON body via `AuthCredentials`)
- `GET /usuarios/recuperacionContrasena/**` and `GET /usuarios/numeroDocumento/**` — public
- All other endpoints require a valid JWT in the `Authorization: Bearer <token>` header
- `TokenUtils` signs tokens with a hardcoded HMAC secret, 30-day expiry
- **Passwords are stored and compared in plain text** — the `PasswordEncoder` bean does no hashing

### Domain Entities

Core entities: `Usuario`, `TipoUsuario`, `TipoDocumentoIdentificacion`, `Rol`, `Funcionalidad`, `PrivilegyRestriccAccesoUsuario`, `ParametrosSistema`, `TipoEmpleado`, `TipoEmpleadoPlanta`, `PaisMundo`, `DepartamentooEstadoMundo`, `CiudadMundo`.

`Usuario` has FK relationships to `TipoDocumentoIdentificacion` and `TipoUsuario`. Text fields on `Usuario` are normalized to uppercase on write via `.toUpperCase()` in the DAO.

### Adding a New Entity

Follow this checklist in order:
1. `persistencia/entity/` — JPA entity
2. `persistencia/repository/` — JpaRepository interface with custom `@Query` methods
3. `dominio/dto/` — DTO class
4. `persistencia/dao/` — DAO component with `entity(dto)` and `dto(entity)` methods
5. `dominio/service/` — service interface
6. `dominio/serviceImpl/` — service implementation
7. `web/controller/` — REST controller
8. Add the new DTO fields (singular + list) to `RespuestaDTO`
9. Add any new message constants to `MensajesConstantes`
