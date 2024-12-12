(ns user
  (:require [dev]))

(println "
Development environment ready.

Available commands:
------------------
(dev/go)          Initialize and start the system
(dev/stop)        Stop the system
(dev/reset)       Reset the system (stop, reload code, start)
(dev/status)      Check system status
(dev/routes)      List available routes
(dev/config)      View current configuration
")