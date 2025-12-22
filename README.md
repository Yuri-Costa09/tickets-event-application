# 🚀 Spring Boot Template

A production-ready Spring Boot 4.0 starter template with JWT authentication, role-based authorization, PostgreSQL, Flyway migrations, and best practices for building secure REST APIs.

> **Use as Template**: Click "Use this template" button on GitHub to create your own repository based on this template.

## 🚀 Features

### Security & Authentication

- **JWT Authentication** with RSA public/private key pairs
- **Role-Based Access Control (RBAC)** with Spring Security
- **Password Encryption** using BCrypt
- **Stateless Sessions** for scalability

### Architecture & Design

- **Centralized Error Handling** with `@RestControllerAdvice`
- **Standardized API Responses** using `ApiResponse<T>` wrapper
- **Base Entity Pattern** with automatic auditing (createdAt, updatedAt)
- **Repository Pattern** with Spring Data JPA

### Database & Persistence

- **PostgreSQL** as primary database
- **Flyway Migrations** for version-controlled schema changes
- **H2 Console** for development/testing
- **UUID-based Primary Keys** for entities
- **Automatic Auditing** with JPA EntityListeners

### Developer Experience

- **Lombok** integration for cleaner code
- **Docker Compose** for easy database setup
- **Spring Boot DevTools** ready
- **Actuator** endpoints for monitoring

## 📋 Prerequisites

- Java 21+
- Maven 3.8+
- Docker & Docker Compose (for database)
- PostgreSQL (or use provided Docker setup)

## ⚡ Quick Start

### 1. Use This Template

Click **"Use this template"** button on GitHub or clone:

```bash
git clone <your-repo-url>
cd template-spring-boot
```

### 2. Configure Environment

```bash
# Copy environment template
cp .env.example .env

# Edit .env with your settings (optional - defaults work for local dev)
```

### 3. Generate RSA Keys for JWT

```bash
# Generate private key
openssl genrsa -out src/main/resources/app.key 2048

# Generate public key from private key
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

**Important**: Keys are gitignored. Never commit `app.key` to version control.

### 4. Start Database

```bash
docker-compose up -d
```

### 5. Run Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or with environment variables
APP_NAME=my-app ./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### 6. Test Authentication

```bash
# Register a new user
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"username":"test","email":"test@example.com","password":"password123"}'

# Login
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"test","password":"password123"}'
```

## 📚 Documentation

- **[Quick Start](QUICK_START.md)** - Get running in 5 minutes
- **[Template Setup](TEMPLATE_SETUP.md)** - Customize for your project
- **[Deployment Guide](docs/DEPLOYMENT.md)** - Deploy to production
- **[Security Policy](SECURITY.md)** - Security best practices

## 🔑 Default Endpoints

### Public Endpoints (No Authentication Required)

- `POST /login` - User authentication
- `POST /register` - User registration

All other endpoints are protected by default (change in `SecurityConfig.PUBLIC_ENDPOINTS`).

## 🏗️ Project Structure

```
src/main/java/com/yuricosta/real_state_ai_backend/  # ⚠️ Change to your package
├── security/              # Authentication & authorization
│   ├── controllers/       # Auth endpoints (login, register)
│   ├── dtos/             # Request/response DTOs
│   ├── AuthService.java  # JWT generation logic
│   └── SecurityConfig.java # Security configuration
├── user/                  # User domain
│   ├── User.java         # User entity
│   ├── UserRepository.java
│   └── UserService.java
├── roles/                 # Role management
│   ├── Role.java
│   └── RoleRepository.java
└── shared/               # Shared utilities
    ├── ApiResponse.java  # Standard response wrapper
    ├── StandardError.java # Error response format
    ├── BaseEntity.java   # Base class for entities
    ├── GlobalExceptionHandler.java # Centralized error handling
    └── errors/           # Custom exceptions
```

**Note**: The package name `com.yuricosta.real_state_ai_backend` is a placeholder. Refactor to your own package when using this template.

## 🎯 Core Conventions

### 1. API Response Format

All successful responses use `ApiResponse<T>`:

```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... },
  "timestamp": "2025-12-11T10:30:00"
}
```

### 2. Error Response Format

All errors use `StandardError`:

```json
{
  "timestamp": "2025-12-11T10:30:00.123Z",
  "status": 404,
  "error": "Not Found",
  "message": "User not found",
  "path": "/api/users/123"
}
```

### 3. Entity Base Class

All entities extend `BaseEntity` for automatic:

- UUID-based primary keys
- `createdAt` timestamp
- `updatedAt` timestamp

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Spring Boot | 4.0.0 | Framework |
| Java | 21 | Language |
| PostgreSQL | Latest | Database |
| Flyway | Latest | Migrations |
| Spring Security | 6.x | Security |
| OAuth2 Resource Server | 6.x | JWT handling |
| Lombok | Latest | Code generation |
| BCrypt | - | Password hashing |

## 🔒 Security Features

- **JWT Tokens**: 1-hour expiration (configurable)
- **Password Hashing**: BCrypt with default strength
- **Stateless Authentication**: No server-side sessions
- **RSA Encryption**: Public/private key pair for JWT signing
- **CSRF Protection**: Disabled for stateless API
- **CORS**: Configure in `SecurityConfig` as needed

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw verify
```

