# 📋 Template Setup Guide

This guide will help you customize this Spring Boot template for your project.

## Quick Setup (5 minutes)

### 1. Clone/Use Template

```bash
# Use GitHub's "Use this template" button, or clone:
git clone <your-repo-url>
cd template-spring-boot
```

### 2. Configure Environment

```bash
cp .env.example .env
# Edit .env if needed (defaults work for local development)
```

### 3. Generate JWT Keys

```bash
openssl genrsa -out src/main/resources/app.key 2048
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

### 4. Start Database & Run

```bash
docker-compose up -d
./mvnw spring-boot:run
```

✅ **Template is ready to use with default settings!**

---

## Full Customization (15-30 minutes)

Follow these steps to fully customize for your project:

### Step 1: Update Package Name

**Using IntelliJ IDEA / Eclipse:**

1. Right-click on package: `com.yuricosta.real_state_ai_backend`
2. Select **Refactor → Rename**
3. Enter your package name: `com.yourcompany.projectname`
4. Select "Search in comments and strings" and "Search for text occurrences"
5. Click **Refactor**

**Using VS Code:**

1. Open Find and Replace (Cmd/Ctrl + Shift + H)
2. Find: `com.yuricosta.real_state_ai_backend`
3. Replace: `com.yourcompany.projectname`
4. Replace all in `src/` folder
5. Manually move directories to match new package structure

**Using Command Line:**

```bash
# 1. Replace in all Java files
find src -type f -name "*.java" -exec sed -i '' \
  's/com\.yuricosta\.real_state_ai_backend/com.yourcompany.projectname/g' {} +

# 2. Create new directory structure
mkdir -p src/main/java/com/yourcompany/projectname
mkdir -p src/test/java/com/yourcompany/projectname

# 3. Move files
mv src/main/java/com/yuricosta/real_state_ai_backend/* \
   src/main/java/com/yourcompany/projectname/

mv src/test/java/com/yuricosta/real_state_ai_backend/* \
   src/test/java/com/yourcompany/projectname/

# 4. Remove old directories
rm -rf src/main/java/com/yuricosta
rm -rf src/test/java/com/yuricosta
```

### Step 2: Update Project Metadata

Edit `pom.xml`:

```xml
<groupId>com.yourcompany</groupId>
<artifactId>your-project-name</artifactId>
<version>1.0.0-SNAPSHOT</version>
<name>Your Project Name</name>
<description>Your project description</description>
```

### Step 3: Configure Application Name

Edit `.env`:

```env
APP_NAME=your-project-name
```

### Step 4: Configure Database

Edit `.env`:

```env
DATABASE_NAME=your_project_db
DATABASE_URL=jdbc:postgresql://localhost:5432/your_project_db
DATABASE_USERNAME=your_user
DATABASE_PASSWORD=your_secure_password
```

Update `compose.yaml` if needed (it uses .env variables automatically).

### Step 5: Update Documentation

1. **README.md**: Update project description, features, and author info
2. **docs/**: Review and update documentation files
3. **LICENSE**: Add your license file

### Step 6: Remove Template-Specific Files

```bash
rm TEMPLATE_SETUP.md  # This file
# Optionally remove or update docs/
```

### Step 7: Initialize Git (if starting fresh)

```bash
rm -rf .git  # Remove template's git history
git init
git add .
git commit -m "Initial commit from Spring Boot template"
git branch -M main
git remote add origin <your-repo-url>
git push -u origin main
```

---

## Verification Checklist

After setup, verify everything works:

- [ ] Application starts without errors: `./mvnw spring-boot:run`
- [ ] Database connects successfully (check logs)
- [ ] Flyway migrations run successfully
- [ ] Can register a user: `POST /register`
- [ ] Can login: `POST /login`
- [ ] JWT token is returned in login response
- [ ] Protected endpoints require authentication
- [ ] Tests pass: `./mvnw test`

---

## Common Issues

### Issue: "Failed to load ApplicationContext"

**Solution**: Ensure JWT keys are generated and in `src/main/resources/`

### Issue: "Connection refused" to database

**Solution**:

- Check if Docker is running: `docker ps`
- Start database: `docker-compose up -d`
- Check port conflicts (default: 5432)

### Issue: Package structure mismatch

**Solution**: Ensure package in Java files matches directory structure

### Issue: Flyway migration fails

**Solution**:

- Database might have old schema. Drop and recreate:

```bash
docker-compose down -v
docker-compose up -d
```

---

## Next Steps

1. **Add Your Features**: Start building your domain models and services
2. **Update Security**: Configure CORS, adjust JWT expiration, add roles
3. **Add Tests**: Write integration and unit tests
4. **Configure CI/CD**: Set up GitHub Actions, Jenkins, etc.
5. **Deploy**: Follow production deployment guide in README.md

---

## Need Help?

- [Quick Start Guide](QUICK_START.md)
- [Deployment Guide](docs/DEPLOYMENT.md)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

**Happy coding!** 🚀
