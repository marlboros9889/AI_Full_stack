# Oracle Setup

This project supports two database modes:

- default mode: H2 in-memory database
- oracle mode: Oracle XE profile

Oracle mode is useful for practicing real database setup while keeping the same Spring Boot application code.

## 1. Verify Oracle Login

Use SQL*Plus or another Oracle client:

```sql
SELECT SYSDATE FROM DUAL;
```

Expected result:

- Oracle returns the current database date and time.

## 2. Create Project Tables

Run the scripts in this order:

1. `src/main/resources/db/oracle/01_verify_sysdate.sql`
2. `src/main/resources/db/oracle/02_create_tables.sql`
3. `src/main/resources/db/oracle/03_seed_sample_data.sql`

From this machine, the SQL*Plus command shape is:

```powershell
& 'C:\JAng\dbhomeXE\bin\sqlplus.exe' -S scott/tiger@localhost:1521/xe '@C:\Program Files\Jang_adam\AI_Full_stack\marinboy\src\main\resources\db\oracle\01_verify_sysdate.sql'
& 'C:\JAng\dbhomeXE\bin\sqlplus.exe' -S scott/tiger@localhost:1521/xe '@C:\Program Files\Jang_adam\AI_Full_stack\marinboy\src\main\resources\db\oracle\02_create_tables.sql'
& 'C:\JAng\dbhomeXE\bin\sqlplus.exe' -S scott/tiger@localhost:1521/xe '@C:\Program Files\Jang_adam\AI_Full_stack\marinboy\src\main\resources\db\oracle\03_seed_sample_data.sql'
```

## 3. Run Spring Boot With Oracle Profile

```bash
mvn spring-boot:run "-Dspring-boot.run.profiles=oracle"
```

If port `8080` is already in use:

```bash
mvn spring-boot:run "-Dspring-boot.run.profiles=oracle" "-Dspring-boot.run.arguments=--server.port=8081"
```

## 4. Verify From The Application

- `GET /api/health`
- `GET /api/db-time`
- `GET /api/services`
- `GET /api/services/1/available-slots?date=2030-01-15`
- `GET /api/reservations`

Expected `/api/db-time` shape:

```json
{
  "vendor": "ORACLE",
  "validationQuery": "SELECT SYSDATE FROM DUAL",
  "databaseTime": "2026-07-07 12:10:26.0"
}
```

## Tables

The JPA entities map to:

- `SALON_SERVICE_ITEM`
- `SALON_RESERVATION`

The seed script resets demo rows and inserts clean sample data for:

- `Signature Cut`
- `Quick Trim`
- `Root Color`
- `Perm Styling`

## Notes

- The Oracle profile keeps `spring.jpa.hibernate.ddl-auto` set to `none`.
- The Oracle profile does not run the H2 `data.sql` seed script.
- Override values with `ORACLE_URL`, `ORACLE_USERNAME`, and `ORACLE_PASSWORD`.
