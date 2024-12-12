(ns user
  (:require [dev]))

(println "
Development environment ready.

Available commands:
------------------
(dev/start)         Start the system
(dev/stop)          Stop the system
(dev/reset)         Reset the system (stop, reload code, start)
(dev/system-status) Check system status
(dev/print-routes)  List available routes

For more tools, explore the (dev) namespace.
")