## 📦 Building for Production

```bash
# Create executable JAR
./mvnw clean package

# Run with production profile and environment variables
java -jar target/template-spring-boot-1.0.0-SNAPSHOT.jar \
  -Dspring.profiles.active=production \
  -DDATABASE_URL=jdbc:postgresql://prod-host:5432/prod_db \
  -DDATABASE_USERNAME=prod_user \
  -DDATABASE_PASSWORD=secure_password
```

### Docker Deployment

Create a `Dockerfile`:

```dockerfile
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
COPY src/main/resources/app.pub /app/app.pub
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:

```bash
docker build -t my-spring-app .
docker run -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://host.docker.internal:5432/template_db \
  -e DATABASE_USERNAME=postgres \
  -e DATABASE_PASSWORD=postgres \
  my-spring-app
```

## 🔧 Configuration

All sensitive configuration uses environment variables with sensible defaults.

### Environment Variables

See `.env.example` for all available options:

| Variable | Default | Description |
|----------|---------|-------------|
| `APP_NAME` | template-spring-boot | Application name |
| `SERVER_PORT` | 8080 | Server port |
| `DATABASE_URL` | jdbc:postgresql://localhost:5432/template_db | Database connection |
| `DATABASE_USERNAME` | postgres | Database user |
| `DATABASE_PASSWORD` | postgres | Database password |
| `JWT_PUBLIC_KEY` | classpath:app.pub | Path to JWT public key |
| `JWT_PRIVATE_KEY` | classpath:app.key | Path to JWT private key |

### Local Development

For local development, the defaults in `application.properties` work out of the box with the provided `compose.yaml`.

## 🚀 Customizing for Your Project

### 1. Update Package Names

**Option A: IDE Refactoring (Recommended)**

- Right-click package `com.yuricosta.real_state_ai_backend`
- Select "Refactor" → "Rename"
- Enter your package name (e.g., `com.yourcompany.yourproject`)

**Option B: Command Line**

```bash
# Linux/Mac
find src -type f -name "*.java" -exec sed -i '' 's/com.yuricosta.real_state_ai_backend/com.yourcompany.yourproject/g' {} +

# Then move directories
mkdir -p src/main/java/com/yourcompany/yourproject
mv src/main/java/com/yuricosta/real_state_ai_backend/* src/main/java/com/yourcompany/yourproject/
```

### 2. Update Project Metadata

Edit `pom.xml`:

```xml
<groupId>com.yourcompany</groupId>
<artifactId>your-project-name</artifactId>
<name>Your Project Name</name>
<description>Your project description</description>
```

### 3. Configure Environment

Create `.env` from `.env.example` and customize:

```bash
cp .env.example .env
# Edit .env with your values
```

### 4. Update Database Name

In `.env`:

```env
DATABASE_NAME=your_db_name
DATABASE_URL=jdbc:postgresql://localhost:5432/your_db_name
```

### 5. Generate Your JWT Keys

```bash
openssl genrsa -out src/main/resources/app.key 2048
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

### 6. Update Documentation

- Update this README with your project details
- Modify docs in `/docs` folder as needed
- Update the LICENSE file

## 📖 Learning Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Security OAuth2](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)
- [Flyway Documentation](https://flywaydb.org/documentation/)

## ⚠️ Security Checklist

Before deploying to production:

- [ ] Change all default passwords
- [ ] Generate new JWT key pairs (never use template keys in production)
- [ ] Configure CORS properly in `SecurityConfig`
- [ ] Enable HTTPS/TLS
- [ ] Set up proper environment variable management (AWS Secrets Manager, etc.)
- [ ] Review and adjust JWT token expiration time
- [ ] Enable Spring Security production settings
- [ ] Set up proper logging and monitoring
- [ ] Configure database connection pooling
- [ ] Review and customize exception messages (avoid leaking internal details)

## 📝 License

MIT License - feel free to use this template for any purpose.

## 🙏 Credits

Created as a production-ready starter template for Spring Boot projects.

---

**Ready to build your next Spring Boot application!** 🚀
