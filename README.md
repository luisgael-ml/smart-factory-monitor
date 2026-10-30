# Smart Factory Monitor — JDBC + PostgreSQL

Aplicación Java de consola que gestiona sensores IoT (CRUD) usando JDBC y PostgreSQL en Docker.

## Requisitos
- JDK 17+
- Maven 3.9+
- Docker Desktop

## Cómo ejecutar

### Opción A — Solo con Docker (no requiere instalar Java ni Maven)
```bash
docker compose run --rm app
```
Levanta PostgreSQL, crea la tabla con 3 sensores y abre el menú.

### Opción B — Con JDK 17 y Maven instalados
```bash
docker compose up -d postgres
mvn compile exec:java
```

## Estructura
```
src/main/java/com/nexodev/
├── Conexion.java    → abre la conexión JDBC
├── Sensor.java      → modelo (una fila de la tabla)
├── SensorDAO.java   → operaciones CRUD
└── Main.java        → menú de consola
sql/init.sql         → tabla y datos iniciales
docker-compose.yml   → contenedor PostgreSQL 16
```

## Detener la base de datos
```bash
docker compose down
```
