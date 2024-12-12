# NXT Server

A Clojure web application using:
- Pedestal (web server)
- Stuart Sierra Component (system management)
- HTMX (dynamic HTML interactions)
- Bulma CSS (styling)

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
   (dev/start)
   ```

3. Visit http://localhost:3000

### Development Commands

- `(dev/start)` - Start the system
- `(dev/stop)` - Stop the system
- `(dev/reset)` - Reset the system (stop, reload code, start)
- `(dev/system-status)` - Check system status
- `(dev/print-routes)` - List available routes

### Production

To run in production:

```bash
clj -M -m nxt.system
```

## License

Copyright © 2024
