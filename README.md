# NXT Server

A Clojure web application using:
- Pedestal (web server)
- Stuart Sierra Component (system management)
- HTMX (dynamic HTML interactions)
- Bulma CSS (styling)
- Aero (configuration management)

## Development

### Prerequisites

- Java JDK 11+
- Clojure CLI tools

### Running

1. Start a REPL:
   ```bash
   clj -M:dev
   ```

2. Start the system:
   ```clojure
   (dev/go)
   ```

3. Visit http://localhost:3000

### Development Commands

- `(dev/go)`           Start the system
- `(dev/stop)`         Stop the system
- `(dev/reset)`        Reset the system (stop, reload code, start)
- `(dev/status)`       Check system status
- `(dev/routes)`       List available routes
- `(dev/print-config)` View current configuration

### Testing

Run tests:
```bash
clj -X:test
```

Run tests with coverage:
```bash
# Using the convenience script
./scripts/test-coverage.sh

# Or directly with clojure
clj -M:coverage
```

Coverage reports will be generated in:
- HTML report: target/coverage/index.html
- Codecov report: target/coverage/codecov.json
- JUnit report: target/coverage/junit.xml

### Configuration

Configuration is managed using Aero and separated by environment:

- Development: `resources/config/nxt/development.edn`
- Production: `resources/config/nxt/production.edn`
- Base config: `resources/config/nxt/base.edn`

### Production

To run in production:

```bash
# Set required environment variables
export PORT=8080
export ALLOWED_ORIGINS="https://yoursite.com"

# Run with production profile
clj -M -m nxt.system production
```

## License

Copyright © 2024