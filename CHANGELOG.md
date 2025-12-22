# Changelog

All notable changes to this template will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2025-12-12

### Added

- Initial template release
- JWT authentication with RSA keys
- Role-based authorization (RBAC)
- User registration and login endpoints
- PostgreSQL database with Flyway migrations
- Docker and Docker Compose support
- Centralized error handling with `@RestControllerAdvice`
- Standardized API responses with `ApiResponse<T>`
- Base entity pattern with automatic auditing
- Spring Security 6.x configuration
- Comprehensive documentation in `/docs`
- Environment variable configuration
- GitHub Actions CI/CD pipeline
- Production-ready Dockerfile
- Health check endpoints via Spring Actuator
- Lombok integration
- H2 console for development

### Security

- BCrypt password hashing
- Stateless JWT authentication
- CSRF protection disabled for stateless API
- Secure default configuration

### Documentation

- README with quick start guide
- Quick start guide (5 minutes)
- Template setup instructions
- Deployment guide
- Security policy

## [Unreleased]

### Planned

- Email verification
- Password reset functionality
- Refresh token support
- API rate limiting
- Swagger/OpenAPI documentation
- Integration tests
- Performance benchmarks
