# Deployment Guide

This guide covers different deployment strategies for your Spring Boot application.

## Table of Contents

- [Local Development](#local-development)
- [Docker Deployment](#docker-deployment)
- [Production Deployment](#production-deployment)
- [Cloud Platforms](#cloud-platforms)
- [Environment Variables](#environment-variables)
- [Security Considerations](#security-considerations)

---

## Local Development

### Prerequisites

- Java 21+
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL (or use Docker)

### Steps

1. **Setup environment**:

   ```bash
   cp .env.example .env
   ```

2. **Generate JWT keys**:

   ```bash
   openssl genrsa -out src/main/resources/app.key 2048
   openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
   ```

3. **Start database**:

   ```bash
   docker-compose up -d
   ```

4. **Run application**:

   ```bash
   ./mvnw spring-boot:run
   ```

---

## Docker Deployment

### Development Docker Setup

Use the provided `compose.yaml`:

```bash
docker-compose up -d
```

### Production Docker Setup

1. **Build the image**:

   ```bash
   docker build -t template-spring-boot:latest .
   ```

2. **Create secrets directory**:

   ```bash
   mkdir -p secrets
   cp src/main/resources/app.key secrets/
   ```

3. **Run with docker-compose**:

   ```bash
   docker-compose -f docker-compose.prod.yml up -d
   ```

### Manual Docker Run

```bash
docker run -d \
  --name template-spring-boot \
  -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://host.docker.internal:5432/template_db \
  -e DATABASE_USERNAME=postgres \
  -e DATABASE_PASSWORD=postgres \
  -v $(pwd)/secrets/app.key:/app/secrets/app.key:ro \
  template-spring-boot:latest
```

---

## Production Deployment

### Build JAR

```bash
./mvnw clean package -DskipTests
```

The JAR will be in `target/template-spring-boot-1.0.0-SNAPSHOT.jar`

### Run JAR

```bash
java -jar target/template-spring-boot-1.0.0-SNAPSHOT.jar \
  --spring.profiles.active=production \
  --DATABASE_URL=jdbc:postgresql://prod-host:5432/prod_db \
  --DATABASE_USERNAME=prod_user \
  --DATABASE_PASSWORD=${DB_PASSWORD}
```

### Systemd Service (Linux)

Create `/etc/systemd/system/template-spring-boot.service`:

```ini
[Unit]
Description=Spring Boot Template Application
After=network.target

[Service]
Type=simple
User=appuser
WorkingDirectory=/opt/template-spring-boot
ExecStart=/usr/bin/java -jar /opt/template-spring-boot/app.jar
EnvironmentFile=/opt/template-spring-boot/.env
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```

Enable and start:

```bash
sudo systemctl enable template-spring-boot
sudo systemctl start template-spring-boot
sudo systemctl status template-spring-boot
```

---

## Cloud Platforms

### AWS Elastic Beanstalk

1. **Create application**:

   ```bash
   eb init -p java-21 template-spring-boot
   ```

2. **Configure environment variables** in EB console or `.ebextensions`

3. **Deploy**:

   ```bash
   eb create production-env
   eb deploy
   ```

---

## Environment Variables

### Required Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `DATABASE_URL` | Database connection URL | `jdbc:postgresql://host:5432/db` |
| `DATABASE_USERNAME` | Database username | `postgres` |
| `DATABASE_PASSWORD` | Database password | `secure_password` |
| `JWT_PRIVATE_KEY` | Path to JWT private key | `file:/app/secrets/app.key` |
| `JWT_PUBLIC_KEY` | Path to JWT public key | `file:/app/app.pub` |

### Optional Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `APP_NAME` | template-spring-boot | Application name |
| `SERVER_PORT` | 8080 | Server port |
| `JPA_SHOW_SQL` | false | Show SQL queries in logs |
| `SPRING_PROFILES_ACTIVE` | default | Active Spring profile |

---

## Security Considerations

### Before Production Deployment

- [ ] **Generate new JWT keys** - Never use template keys
- [ ] **Use strong database passwords**
- [ ] **Enable HTTPS/TLS**
- [ ] **Configure CORS properly** in `SecurityConfig`
- [ ] **Set up secrets management** (AWS Secrets Manager, HashiCorp Vault, etc.)
- [ ] **Enable security headers**
- [ ] **Configure rate limiting**
- [ ] **Set up monitoring and logging**
- [ ] **Enable database connection pooling**
- [ ] **Configure firewall rules**
- [ ] **Set up backup strategy**
- [ ] **Review and update dependencies**

### JWT Key Management

**Development**:

- Keys in `src/main/resources/` (gitignored)

**Production**:

- Store in secrets manager (AWS Secrets Manager, Azure Key Vault, etc.)
- Mount as Docker secrets
- Use environment variables pointing to secure locations
- Rotate keys periodically

### Database Security

- Use SSL/TLS for database connections
- Restrict database access by IP
- Use strong passwords
- Enable audit logging
- Regular backups
- Encrypt data at rest

### Application Security

- Keep dependencies updated
- Enable Spring Security production settings
- Configure proper CORS
- Use HTTPS only
- Implement rate limiting
- Set up Web Application Firewall (WAF)
- Enable security headers (CSP, HSTS, etc.)

---

## Monitoring & Health Checks

### Actuator Endpoints

Available at `/actuator`:

- `/actuator/health` - Health check
- `/actuator/info` - Application info
- `/actuator/metrics` - Metrics

### Health Check

```bash
curl http://localhost:8080/actuator/health
```

Response:

```json
{
  "status": "UP"
}
```

### Logging

Configure logging in `application.properties`:

```properties
logging.level.root=INFO
logging.level.com.yourcompany=DEBUG
logging.file.name=/var/log/template-spring-boot/application.log
```

---

## Troubleshooting

### Application won't start

1. Check logs: `docker logs template-spring-boot-app`
2. Verify database connection
3. Ensure JWT keys exist
4. Check environment variables

### Database connection issues

1. Verify database is running: `docker ps`
2. Check connection string
3. Verify credentials
4. Check network connectivity

### JWT authentication fails

1. Verify keys are correctly mounted
2. Check key file permissions
3. Ensure keys match (public from private)
