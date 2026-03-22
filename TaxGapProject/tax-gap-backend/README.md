
# Tax Gap Detection & Compliance Validation Service

**Backend:** Java 17, Spring Boot 3, MySQL, JPA, Spring Security (JWT + Basic).  
**Frontend:** React + Vite + Recharts (JWT login).  

## Run Backend
1) Create DB:
```sql
CREATE DATABASE taxgap CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
```
2) Update `src/main/resources/application.yml` creds.  
3) Start:
```bash
mvn clean spring-boot:run
```
On boot, users are auto-created: `admin/admin123`, `auditor/auditor123`.

## JWT Login
```bash
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username":"auditor","password":"auditor123"}'
```
Use returned token as `Authorization: Bearer <token>`.

## Frontend
```bash
cd tax-gap-frontend
npm i
npm run dev
```

## Sample Upload
`POST /api/transactions/upload` with body:
```json
{
  "transactions": [
    {
      "transactionId":"T1001","date":"2026-03-21","customerId":"CUST-001",
      "amount":120000.00,"taxRate":0.18,"reportedTax":21600.00,"transactionType":"SALE"
    }
  ]
}
```
