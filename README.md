# RechargeSys Backend &bull; Spring Boot 3 &amp; Neon PostgreSQL

![Java 21](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/Database-Neon%20Postgres-blue?logo=postgresql)
![Railway](https://img.shields.io/badge/Deploy-Railway-black?logo=railway)

Enterprise-ready REST API backend for the **Mobile Prepaid Recharge System**, powering subscriber management, telecom tariff catalogs, and instant transaction processing.

🔗 **Frontend Repository:** [Sudharsan755/rechargesystem-frontend](https://github.com/Sudharsan755/rechargesystem-frontend)

---

## 🏛️ System Architecture

```mermaid
flowchart LR
    A[Next.js Frontend] -->|REST API Requests| B[Spring Boot 3 Backend]
    B -->|HikariCP / SSL| C[(Neon Serverless PostgreSQL)]
    B -->|Railway Buildpacks| D[Cloud Deployment]
```

---

## 🚀 Key Features

- **Java 21 & Spring Boot 3.3.4**: High-performance, lightweight reactive REST controllers.
- **Neon PostgreSQL Integration**: Connects via SSL (`sslmode=require`) with custom JPA quoting for case-sensitive columns.
- **Embedded Maven Wrapper**: Zero requirement to install Maven manually — runs with `./mvnw` or `.\mvnw.cmd`.
- **CORS Configured**: Pre-configured to accept requests from localhost and production frontend domains.
- **Railway & Cloud Ready**: Includes `Procfile` and `system.properties` for seamless 1-click deployment.

---

## 📡 REST API Documentation

### 1. Authentication (`/api/auth`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Authenticate subscriber with phone and password |
| `POST` | `/api/auth/signup` | Register a new subscriber |

### 2. Subscribers (`/api/users`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/users` | List all registered subscribers |
| `POST` | `/api/users` | Create new subscriber |
| `GET` | `/api/users/{id}` | Get subscriber details by ID |
| `PUT` | `/api/users/{id}` | Update subscriber details |
| `DELETE` | `/api/users/{id}` | Remove subscriber from database |

### 3. Tariff Plans (`/api/plans`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/plans` | Retrieve all telecom tariff packs |
| `GET` | `/api/plans?operator=Airtel` | Filter tariff packs by operator (`Airtel`, `Jio`, `Vi`, `BSNL`) |
| `POST` | `/api/plans` | Add a new recharge package |
| `PUT` | `/api/plans/{id}` | Update tariff pack pricing or data limits |
| `DELETE` | `/api/plans/{id}` | Delete a tariff package |

### 4. Transactions & Recharges (`/api/recharges`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/recharges` | Fetch complete audit transaction ledger |
| `GET` | `/api/recharges?userPhone=...` | Retrieve transaction history for a specific subscriber |
| `POST` | `/api/recharges` | Process and record a new prepaid recharge |

---

## 💻 Local Development Setup

### Prerequisites
- **Java 21 SDK** installed.

### Option 1: Run with Maven Wrapper (Windows PowerShell)
```powershell
.\mvnw.cmd spring-boot:run
```

### Option 2: Run with One-Click Script
Double-click `run.bat` in the root folder.

### Option 3: Build & Package Standalone JAR
```powershell
.\mvnw.cmd clean package -DskipTests
java -jar target/backend-1.0.0.jar
```
The server will start at `http://localhost:8080`.

---

## ☁️ Deployment on Railway

1. Go to [railway.app](https://railway.app) and click **"New Project"**.
2. Select **"Deploy from GitHub repo"** and choose this repository (`Sudharsan755/rechargesystem-backend`).
3. Under **Variables**, add:
   ```env
   DATABASE_URL=postgresql://neondb_owner:...@ep-restless-silence-ay8l1tzi-pooler.c-5.us-east-2.aws.neon.tech/neondb?sslmode=require
   ```
4. Railway will automatically detect the `pom.xml`, compile with Java 21, and provide you with a live backend URL (e.g. `https://rechargesystem-backend.up.railway.app`).

---

## 🔒 License
MIT License. Built by [Sudharsan](https://github.com/Sudharsan755).
