# marinboy

`marinboy` is a Spring Boot portfolio project for managing reservations in a one-person hair salon.

The app focuses on a realistic owner workflow: customers request reservations, the system prevents invalid time slots, and the salon owner manages reservation status from a simple dashboard.

## Stack

- Java 17
- Spring Boot 3.5.3
- Spring Web
- Thymeleaf
- Spring Data JPA
- Bean Validation
- H2 Database for local demo mode
- Oracle XE profile for database practice

## Main Features

- Service catalog with duration, price, and description
- Customer reservation request form
- Available slot lookup by service and date
- Reservation conflict prevention
- Business rules for a one-person salon
- Owner-side reservation list and status updates
- Consistent JSON error responses for API failures
- H2 default mode and Oracle profile mode
- Oracle `SYSDATE` verification endpoint

## Business Rules

- Business hours are `10:00` to `20:00`.
- Sunday reservations are not allowed.
- Reservations can start only on 30-minute boundaries.
- Active reservations with `REQUESTED` or `CONFIRMED` status block overlapping slots.
- Canceled or completed reservations no longer block future slot lookup.

## Run With H2

```bash
mvn spring-boot:run
```

Open the dashboard:

- `http://localhost:8080`

Useful H2 endpoints:

- `GET /api/health`
- `GET /api/db-time`
- `GET /api/services`
- `GET /api/services/1/available-slots?date=2030-01-15`
- `GET /api/reservations`

## Run With Oracle XE

The Oracle profile defaults to:

- URL: `jdbc:oracle:thin:@localhost:1521:xe`
- Username: `scott`
- Password: `tiger`

Run Oracle mode:

```bash
mvn spring-boot:run "-Dspring-boot.run.profiles=oracle"
```

Override credentials when needed:

```bash
set ORACLE_USERNAME=scott
set ORACLE_PASSWORD=tiger
set ORACLE_URL=jdbc:oracle:thin:@localhost:1521:xe
mvn spring-boot:run "-Dspring-boot.run.profiles=oracle"
```

Oracle setup files:

- [docs/oracle-setup.md](</C:/Program Files/Jang_adam/AI_Full_stack/marinboy/docs/oracle-setup.md>)
- [01_verify_sysdate.sql](</C:/Program Files/Jang_adam/AI_Full_stack/marinboy/src/main/resources/db/oracle/01_verify_sysdate.sql>)
- [02_create_tables.sql](</C:/Program Files/Jang_adam/AI_Full_stack/marinboy/src/main/resources/db/oracle/02_create_tables.sql>)
- [03_seed_sample_data.sql](</C:/Program Files/Jang_adam/AI_Full_stack/marinboy/src/main/resources/db/oracle/03_seed_sample_data.sql>)

## API Error Shape

Failed API requests return a consistent JSON body:

```json
{
  "timestamp": "2026-07-07T12:14:50",
  "status": 409,
  "error": "Conflict",
  "message": "There is already a reservation in that time slot.",
  "path": "/api/reservations"
}
```

## Tests

Run the full test suite:

```bash
mvn test
```

The tests cover:

- Spring context loading
- reservation creation conflict rules
- reservation status updates
- H2 database time verification
- business-hour validation
- Sunday closure validation
- 30-minute slot validation
- available slot calculation
- structured API error responses
