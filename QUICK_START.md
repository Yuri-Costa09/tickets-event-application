# ⚡ Quick Start Guide

Get up and running in 5 minutes!

## Prerequisites

- Java 21+
- Docker & Docker Compose
- Terminal/Command Line

## Steps

### 1️⃣ Clone or Use Template

```bash
# Using GitHub template button (recommended)
Click "Use this template" on GitHub

# Or clone
git clone <your-repo-url>
cd template-spring-boot
```

### 2️⃣ Setup Environment

```bash
cp .env.example .env
# Edit .env if needed (defaults work for local dev)
```

### 3️⃣ Generate JWT Keys

```bash
openssl genrsa -out src/main/resources/app.key 2048
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

### 4️⃣ Start Database

```bash
docker-compose up -d
```

### 5️⃣ Run Application

```bash
./mvnw spring-boot:run
```

✅ **Application running at <http://localhost:8080>**

---

## Test It Out

### Register a User

```bash
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123"
  }'
```

### Login

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

Response:

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJSUzI1NiJ9...",
    "expiresIn": 3600
  }
}
```

### Use Protected Endpoint

```bash
# Replace YOUR_TOKEN with the token from login
curl -X GET http://localhost:8080/api/protected \
  -H "Authorization: Bearer YOUR_TOKEN"
```

---

## What's Next?

1. **Customize**: See [TEMPLATE_SETUP.md](TEMPLATE_SETUP.md)
2. **Deploy**: See [docs/DEPLOYMENT.md](docs/DEPLOYMENT.md)
3. **Develop**: See [docs/DEVELOPMENT.md](docs/DEVELOPMENT.md)

---

## Common Commands

```bash
# Run tests
./mvnw test

# Build JAR
./mvnw clean package

# Run with custom port
SERVER_PORT=9090 ./mvnw spring-boot:run

# View logs
docker-compose logs -f

# Stop everything
docker-compose down
```

---

## Troubleshooting

**Database connection failed?**

```bash
docker-compose down -v
docker-compose up -d
```

**JWT keys missing?**

```bash
openssl genrsa -out src/main/resources/app.key 2048
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

**Port already in use?**

```bash
# Change port in .env
SERVER_PORT=9090
```

---

## Project Structure

```
src/main/java/
├── security/     # Authentication & JWT
├── user/         # User management
├── roles/        # Role management
└── shared/       # Common utilities
```

---

## Key Files

- `application.properties` - Configuration
- `.env.example` - Environment variables template
- `compose.yaml` - Docker setup
- `pom.xml` - Dependencies

---

## Need Help?

- 📖 [Full Documentation](README.md)
- 🔧 [Setup Guide](TEMPLATE_SETUP.md)
- 🚀 [Deployment Guide](docs/DEPLOYMENT.md)
- 🔒 [Security Guide](SECURITY.md)

**Happy coding!** 🎉
