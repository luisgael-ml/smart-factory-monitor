# Smart Factory Monitor — JDBC + PostgreSQL

Aplicación Java de consola que gestiona sensores IoT (CRUD) usando JDBC y PostgreSQL en Docker.

## Requisitos
- JDK 17+
- Maven 3.9+
- Docker Desktop

## Cómo ejecutar

La contraseña de la base de datos se toma de la variable `DB_PASSWORD`. Para cambiarla, crea un archivo `.env` a partir de `.env.example` (ese archivo no se sube a GitHub); si no existe, se usa la contraseña de desarrollo `admin123`.

### Opción A — Solo con Docker (no requiere instalar Java ni Maven)
```bash
docker compose run --rm app
```
Levanta PostgreSQL, crea la tabla con 3 sensores y abre el menú.

### Opción B — Con JDK 17 y Maven instalados
```bash
docker compose up -d postgres
set DB_PASSWORD=admin123      # Linux/Mac: export DB_PASSWORD=admin123
mvn compile exec:java
```

## Seguridad
- La conexión JDBC viaja cifrada con SSL/TLS (`sslmode=require`).
- La contraseña se lee de la variable de entorno `DB_PASSWORD`; no está escrita en el código.
- Todas las consultas usan `PreparedStatement` para evitar inyección SQL.

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
