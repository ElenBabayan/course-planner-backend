# Student Course Planner – Backend

Java 21 / Spring Boot 3 service that powers the **Student Course Planner** (Gemini-driven) web app.

## Features
* PostgreSQL **UniversityDB** – students, courses, enrollments
* PostgreSQL **ApplicationDB** – chat sessions (`JSONB` column)  
  *(same Postgres instance but isolated database for clarity)*
* REST endpoints
    * `GET /university/availableCourses/{studentID}` – list courses a student can legally take next
    * `POST /session/createSession` – new chat session (Gemini prompt pre-loaded)
    * `GET  /session/{sessionID}` – retrieve session messages
* Layered architecture (Controller → Service → Repository) with JPA entities
* Unit tests (JUnit 5, Mockito)
* Flyway migrations and `data.sql` with **20 students, 30 courses** seeded

## Tech Stack
| Area      | Library / Tool |
|-----------|----------------|
| Language  | Java 21        |
| Framework | Spring Boot 3.4 |
| Data      | PostgreSQL 16, Hibernate 6 (JSONB via `hibernate-types-60`) |
| Build     | Maven          |
| Tests     | JUnit 5, Mockito |

## Quick start (local dev)

```bash
# 1. Postgres – create user + two DBs
psql -U postgres -c "CREATE ROLE planner WITH LOGIN PASSWORD 'planner';"
psql -U postgres -c "ALTER ROLE planner CREATEDB;"

createdb -U planner universitydb
createdb -U planner applicationdb

# 2. (Optional) load schema & seed data manually
psql -U planner -d universitydb    -f src/main/resources/schema-university.sql
psql -U planner -d universitydb    -f src/main/resources/data-university.sql
psql -U planner -d applicationdb   -f src/main/resources/schema-application.sql

# 3. Run the app
./mvnw spring-boot:run  # or mvn package && java -jar target/course-planner-0.0.1-SNAPSHOT.jar
```

## REST API Endpoints & Examples

### `GET /university/availableCourses/{studentID}`

Returns a list of courses that the student **can take next**, based on:
- Courses not yet completed
- Prerequisite rules

**Example**:
```http
GET /university/availableCourses/2
```

**Response:**
```json
[
  {
    "courseID": 103,
    "name": "Algorithms",
    "startDate": "2026-01-10",
    "endDate": "2026-05-20",
    "credits": 4,
    "prerequisite": {
      "courseID": 102,
      "name": "Data Structures"
    }
  },
  {
    "courseID": 105,
    "name": "English Literature II",
    "startDate": "2026-01-10",
    "endDate": "2026-05-20",
    "credits": 3,
    "prerequisite": {
      "courseID": 111,
      "name": "English Literature I"
    }
  }
]
```

---

### `POST /session/createSession`

Creates a **new chat session** and returns a `sessionID`.

**Request:**
```http
POST /session/createSession
```

**Response:**
```json
{
  "sessionID": "550e8400-e29b-41d4-a716-446655440000"
}
```

---

### `GET /session/{sessionID}`

Retrieves the **entire message history** for the specified session.

**Request:**
```http
GET /session/550e8400-e29b-41d4-a716-446655440000
```

**Response:**
```json
[
  {
    "role": "system",
    "content": "You are a university assistant. Based on the list of Courses and StudentProfile, generate an optimized SemesterPlan for the student.",
    "parts": []
  }
]
```

### 📘 API Documentation

Once the app is running, open:  
→ [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

This displays interactive API docs powered by **Swagger UI** and **OpenAPI 3**.

**Features:**
- Auto-generated from Spring annotations (`@RestController`, `@RequestMapping`, etc.)
- Includes request/response models
- Shows HTTP methods and example payloads
- Documents query and path parameters

The OpenAPI spec is also available in raw JSON:  
→ [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)


### 🔄 Docker Compose (one-liner)

```bash
# build the image, start Postgres and the app
docker compose up --build
