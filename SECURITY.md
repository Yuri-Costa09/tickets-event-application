# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |

## Reporting a Vulnerability

If you discover a security vulnerability in this template, please follow these steps:

### 1. Do Not Open a Public Issue

Please **do not** open a public GitHub issue for security vulnerabilities.

### 2. Report Privately

Send a detailed report to: [your-email@example.com]

Include:

- Description of the vulnerability
- Steps to reproduce
- Potential impact
- Suggested fix (if any)

### 3. Response Timeline

- **Initial Response**: Within 48 hours
- **Status Update**: Within 7 days
- **Fix Timeline**: Depends on severity
  - Critical: 1-3 days
  - High: 1-2 weeks
  - Medium: 2-4 weeks
  - Low: Best effort

## Security Best Practices

When using this template:

### 1. JWT Keys

- **Never commit private keys** to version control
- Generate new keys for each environment
- Use strong key sizes (minimum 2048 bits for RSA)
- Rotate keys periodically
- Store keys securely (AWS Secrets Manager, Azure Key Vault, etc.)

```bash
# Generate secure keys
openssl genrsa -out app.key 4096
openssl rsa -in app.key -pubout -out app.pub
```

### 2. Database Security

- Use strong, unique passwords
- Enable SSL/TLS for database connections
- Restrict database access by IP
- Regular backups
- Keep PostgreSQL updated

### 3. Environment Variables

- Never commit `.env` files
- Use secrets management in production
- Rotate credentials regularly
- Use different credentials per environment

### 4. Dependencies

- Keep dependencies updated
- Run security audits regularly:

  ```bash
  ./mvnw dependency-check:check
  ```

- Subscribe to security advisories

### 5. CORS Configuration

Update `SecurityConfig.java` with appropriate CORS settings:

```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("https://yourdomain.com"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
```

### 6. HTTPS/TLS

- Always use HTTPS in production
- Configure proper SSL certificates
- Enable HSTS headers
- Disable insecure protocols (TLS 1.0, 1.1)

### 7. Rate Limiting

Implement rate limiting to prevent abuse:

```java
// Example using Bucket4j or similar
@Bean
public RateLimiter rateLimiter() {
    return RateLimiter.create(100.0); // 100 requests per second
}
```

### 8. Input Validation

- Always validate user input
- Use `@Valid` and `@Validated` annotations
- Sanitize data before processing
- Use parameterized queries (already done with JPA)

### 9. Error Messages

- Don't expose internal details in error messages
- Log detailed errors server-side only
- Return generic messages to clients

### 10. Logging

- Don't log sensitive data (passwords, tokens, etc.)
- Use appropriate log levels
- Implement log rotation
- Monitor logs for suspicious activity

## Security Checklist

Before deploying to production:

- [ ] Generate new JWT keys (never use template keys)
- [ ] Change all default passwords
- [ ] Configure CORS properly
- [ ] Enable HTTPS/TLS
- [ ] Set up secrets management
- [ ] Review JWT token expiration (default: 1 hour)
- [ ] Enable Spring Security production settings
- [ ] Set up proper logging and monitoring
- [ ] Configure database connection pooling
- [ ] Review exception messages
- [ ] Enable security headers (CSP, HSTS, X-Frame-Options, etc.)
- [ ] Implement rate limiting
- [ ] Set up Web Application Firewall (WAF)
- [ ] Configure firewall rules
- [ ] Enable audit logging
- [ ] Set up intrusion detection
- [ ] Regular security audits
- [ ] Penetration testing
- [ ] Update dependencies

## Known Security Considerations

### JWT Token Storage

- Tokens are stateless and cannot be revoked
- Consider implementing token blacklist for logout
- Use short expiration times
- Implement refresh token mechanism

### Password Policy

Default implementation uses BCrypt but doesn't enforce:

- Minimum password length
- Password complexity
- Password history
- Account lockout after failed attempts

Consider implementing these based on your requirements.

### Session Management

Application is stateless by default. If you add session management:

- Use secure session cookies
- Implement session timeout
- Regenerate session IDs after login

## Security Headers

Add to `SecurityConfig.java`:

```java
http.headers()
    .contentSecurityPolicy("default-src 'self'")
    .and()
    .frameOptions().deny()
    .and()
    .xssProtection().block(true)
    .and()
    .httpStrictTransportSecurity()
        .maxAgeInSeconds(31536000)
        .includeSubDomains(true);
```

## Compliance

This template provides a foundation but may require additional configuration for:

- GDPR (data protection, right to deletion, etc.)
- HIPAA (healthcare data)
- PCI DSS (payment card data)
- SOC 2 (security controls)

Consult with legal and compliance teams for your specific requirements.

## Resources

- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/)
- [JWT Best Practices](https://tools.ietf.org/html/rfc8725)
- [NIST Cybersecurity Framework](https://www.nist.gov/cyberframework)

## Updates

This security policy will be updated as new vulnerabilities are discovered or best practices evolve.

Last updated: 2025-12-12
