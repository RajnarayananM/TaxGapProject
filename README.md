# Tax Gap Detection & Compliance Validation System

A full-stack project built with **Spring Boot (Java 17)** and **React (Vite)** to process tax-related transactions, validate them using a rule engine, flag exceptions, and present reports via a simple UI.

---

## Project Structure

```
project-root/
 ├─ backend/     # Spring Boot REST API (Java 17 + MySQL)
 └─ frontend/    # React UI (Vite + Axios)
```

---

## Features

Backend (Spring Boot):
- JWT-based authentication with Admin and Auditor roles
- Batch transaction upload (JSON)
- Tax computation (expected tax, tax gap) and compliance classification
- Rule engine (DB-driven) with examples:
  - High-value transaction
  - Refund validation (original or fallback recent sale)
  - GST slab violation
- Exception recording with severity and messages
- Reporting APIs (customer summaries, exception summaries)
- Automatic table creation using `schema.sql` and default seeds via `data.sql`

Frontend (React):
- Login screen (JWT)
- Transaction upload UI (JSON)
- Exceptions list
- Reports view (customer compliance, exception counts)

---

## Tech Stack

Backend: Java 17, Spring Boot, Spring Security (JWT), Spring Data JPA, MySQL, Maven

Frontend: React (Vite), JavaScript/JSX, Axios, Recharts (for charts)

---

## Backend – Setup (Local)

Requirements:
- Java 17
- MySQL 8.x
- Maven (or use IntelliJ Maven import)

1) Create the database:

```sql
CREATE DATABASE taxgap CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
```

2) Configure database credentials in `backend/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/taxgap?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false
    username: root
    password: root
```

3) Run the backend:

```bash
cd backend
mvn clean spring-boot:run
# App starts on http://localhost:8080
```

Default users (auto-created on first run):
- admin / admin123
- auditor / auditor123

---

## Frontend – Setup (Local)

1) Install and run:

```bash
cd frontend
npm install
npm run dev
# Opens http://localhost:5173
```

---

## Authentication

1) Get a JWT token by logging in:

```
POST /auth/login
Content-Type: application/json
```

Body example:

```json
{ "username": "auditor", "password": "auditor123" }
```

2) Use the token for secured endpoints:

```
Authorization: Bearer <token>
```

---

## Core API Examples

Upload transactions:

```
POST /api/transactions/upload
Authorization: Bearer <token>
Content-Type: application/json
```

Sample payload:

```json
{
  "transactions": [
    {
      "transactionId": "T1001",
      "date": "2026-03-21",
      "customerId": "CUST001",
      "amount": 120000,
      "taxRate": 0.18,
      "reportedTax": 21600,
      "transactionType": "SALE"
    }
  ]
}
```

Reports:

```
GET /api/reports/customers
GET /api/reports/exceptions
GET /api/reports/exceptions/per-customer
```

Exceptions with filters:

```
GET /api/exceptions?customerId=CUST001&severity=HIGH&ruleName=HighValueTransaction
```

---

## Notes

- `schema.sql` and `data.sql` are executed automatically on startup.
- The project is configured for local development on `localhost` ports 8080 (backend) and 5173 (frontend).
- For production, externalize secrets (JWT secret, DB credentials) via environment variables.

---


